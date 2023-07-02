package com.lixin.trainticketsellsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixin.trainticketsellsystem.common.SystemUtils;
import com.lixin.trainticketsellsystem.dao.PurchaseRecordDao;
import com.lixin.trainticketsellsystem.dao.TicketDao;
import com.lixin.trainticketsellsystem.model.bo.TicketQueryBo;
import com.lixin.trainticketsellsystem.model.entity.PurchaseRecord;
import com.lixin.trainticketsellsystem.model.entity.Ticket;
import com.lixin.trainticketsellsystem.model.vo.request.PageRequest;
import com.lixin.trainticketsellsystem.model.vo.request.TicketQuery;
import com.lixin.trainticketsellsystem.model.vo.response.PurchaseRecordList;
import com.lixin.trainticketsellsystem.model.vo.response.PurchaseRecordListItem;
import com.lixin.trainticketsellsystem.model.vo.response.TicketList;
import com.lixin.trainticketsellsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author lixin
 * @date 2023/7/1 22:19
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;
    private final PurchaseRecordDao purchaseRecordDao;

    @Override
    public boolean add(Ticket ticket) {
        ticketDao.insert(ticket);
        return true;
    }

    @Override
    public boolean del(long id) {
        ticketDao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public TicketList list(TicketQuery query) {
        TicketQueryBo queryBo = new TicketQueryBo();
        BeanUtils.copyProperties(query, queryBo);
        LocalDate date = query.getDate();
        if (Objects.nonNull(date)) {
            queryBo.setStart(date.atStartOfDay());
            queryBo.setEnd(date.atTime(LocalTime.MAX));
        }
        PageHelper.startPage(queryBo.getPageNum(), queryBo.getPageSize());
        List<Ticket> list = ticketDao.select(queryBo);
        TicketList result = new TicketList();
        PageInfo<Ticket> info = new PageInfo<>(list);
        SystemUtils.configPageInfo(result, info);
        result.setList(list);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean buy(Long userId, long ticketId) {
        ticketDao.updateTicketNumber(ticketId);
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setPurchaseTime(LocalDateTime.now());
        purchaseRecord.setTicketId(ticketId);
        purchaseRecord.setUserId(userId);
        purchaseRecordDao.insert(purchaseRecord);
        return true;
    }

    @Override
    public PurchaseRecordList getPurchaseRecordList(PageRequest pageRequest, Long userId) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PurchaseRecordListItem> list = purchaseRecordDao.select(pageRequest, userId);
        PurchaseRecordList result = new PurchaseRecordList();
        PageInfo<PurchaseRecordListItem> info = new PageInfo<>(list);
        SystemUtils.configPageInfo(result, info);
        result.setList(list);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean returnTicket(Long userId, long ticketId) {
        PurchaseRecord record = purchaseRecordDao.selectOneByUserIdTicketId(userId, ticketId);
        if (Objects.isNull(record)) {
            return false;
        }
        Long recordTicketId = record.getTicketId();
        Ticket ticket = ticketDao.selectByPrimaryKey(recordTicketId);
        LocalDateTime departureTime = ticket.getDepartureTime();
        if (departureTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("火车已经发车，不可退票。");
        }
        ticket.setTicketNumber(ticket.getTicketNumber() + 1);
        ticketDao.updateByPrimaryKey(ticket);
        purchaseRecordDao.deleteByPrimaryKey(record.getId());
        return true;
    }

    @Override
    public TicketList all(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Ticket> list = ticketDao.selectAll();
        TicketList result = new TicketList();
        PageInfo<Ticket> info = new PageInfo<>(list);
        SystemUtils.configPageInfo(result, info);
        result.setList(list);
        return result;
    }
}
