package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.UserInfoResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
@ApiModel(description = "This entity is used to describe find user response.")
@Validated
@Data
public class FindUserResponse extends BaseResponse {
	@JsonProperty("data")
	private UserInfoResponseData data = null;

}
