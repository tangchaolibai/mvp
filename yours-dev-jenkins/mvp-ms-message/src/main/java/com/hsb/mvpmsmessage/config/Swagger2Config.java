package com.hsb.mvpmsmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;
import com.hsb.mvpmsmessage.constant.Swagger2Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.protocols(Sets.newHashSet("http"))
				.tags(new Tag(Swagger2Constants.TAG_MESSAGE, Swagger2Constants.TAG_DESC_MESSAGE))
				.select()
				.apis(RequestHandlerSelectors.basePackage(Swagger2Constants.API_BASE_PKG))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(Swagger2Constants.API_INFO_TITLE)
				.description(Swagger2Constants.API_INFO_DESC)
				.version(Swagger2Constants.API_INFO_VER)
				.build();
	}
	
}
