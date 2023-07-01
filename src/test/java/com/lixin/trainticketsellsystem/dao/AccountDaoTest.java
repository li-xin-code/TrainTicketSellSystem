package com.lixin.trainticketsellsystem.dao;

import com.lixin.trainticketsellsystem.common.SystemUtils;
import com.lixin.trainticketsellsystem.model.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lixin
 * @date 2023/7/1 23:30
 */
@SpringBootTest
class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void insertTest() {
        Account account = new Account();
        account.setAccountNo("admin");
        account.setAccountType(0);
        account.setPassword(SystemUtils.passwordEncode("admin"));
        accountDao.insertAccount(account);
    }
}