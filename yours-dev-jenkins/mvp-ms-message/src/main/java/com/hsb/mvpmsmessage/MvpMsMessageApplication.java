package com.hsb.mvpmsmessage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan(basePackages = {"com.hsb.mvpmsmessage.mapper"})
public class MvpMsMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvpMsMessageApplication.class, args);
    }

}
