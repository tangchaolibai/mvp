package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.hsb.mvpmsweb.model.payload.OTPResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe  generate/verify OTP response.")
@Validated
@Data
public class OTPResponse extends BaseResponse {
	
	public OTPResponse(ResponseResult defaultSuccessResponse, OTPResponseData data) {
		super(defaultSuccessResponse);
		this.data = data;
	}

	private OTPResponseData data;

}
