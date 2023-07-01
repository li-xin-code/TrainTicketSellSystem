package com.lixin.trainticketsellsystem.dao;


import com.lixin.trainticketsellsystem.model.entity.PurchaseRecord;
import com.lixin.trainticketsellsystem.model.vo.request.PageRequest;
import com.lixin.trainticketsellsystem.model.vo.response.PurchaseRecordListItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lixin
 * @date 2023-07-01 21:34:49
 */
@Repository
public interface PurchaseRecordDao {

    /**
     * deleteByPrimaryKey
     *
     * @param id ...
     * @return int
     **/
    int deleteByPrimaryKey(Long id);

    /**
     * insert
     *
     * @param record ...
     * @return int
     **/
    int insert(PurchaseRecord record);

    /**
     * insertSelective
     *
     * @param record ...
     * @return int
     **/
    int insertSelective(PurchaseRecord record);

    /**
     * selectByPrimaryKey
     *
     * @param id ...
     * @return PurchaseRecord
     **/
    PurchaseRecord selectByPrimaryKey(Long id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record ...
     * @return int
     **/
    int updateByPrimaryKeySelective(PurchaseRecord record);

    /**
     * updateByPrimaryKey
     *
     * @param record ...
     * @return int
     **/
    int updateByPrimaryKey(PurchaseRecord record);

    /**
     * select
     *
     * @param pageRequest ...
     * @param userId      ...
     * @return ...
     * @date 2023/7/1 23:15
     **/
    List<PurchaseRecordListItem> select(@Param("pageRequest") PageRequest pageRequest, @Param("userId") Long userId);

    /**
     * selectOneByUserIdTicketId
     *
     * @param userId   ...
     * @param ticketId ...
     * @return com.lixin.trainticketsellsystem.model.entity.PurchaseRecord
     * @date 2023/7/2 00:32
     **/
    PurchaseRecord selectOneByUserIdTicketId(@Param("userId") long userId, @Param("ticketId") long ticketId);
}
