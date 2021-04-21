package com.hsb.mvpmsweb.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "This entity is used to describe a RecentlyUpdatedUserRequest.")
@Validated
@Data
public class RecentlyUpdatedUserRequest {

	@JsonProperty("userId")
	@ApiModelProperty(required = true,value = "User Id")
	private Integer userId;

}
