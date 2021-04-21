package com.hsb.mvpmsuser.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
@MapperScan("com.hsb.mvpmsuser.mapper")
public class MybatisConfig {
	
	@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
	
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
	    return new OptimisticLockerInterceptor();
	}
	
}
