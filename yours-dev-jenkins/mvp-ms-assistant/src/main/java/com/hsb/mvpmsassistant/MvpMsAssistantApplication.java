package com.hsb.mvpmsassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hsb.mvpmsassistant.mapper")
public class MvpMsAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvpMsAssistantApplication.class, args);
    }

}
