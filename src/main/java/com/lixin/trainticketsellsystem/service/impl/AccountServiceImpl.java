package com.lixin.trainticketsellsystem.service.impl;

import com.lixin.trainticketsellsystem.common.TokenManager;
import com.lixin.trainticketsellsystem.dao.AccountDao;
import com.lixin.trainticketsellsystem.model.entity.Account;
import com.lixin.trainticketsellsystem.model.form.LoginForm;
import com.lixin.trainticketsellsystem.model.form.RegisterForm;
import com.lixin.trainticketsellsystem.model.vo.response.LoginResult;
import com.lixin.trainticketsellsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.lixin.trainticketsellsystem.common.SystemUtils.passwordEncode;


/**
 * 用户模块 业务实现类
 *
 * @author xxx
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    private final TokenManager<Long> tokenManager;

    /**
     * 根据用户和密码登录
     * 判断账户密码是否一致
     *
     * @param form ...
     * @return ...
     */
    @Override
    public LoginResult login(LoginForm form) {
        Account account = accountDao.selectByAccountNo(form.getAccount());
        if (Objects.isNull(account)) {
            throw new RuntimeException("账号密码错误");
        }
        String password = passwordEncode(form.getPassword());
        if (password.equals(account.getPassword())) {
            LoginResult result = new LoginResult();
            String token = tokenManager.getToken(account.getId());
            result.setToken(token);
            result.setType(account.getAccountType());
            return result;
        }
        throw new RuntimeException("账号密码错误");
    }

    @Override
    public boolean register(RegisterForm form) {
        Account account = new Account();
        account.setAccountNo(form.getAccountNo());
        account.setPassword(passwordEncode(form.getPassword()));
        account.setAccountType(1);
        accountDao.insertAccount(account);
        return true;
    }


}
