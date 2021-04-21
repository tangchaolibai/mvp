package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a add friend request.
 */
@ApiModel(description = "This entity is used to describe a add friend request.")
@Validated
@Data
public class AddFriendRequest   {
	
  @JsonProperty("userId")
  @ApiModelProperty(required = true,value = "User Id")
  private Integer userId;

  @JsonProperty("fanId")
  @ApiModelProperty(required = true,value = "Fan Id")
  private Integer fanId;

  @JsonProperty("comfirm")
  @ApiModelProperty(required = true,value = "Confirm")
  private Boolean comfirm;

}

