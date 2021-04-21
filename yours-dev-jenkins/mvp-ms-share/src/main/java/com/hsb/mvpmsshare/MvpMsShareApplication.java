package com.hsb.mvpmsshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan(basePackages = {"com.hsb.mvpmsshare.dao"})
public class MvpMsShareApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MvpMsShareApplication.class, args);
	}
	
}
