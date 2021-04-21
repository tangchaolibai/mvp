package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe an verify otp Request.")
@Data
@Accessors(fluent = true)
public class VerifyOtpRequest {
	
	@ApiModelProperty(required = true, value = "Phone Number")
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	
	@ApiModelProperty(required = true, value = "Input Check Code")
	@JsonProperty("inputCheckCode")
	private String inputCheckCode;

}
