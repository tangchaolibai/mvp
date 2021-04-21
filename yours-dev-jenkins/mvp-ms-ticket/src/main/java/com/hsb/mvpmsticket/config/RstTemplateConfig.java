package com.hsb.mvpmsticket.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RstTemplateConfig {
	
	 private Integer connectionTimeout = 15;
	 
	 private Integer readTimeout = 15;
	 
	@Bean
	public RestTemplate registerTemplate() {
		RestTemplate restTemplate = new RestTemplate(getFactory());
		// 这个地方需要配置消息转换器，不然收到消息后转换会出现异常
		restTemplate.setMessageConverters(getConverts());
		return restTemplate;
	}
	
	private SimpleClientHttpRequestFactory getFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }
	
	@SuppressWarnings("serial")
	private List<HttpMessageConverter<?>> getConverts() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        // String转换器
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            //配置text/plain和text/html类型的数据都转成String
            add(new MediaType("text", "plain", Charset.forName("UTF-8")));
            add(MediaType.TEXT_HTML);
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        messageConverters.add(stringConvert);
        return messageConverters;
    }
}
