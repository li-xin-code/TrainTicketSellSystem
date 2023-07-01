package com.lixin.trainticketsellsystem.common;

/**
 * @author lixin
 */
public interface TokenManager<T> {

    /**
     * Generate a token.
     *
     * @param data ...
     * @return token
     */
    String getToken(T data);

    /**
     * get data for token.
     *
     * @param token ...
     * @return data
     */
    T getData(String token);

    /**
     * remove token.
     *
     * @param token ...
     */
    void removeToken(String token);

}
