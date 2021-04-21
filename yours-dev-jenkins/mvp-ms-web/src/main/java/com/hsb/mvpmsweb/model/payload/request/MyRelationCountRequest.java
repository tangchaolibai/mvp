package com.hsb.mvpmsweb.model.payload.request;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a my relation count request.")
@Validated
public class MyRelationCountRequest {
	
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "MyRelationCountRequest [userId=" + userId + "]";
	}
	
}
