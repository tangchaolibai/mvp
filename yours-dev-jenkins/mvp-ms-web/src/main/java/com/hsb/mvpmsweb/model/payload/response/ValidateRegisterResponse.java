package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.ValidateRegisterResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to validate register response.")
@Validated
@Data
public class ValidateRegisterResponse extends BaseResponse {
	
	public ValidateRegisterResponse(ResponseResult defaultSuccessResponse, ValidateRegisterResponseData data) {
		super(defaultSuccessResponse);
		this.data = data;
	}

	private ValidateRegisterResponseData data;

}
