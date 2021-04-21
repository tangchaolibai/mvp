package com.hsb.mvpmsweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hsb.mvpmsweb.api.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}
	
    /**
     * Register Interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/register");
    }

    /**
     * cros support
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
                .maxAge(3600 * 24 * 30);
    }

}
