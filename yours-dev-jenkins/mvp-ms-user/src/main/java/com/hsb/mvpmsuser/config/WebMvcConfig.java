package com.hsb.mvpmsuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hsb.mvpmsuser.interceptor.UserInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public UserInterceptor usernameInterceptor() {
		return new UserInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(usernameInterceptor()).excludePathPatterns("/login").excludePathPatterns("/register");
	}
	
}
