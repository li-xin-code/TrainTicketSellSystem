package com.lixin.trainticketsellsystem.service;

import com.lixin.trainticketsellsystem.model.form.LoginForm;
import com.lixin.trainticketsellsystem.model.form.RegisterForm;
import com.lixin.trainticketsellsystem.model.vo.response.LoginResult;

/**
 * 用户模块 业务接口
 *
 * @author xxx
 */
public interface AccountService {

    /**
     * 用户登录
     *
     * @param form ...
     * @return ...
     */
    LoginResult login(LoginForm form);

    /**
     * 注册
     *
     * @param form ..
     * @return ..
     */
    boolean register(RegisterForm form);

}
