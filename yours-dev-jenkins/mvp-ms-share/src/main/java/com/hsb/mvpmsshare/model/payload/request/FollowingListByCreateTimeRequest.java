package com.hsb.mvpmsshare.model.payload.request;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a FollowingListByCreateTimeRequest.")
@Validated
public class FollowingListByCreateTimeRequest {

	@JsonProperty("userId")
	private Integer userId = null;
	
	@JsonProperty("refreshTime")
	private LocalDateTime refreshTime = null;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(LocalDateTime refreshTime) {
		this.refreshTime = refreshTime;
	}
	
}
