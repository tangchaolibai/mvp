package com.hsb.mvpmsshare.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a FollowRequest.")
@Validated
public class FollowRequest {

	@JsonProperty("userId")
	private Integer userId;
	@JsonProperty("fanId")
	private Integer fanId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFanId() {
		return fanId;
	}
	public void setFanId(Integer fanId) {
		this.fanId = fanId;
	}
	
	
}
