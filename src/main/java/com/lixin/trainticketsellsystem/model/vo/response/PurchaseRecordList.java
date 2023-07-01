package com.lixin.trainticketsellsystem.model.vo.response;

import com.lixin.trainticketsellsystem.model.entity.PurchaseRecord;
import com.lixin.trainticketsellsystem.model.vo.response.base.PageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lixin
 * @date 2023/7/1 23:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseRecordList extends PageResponse {
    private List<PurchaseRecord> list;
}
