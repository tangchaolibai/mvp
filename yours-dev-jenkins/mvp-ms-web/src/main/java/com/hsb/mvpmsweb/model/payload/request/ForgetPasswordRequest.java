package com.hsb.mvpmsweb.model.payload.request;


import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "This entity is used to describe a Forget Password Request.")
@Data
@Validated
public class ForgetPasswordRequest {
	
	@JsonProperty
	@ApiModelProperty(required = true,value = "Mobile Phone")
	private String mobilePhone;
	
	@JsonProperty
	@ApiModelProperty(required = true,value = "Password")
	private String password;

}
