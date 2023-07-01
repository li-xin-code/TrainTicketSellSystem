package com.lixin.trainticketsellsystem.dao;

import com.lixin.trainticketsellsystem.model.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lixin
 */
@Repository
public interface AccountDao {

    /**
     * 根据账号获取用户信息
     *
     * @param accountNo 账号
     * @return ...
     */
    Account selectByAccountNo(String accountNo);

    /**
     * 保存账号信息
     *
     * @param account account
     * @return row
     */
    int insertAccount(Account account);

    /**
     * 修改密码
     *
     * @param account ...
     * @return ...
     */
    int updatePassword(@Param("account") Account account);

    /**
     * ...
     *
     * @param userId ...
     * @return ...
     */
    Account selectById(@Param("userId") Long userId);

}
