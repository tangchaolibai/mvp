package com.hsb.mvpmsticket.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hsb.mvpmsticket.interceptor.HttpServletFilter;

@Configuration
public class RequestParamFilterConfig {
	
	@Bean
    public FilterRegistrationBean<Filter> webAuthFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(httpServletFilter());
        registration.setName("AddCityFilter");
        registration.addUrlPatterns("/cinemas");
//        registration.addInitParameter("excludeUrls", "/web/login");
        registration.setOrder(0);
        return registration;
    }

    @Bean
    public Filter httpServletFilter() {
        return new HttpServletFilter();
    }

}
