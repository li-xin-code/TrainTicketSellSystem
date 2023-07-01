package com.lixin.trainticketsellsystem.model.vo.response;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class LoginResult {
    private String token;
    private Integer type;
}
