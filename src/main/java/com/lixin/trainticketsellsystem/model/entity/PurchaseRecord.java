package com.lixin.trainticketsellsystem.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 购买记录表
 *
 * @author lixin
 */
@Data
public class PurchaseRecord implements Serializable {

    private static final long serialVersionUID = 6789713284798894677L;
    /**
     *
     */
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 火车票Id
     */
    private Long ticketId;

    /**
     * 购买时间
     */
    private LocalDateTime purchaseTime;

}
