package com.lixin.trainticketsellsystem.controller;

import com.lixin.trainticketsellsystem.common.ResultUtils;
import com.lixin.trainticketsellsystem.common.result.DataResult;
import com.lixin.trainticketsellsystem.common.result.NoDataResult;
import com.lixin.trainticketsellsystem.model.form.LoginForm;
import com.lixin.trainticketsellsystem.model.form.RegisterForm;
import com.lixin.trainticketsellsystem.model.vo.response.LoginResult;
import com.lixin.trainticketsellsystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixin
 * @date 2023/7/1 21:49
 */
@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/login")
    public DataResult<LoginResult> login(@RequestBody LoginForm form) {
        return ResultUtils.success(accountService.login(form));
    }

    @PostMapping("/register")
    public NoDataResult login(@RequestBody RegisterForm form) {
        boolean register = accountService.register(form);
        return register ? ResultUtils.success() : ResultUtils.fail("注册失败");
    }
}
