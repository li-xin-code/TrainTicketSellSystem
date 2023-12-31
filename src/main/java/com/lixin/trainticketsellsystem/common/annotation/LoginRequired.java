package com.lixin.trainticketsellsystem.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.lixin.trainticketsellsystem.common.constant.AccountConstant.ADMIN_CODE;
import static com.lixin.trainticketsellsystem.common.constant.AccountConstant.NORMAL_CODE;

/**
 * @author lixin
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

    /**
     * 是否必须登陆
     *
     * @return ...
     */
    boolean required() default true;

    /**
     * 允许角色列表
     *
     * @return ...
     */
    int[] role() default {ADMIN_CODE, NORMAL_CODE};
}
