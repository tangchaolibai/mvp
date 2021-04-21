package com.hsb.mvpmsticket.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hsb.mvpmsticket.model.payload.ExceptionResponse;
import com.hsb.mvpmsticket.model.payload.response.BaseResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses(value = { 
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class), 
		@ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
public @interface ResponseConfig {
	
	ApiResponse value() default @ApiResponse(code = 200, message = "OK", response = BaseResponse.class);

}
