package com.hsb.mvpmsuser.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.hsb.mvpmsuser.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;

/**
 * This entity is data initialization response.
 */
@ApiModel(description = "This entity is data initialization response.")
@Validated
public class InitDataResponse extends ResponseBody {
	
	public InitDataResponse(ResponseResult result) {
		super(result);
	}
	
}
