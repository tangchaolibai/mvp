package com.hsb.mvpmsshare.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a RecentlyUpdatedUserRequest.")
@Validated
public class RecentlyUpdatedUserRequest {

	@JsonProperty("userId")
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
