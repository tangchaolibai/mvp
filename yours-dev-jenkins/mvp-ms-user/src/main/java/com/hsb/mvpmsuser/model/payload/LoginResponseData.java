package com.hsb.mvpmsuser.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe an Login response data.")
@Data
@Accessors(fluent = true)
public class LoginResponseData {
	
	@JsonProperty("userInfoResponseData")
	private UserInfoResponseData userInfoResponseData = null;
	
	@JsonProperty("token")
	private String token = null;
}
