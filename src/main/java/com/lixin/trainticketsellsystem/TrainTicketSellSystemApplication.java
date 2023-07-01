package com.lixin.trainticketsellsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lixin
 */
@MapperScan(value = {"com.lixin.trainticketsellsystem.dao"})
@SpringBootApplication
public class TrainTicketSellSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainTicketSellSystemApplication.class, args);
    }

}
