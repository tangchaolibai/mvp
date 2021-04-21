package com.hsb.mvpmsuser.model.payload.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to describe an Register Request.
 */
@ApiModel(description = "This entity is used to describe an Register Request.")
@Data
@Accessors(fluent = true)
public class RegisterRequest   {
  @JsonProperty("mobilePhone")
  @NotBlank
  @ApiModelProperty(required = true, value = "Mobile Phone for Register yours system")
  private String mobilePhone = null;

  @JsonProperty("password")
  @NotBlank
  @ApiModelProperty(required = true, value = "Password for Register yours system")
  private String password = null;
  
  @JsonProperty("areaCode")
  @NotBlank
  @ApiModelProperty(required = true, value = "Area Code for Register yours system")
  private String areaCode = null;

}

