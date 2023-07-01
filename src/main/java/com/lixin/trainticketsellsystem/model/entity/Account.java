package com.lixin.trainticketsellsystem.model.entity;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author xxx
 */
@Data
public class Account {
    private Long id;
    private String accountNo;
    private String password;
    private Integer accountType;
}
