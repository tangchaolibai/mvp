package com.hsb.mvpmsticket.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
@ApiModel(description = "This entity is used to describe edit user response.")
@Validated
@Data
public class BaseResponse {

	@JsonProperty("result")
	private ResponseResult result = null;
	
}
