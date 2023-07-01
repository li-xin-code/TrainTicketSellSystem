package com.lixin.trainticketsellsystem.dao;


import com.lixin.trainticketsellsystem.model.bo.TicketQueryBo;
import com.lixin.trainticketsellsystem.model.entity.Ticket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author lixin
 * @date 2023-07-01 21:34:49
 */
@Repository
public interface TicketDao {

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
    int insert(Ticket record);

    /**
     * insertSelective
     *
     * @param record ...
     * @return int
     **/
    int insertSelective(Ticket record);

    /**
     * selectByPrimaryKey
     *
     * @param id ...
     * @return Ticket
     **/
    Ticket selectByPrimaryKey(Long id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record ...
     * @return int
     **/
    int updateByPrimaryKeySelective(Ticket record);

    /**
     * updateByPrimaryKey
     *
     * @param record ...
     * @return int
     **/
    int updateByPrimaryKey(Ticket record);

    /**
     * select
     *
     * @param queryBo ...
     * @return java.util.List<com.lixin.trainticketsellsystem.model.entity.Ticket>
     * @date 2023/7/1 22:28
     **/
    List<Ticket> select(@Param("query") TicketQueryBo queryBo);

    /**
     * updateTicketNumber
     *
     * @param ticketId ...
     * @return int
     * @date 2023/7/1 23:06
     **/
    int updateTicketNumber(@Param("ticketId") long ticketId);

    /**
     * selectByIds
     *
     * @param ticketIds ...
     * @return java.util.List<com.lixin.trainticketsellsystem.model.entity.Ticket>
     * @date 2023/7/2 00:13
     **/
    List<Ticket> selectByIds(@Param("ticketIds") Set<Long> ticketIds);

    /**
     * selectAll
     *
     * @return java.util.List<com.lixin.trainticketsellsystem.model.entity.Ticket>
     * @date 2023/7/2 00:48
     **/
    List<Ticket> selectAll();

}
