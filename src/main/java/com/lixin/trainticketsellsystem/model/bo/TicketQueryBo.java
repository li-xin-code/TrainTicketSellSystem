package com.lixin.trainticketsellsystem.model.bo;

import com.lixin.trainticketsellsystem.model.vo.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author lixin
 * @date 2023/7/1 22:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TicketQueryBo extends PageRequest {
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
    private LocalDateTime start;
    private LocalDateTime end;
}
