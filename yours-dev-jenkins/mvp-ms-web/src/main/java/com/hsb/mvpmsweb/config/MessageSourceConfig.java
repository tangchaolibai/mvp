package com.hsb.mvpmsweb.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.hsb.mvpmsweb.constant.BaseConstants;

@Configuration
public class MessageSourceConfig {

	@Bean
	public MessageSource exceptionMessageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:/messages/exception-messages");
	    messageSource.setDefaultEncoding(BaseConstants.ENCODING_UTF8);
	    return messageSource;
	}
	
	@Bean
	public MessageSource validationMessageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setBasename("classpath:/messages/validation-messages");
	    messageSource.setDefaultEncoding(BaseConstants.ENCODING_UTF8);
	    return messageSource;
	}
	
}
