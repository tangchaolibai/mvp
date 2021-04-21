package com.hsb.mvpmsweb.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class WebDatabaseConfig {

	public WebDatabaseConfig() {
        super();
    }

	@Bean
	@ConfigurationProperties(prefix = "spring.web-datasource")
	public DataSource messageDataSource() {
		return DataSourceBuilder.create().build();
	}

}
