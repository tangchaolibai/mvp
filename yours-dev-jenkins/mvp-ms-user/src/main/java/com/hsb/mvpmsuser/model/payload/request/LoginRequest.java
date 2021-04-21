package com.hsb.mvpmsuser.model.payload.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to describe an Login Request.
 */
@ApiModel(description = "This entity is used to describe an Login Request.")
@Data
@Accessors(fluent = true)
public class LoginRequest {
	
  @JsonProperty("mobilePhone")
  @ApiModelProperty(required = true, value = "Mobile Phone for logining yours system")
  @NotBlank
  private String mobilePhone = null;
  
  @JsonProperty("areaCode")
  @NotBlank
  @ApiModelProperty(required = true, value = "Area Code for Logining yours system")
  private String areaCode = null;

  @JsonProperty("password")
  @ApiModelProperty(required = true, value = "Password for logining yours system")
  @NotBlank
  private String password = null;

}

