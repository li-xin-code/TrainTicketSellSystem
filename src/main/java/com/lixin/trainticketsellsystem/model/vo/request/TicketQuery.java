package com.lixin.trainticketsellsystem.model.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author lixin
 * @date 2023/7/1 22:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TicketQuery extends PageRequest {
    /**
     * 上车站点
     */
    @NotBlank(message = "[上车站点]不能为空")
    private String boardingStation;

    /**
     * 下车站点
     */
    @NotBlank(message = "[下车站点]不能为空")
    private String disembarkingStation;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
