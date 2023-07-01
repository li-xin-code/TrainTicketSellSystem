package com.lixin.trainticketsellsystem.service;

import com.lixin.trainticketsellsystem.model.entity.Ticket;
import com.lixin.trainticketsellsystem.model.vo.request.PageRequest;
import com.lixin.trainticketsellsystem.model.vo.request.TicketQuery;
import com.lixin.trainticketsellsystem.model.vo.response.PurchaseRecordList;
import com.lixin.trainticketsellsystem.model.vo.response.TicketList;

/**
 * @author lixin
 * @date 2023/7/1 22:05
 */
public interface TicketService {
    /**
     * add
     *
     * @param ticket ...
     * @return boolean
     * @date 2023/7/1 22:19
     **/
    boolean add(Ticket ticket);

    /**
     * del
     *
     * @param id ...
     * @return boolean
     * @date 2023/7/1 22:19
     **/
    boolean del(long id);

    /**
     * list
     *
     * @param query ...
     * @return com.lixin.trainticketsellsystem.model.vo.response.TicketList
     * @date 2023/7/1 22:19
     **/
    TicketList list(TicketQuery query);

    /**
     * buy
     *
     * @param userId   ...
     * @param ticketId ...
     * @return boolean
     * @date 2023/7/1 23:00
     **/
    boolean buy(Long userId, long ticketId);

    /**
     * getPurchaseRecordList
     *
     * @param pageRequest ...
     * @param userId      ...
     * @return com.lixin.trainticketsellsystem.model.vo.response.PurchaseRecordList
     * @date 2023/7/1 23:13
     **/
    PurchaseRecordList getPurchaseRecordList(PageRequest pageRequest, Long userId);

    /**
     * returnTicket
     *
     * @param userId   ...
     * @param ticketId ...
     * @return boolean
     * @date 2023/7/2 00:29
     **/
    boolean returnTicket(Long userId, long ticketId);

    /**
     * all
     *
     * @param pageRequest ...
     * @return com.lixin.trainticketsellsystem.model.vo.response.TicketList
     * @date 2023/7/2 00:46
     **/
    TicketList all(PageRequest pageRequest);
}
