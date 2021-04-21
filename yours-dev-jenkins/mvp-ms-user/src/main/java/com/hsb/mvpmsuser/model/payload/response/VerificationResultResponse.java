package com.hsb.mvpmsuser.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsuser.model.payload.VerificationCodeResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe find user response.")
@Validated
@Data
public class VerificationResultResponse extends BaseResponse {
	
	@JsonProperty("data")
	private VerificationCodeResponseData data = null;
}
