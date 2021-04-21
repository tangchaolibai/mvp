package com.hsb.mvpmsshare.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a FollowingUserByUserNameRequest.")
@Validated
public class FollowingUserByUserNameRequest {

	@JsonProperty("userId")
	private Integer userId;
	@JsonProperty("searchContent")
	private String searchContent;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	
}
