package com.lixin.trainticketsellsystem.model.vo.response;

import com.lixin.trainticketsellsystem.model.entity.Ticket;
import com.lixin.trainticketsellsystem.model.vo.response.base.PageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lixin
 * @date 2023/7/1 22:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TicketList extends PageResponse {
    private List<Ticket> list;
}
