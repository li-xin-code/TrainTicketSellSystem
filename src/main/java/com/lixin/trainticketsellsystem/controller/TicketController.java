package com.lixin.trainticketsellsystem.controller;

import com.lixin.trainticketsellsystem.common.ResultUtils;
import com.lixin.trainticketsellsystem.common.TokenManager;
import com.lixin.trainticketsellsystem.common.result.DataResult;
import com.lixin.trainticketsellsystem.common.result.NoDataResult;
import com.lixin.trainticketsellsystem.model.entity.Ticket;
import com.lixin.trainticketsellsystem.model.vo.request.PageRequest;
import com.lixin.trainticketsellsystem.model.vo.request.TicketQuery;
import com.lixin.trainticketsellsystem.model.vo.response.PurchaseRecordList;
import com.lixin.trainticketsellsystem.model.vo.response.TicketList;
import com.lixin.trainticketsellsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author lixin
 * @date 2023/7/1 21:49
 */
@RequestMapping("/ticket")
@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TokenManager<Long> tokenManager;

    @PostMapping("/add")
    public NoDataResult add(@RequestBody Ticket ticket) {
        return ResultUtils.auto(ticketService.add(ticket));
    }

    @PostMapping("/del/{id}")
    public NoDataResult del(@PathVariable long id) {
        return ResultUtils.auto(ticketService.del(id));
    }

    @GetMapping("/list")
    public DataResult<TicketList> list(TicketQuery query) {
        return ResultUtils.success(ticketService.list(query));
    }

    @GetMapping("/all")
    public DataResult<TicketList> listAll(PageRequest pageRequest) {
        return ResultUtils.success(ticketService.all(pageRequest));
    }

    @PostMapping("/buy/{ticketId}")
    public NoDataResult buy(@RequestHeader String token, @PathVariable long ticketId) {
        Long userId = Optional.ofNullable(tokenManager.getData(token))
                .orElseThrow(() -> new RuntimeException("Token invalid."));
        return ResultUtils.auto(ticketService.buy(userId, ticketId));
    }

    /**
     * 购票记录
     *
     * @param pageRequest ...
     * @param token       ...
     * @return ...
     */
    @GetMapping("/purchase-record")
    public DataResult<PurchaseRecordList> purchaseRecordList(PageRequest pageRequest, @RequestHeader String token) {
        Long userId = Optional.ofNullable(tokenManager.getData(token))
                .orElseThrow(() -> new RuntimeException("Token invalid."));
        return ResultUtils.success(ticketService.getPurchaseRecordList(pageRequest, userId));

    }

    /**
     * 退票
     *
     * @param ticketId ...
     * @param token    ...
     * @return ...
     */
    @PutMapping("/return-ticket/{ticketId}")
    public NoDataResult returnTicket(@PathVariable long ticketId, @RequestHeader String token) {
        Long userId = Optional.ofNullable(tokenManager.getData(token))
                .orElseThrow(() -> new RuntimeException("Token invalid."));
        return ResultUtils.auto(ticketService.returnTicket(userId, ticketId));
    }
}
