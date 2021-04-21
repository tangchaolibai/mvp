package com.hsb.mvpmsweb.config;

import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Sets;
import com.hsb.mvpmsweb.constant.Swagger2Constants;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
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
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.protocols(Sets.newHashSet("http"))
				.tags(new Tag(Swagger2Constants.TAG_NM_USER, Swagger2Constants.TAG_DESC_USER),
					  new Tag(Swagger2Constants.TAG_NM_SHARE, Swagger2Constants.TAG_DESC_SHARE),
					  new Tag(Swagger2Constants.TAG_NM_ASSISTANT, Swagger2Constants.TAG_DESC_ASSISTANT),
					  new Tag(Swagger2Constants.TAG_NM_MESSAGE, Swagger2Constants.TAG_DESC_MESSAGE),
					  new Tag(Swagger2Constants.TAG_NM_TICKET, Swagger2Constants.TAG_DESC_TICKET)
				)
				.directModelSubstitute(LocalTime.class, String.class)
				.select()
				.apis(RequestHandlerSelectors.basePackage(Swagger2Constants.API_BASE_PKG))	
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(Swagger2Constants.API_INFO_TITLE)
				.description(Swagger2Constants.API_INFO_DESC)
				.termsOfServiceUrl("")
				.version(Swagger2Constants.API_INFO_VER)
				.build();
	}
	
}
