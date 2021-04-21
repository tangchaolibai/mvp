package com.hsb.mvpmsuser.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsuser.model.payload.LoginResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is used to describe an Critical Notice response.
 */
@ApiModel(description = "This entity is used to describe an Critical Notice response.")
@Validated
@Data
public class LoginResponse extends BaseResponse {
	
  @JsonProperty("data")
  private LoginResponseData data = null;

}

