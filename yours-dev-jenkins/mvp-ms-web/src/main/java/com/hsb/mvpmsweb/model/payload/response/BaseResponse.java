package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@ApiModel(description = "This entity is used to describe edit user response.")
@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

	@JsonProperty("result")
	private ResponseResult result = null;
	
}
