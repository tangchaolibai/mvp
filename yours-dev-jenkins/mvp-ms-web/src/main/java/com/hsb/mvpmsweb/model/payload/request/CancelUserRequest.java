package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe an cancel user Request.")
@Data
@Accessors(fluent = true)
public class CancelUserRequest {
	
	@JsonProperty("userId")
	private Integer userId = null;
	
	@JsonProperty("password")
	private String password = null;
	
	@JsonProperty("mobilePhone")
	private String mobilePhone = null;
	
	@JsonProperty("otp")
	private String otp = null;

}
