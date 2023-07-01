package com.lixin.trainticketsellsystem.config.interceptor;


import com.lixin.trainticketsellsystem.common.TokenManager;
import com.lixin.trainticketsellsystem.common.annotation.LoginRequired;
import com.lixin.trainticketsellsystem.dao.AccountDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lixin
 */
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager<Long> tokenManager;
    private final AccountDao accountDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();
        Class<?> clazz = method.getDeclaringClass();
        boolean classAnnotation = clazz.isAnnotationPresent(LoginRequired.class);
        boolean methodAnnotation = method.isAnnotationPresent(LoginRequired.class);
        // 如果 类 和 方法 上都没有 LoginRequired 直接通过
        if (!classAnnotation && !methodAnnotation) {
            return true;
        }

        LoginRequired loginRequired = (methodAnnotation ? method : clazz).getAnnotation(LoginRequired.class);

        if (!loginRequired.required()) {
            return true;
        }

        String token = Optional.ofNullable(request.getHeader("token"))
                .orElseThrow(() -> new RuntimeException("Login required."));

        Optional.of(tokenManager.getData(token))
                .map(accountDao::selectById)
                .map(account -> {
                    Integer role = account.getAccountType();
                    List<Integer> roleList = Arrays.stream(loginRequired.role()).boxed()
                            .collect(Collectors.toList());
                    if (roleList.contains(role)) {
                        return true;
                    } else {
                        throw new RuntimeException("禁止访问");
                    }
                });
        return true;
    }
}
