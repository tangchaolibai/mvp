package com.hsb.mvpmsweb.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hsb.mvpmsweb.constant.BaseConstants;

@Configuration
public class DateSerializerConfig {
	
	// LocalDate Format
	@Bean 
    public LocalDateSerializer localDateDeserializer() { 
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(BaseConstants.LOCAL_DATE_PATTERN)); 
    }
	
	@Bean 
    public Jackson2ObjectMapperBuilderCustomizer customizerLocalDate() { 
    	return builder -> builder.serializerByType(LocalDate.class, localDateDeserializer()); 
    }
	
	// LocalDateTime Format
	@Bean 
    public LocalDateTimeSerializer localDateTimeDeserializer() { 
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(BaseConstants.LOCAL_DATE_TIME_PATTERN)); 
    }
      
    @Bean 
    public Jackson2ObjectMapperBuilderCustomizer customizerLocalDateTime() { 
    	return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer()); 
    }
 
}