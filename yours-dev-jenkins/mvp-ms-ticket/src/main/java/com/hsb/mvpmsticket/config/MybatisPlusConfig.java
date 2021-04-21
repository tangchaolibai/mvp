package com.hsb.mvpmsticket.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;

@EnableTransactionManagement
@Configuration
@MapperScan("com.hsb.mvpmsticket.mapper")
public class MybatisPlusConfig {
	
	@Bean
    public PaginationInterceptor paginationInterceptor() {
		 PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
	        // 设置最大单页限制数量，默认 500 条，-1 不受限制
	         paginationInterceptor.setLimit(20);
	        // 开启 count 的 join 优化,只针对部分 left join
	        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
	        return paginationInterceptor;
    }

}
