package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.ShareInfoData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * This entity is used to describe a Share Response.
 */
@Data
@ApiModel(description = "This entity is used to describe a Share Response.")
@Validated
@ToString
public class ShareResponse {
	
	@JsonProperty("data")
	private ShareInfoData data = null;

	@JsonProperty("result")
	private ResponseResult result = null;

	public ShareResponse result(ResponseResult result) {
		this.result = result;
		return this;
	}

}
