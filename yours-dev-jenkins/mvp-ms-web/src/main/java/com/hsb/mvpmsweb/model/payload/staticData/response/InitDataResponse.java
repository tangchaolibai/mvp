package com.hsb.mvpmsweb.model.payload.staticData.response;

import org.springframework.validation.annotation.Validated;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

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
