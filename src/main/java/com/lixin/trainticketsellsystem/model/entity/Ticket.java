package com.lixin.trainticketsellsystem.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 火车票表
 *
 * @author lixin
 */
@Data
public class Ticket implements Serializable {

    private static final long serialVersionUID = -3376047916978308511L;
    private Long id;
    /**
     * 火车班次
     */

    @NotBlank(message = "[火车班次]不能为空")
    @Size(max = 13, message = "编码长度不能超过13")
    @Length(max = 13, message = "编码长度不能超过13")
    private String trainNumber;
    /**
     * 发车时间
     */
    @NotNull(message = "[发车时间]不能为空")
    private LocalDateTime departureTime;
    /**
     * 到达时间
     */
    @NotNull(message = "[到达时间]不能为空")
    private LocalDateTime arrivalTime;

    /**
     * 上车站点
     */
    @NotBlank(message = "[上车站点]不能为空")
    @Size(max = 13, message = "编码长度不能超过13")
    @Length(max = 13, message = "编码长度不能超过13")
    private String boardingStation;

    /**
     * 下车站点
     */
    @NotBlank(message = "[下车站点]不能为空")
    @Size(max = 13, message = "编码长度不能超过13")
    @Length(max = 13, message = "编码长度不能超过13")
    private String disembarkingStation;

    /**
     * 价格
     */
    @NotNull(message = "[价格]不能为空")
    private BigDecimal price;

    /**
     * 数量
     */
    @NotNull(message = "[数量]不能为空")
    private Integer ticketNumber;

}
