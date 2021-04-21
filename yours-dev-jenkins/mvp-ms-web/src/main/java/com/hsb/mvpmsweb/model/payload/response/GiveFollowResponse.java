package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a give Follow Response.")
@Validated
public class GiveFollowResponse {

	@JsonProperty("result")
	private ResponseResult result = null;

	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}
	
}
