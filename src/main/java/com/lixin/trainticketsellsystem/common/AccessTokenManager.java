package com.lixin.trainticketsellsystem.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lixin
 */
@Service
public class AccessTokenManager implements TokenManager<Long> {

    /**
     * 本地缓存池
     */
    private final Cache<String, Long> tokenCache = CacheBuilder.newBuilder()
            .maximumSize(1000L)
            .expireAfterAccess(20, TimeUnit.MINUTES)
            .build();

    private String generateToken() {
        String random = String.valueOf((System.currentTimeMillis() +
                ThreadLocalRandom.current().nextInt(8888)));
        return DigestUtils.md5DigestAsHex(random.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getToken(Long data) {
        String token = generateToken();
        tokenCache.put(token, data);
        return token;
    }

    @Override
    public Long getData(String token) {
        Long userId = tokenCache.getIfPresent(token);
        if (Objects.isNull(userId)) {
            throw new RuntimeException("令牌过期");
        }
        return userId;
    }

    @Override
    public void removeToken(String token) {
        tokenCache.invalidate(token);
    }
}
