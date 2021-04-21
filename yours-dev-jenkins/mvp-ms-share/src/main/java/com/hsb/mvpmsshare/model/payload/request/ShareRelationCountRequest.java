package com.hsb.mvpmsshare.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a ShareRelationCountRequest.")
@Validated
public class ShareRelationCountRequest {

	@JsonProperty("shareId")
	private Integer shareId;

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}
	
}
