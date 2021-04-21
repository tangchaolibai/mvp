package com.hsb.mvpmsuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(aipInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.hsb.mvpmsuser.api"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo aipInfo() {
		return new ApiInfoBuilder()
				.title("Yours")
				.description("Yours System for payment, shopping, share, chat and Buying Tickets and so on")
				.termsOfServiceUrl("")
				.version("0.0.1")
				.build();
	}
	
}
