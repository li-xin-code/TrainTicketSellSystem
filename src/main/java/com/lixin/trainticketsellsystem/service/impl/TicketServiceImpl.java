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
import com.lixin.trainticketsellsystem.model.vo.response.TicketList;
import com.lixin.trainticketsellsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        return false;
    }

    @Override
    public TicketList list(TicketQuery query) {
        TicketQueryBo queryBo = new TicketQueryBo();
        BeanUtils.copyProperties(query, queryBo);
        queryBo.setStart(query.getDate().atStartOfDay());
        queryBo.setEnd(query.getDate().atTime(LocalTime.MAX));
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
        List<PurchaseRecord> list = purchaseRecordDao.select(pageRequest, userId);
        PurchaseRecordList result = new PurchaseRecordList();
        PageInfo<PurchaseRecord> info = new PageInfo<>(list);
        SystemUtils.configPageInfo(result, info);
        result.setList(list);
        return result;
    }
}
