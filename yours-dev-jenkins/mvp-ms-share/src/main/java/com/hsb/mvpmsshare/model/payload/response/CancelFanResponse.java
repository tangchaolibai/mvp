package com.hsb.mvpmsshare.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a Cancel Fan Response.")
@Validated
public class CancelFanResponse {

	@JsonProperty("result")
	private ResponseResult result = null;

	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}
	
}
