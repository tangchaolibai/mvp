package com.hsb.mvpmsuser.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe an User response data.")
@Data
@Accessors(fluent = true)
public class VerificationCodeResponseData {
	
	@JsonProperty("phoneNumber")
	private String phoneNumber = null;
	
	@JsonProperty("verificationCode")
	private String verificationCode = null;
	
	@JsonProperty("result")
	private Boolean result = null;

}
