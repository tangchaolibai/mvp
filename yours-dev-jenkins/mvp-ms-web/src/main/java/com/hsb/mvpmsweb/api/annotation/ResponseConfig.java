package com.hsb.mvpmsweb.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hsb.mvpmsweb.constant.HttpStatusCodeConstants;
import com.hsb.mvpmsweb.model.payload.ExceptionResponse;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses(value = { 
		@ApiResponse(code = 201, message = HttpStatusCodeConstants.MSG_201),
		@ApiResponse(code = 400, message = HttpStatusCodeConstants.MSG_400, response = ExceptionResponse.class), 
		@ApiResponse(code = 500, message = HttpStatusCodeConstants.MSG_500, response = ExceptionResponse.class)})
public @interface ResponseConfig {
	
	ApiResponse value() default @ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = BaseResponse.class);

}
