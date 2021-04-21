package com.hsb.mvpmsshare.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a CancelFollowRequest.")
@Validated
public class CancelFollowRequest {

	@JsonProperty("entityId")
	private Integer entityId;

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	
}
