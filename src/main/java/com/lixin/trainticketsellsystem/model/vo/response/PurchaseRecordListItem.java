package com.lixin.trainticketsellsystem.model.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lixin
 * @date 2023/7/2 00:10
 */
@Data
public class PurchaseRecordListItem {
    /**
     * 火车票Id
     */
    private Long ticketId;

    /**
     * 购买时间
     */
    private LocalDateTime purchaseTime;

    /**
     * 火车班次
     */
    private String trainNumber;

    /**
     * 发车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    /**
     * 到达时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;

    /**
     * 上车站点
     */
    private String boardingStation;

    /**
     * 下车站点
     */
    private String disembarkingStation;

    /**
     * 价格
     */
    private BigDecimal price;
}
