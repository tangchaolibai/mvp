package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.PostContentResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * This entity is used to describe post content Response.
 */
@Data
@ApiModel(description = "This entity is used to describe post content Response.")
@Validated
@ToString
public class PostContentResponse {
	
	@JsonProperty("data")
	private PostContentResponseData data = null;

	@JsonProperty("result")
	private ResponseResult result = null;

	public PostContentResponse result(ResponseResult result) {
		this.result = result;
		return this;
	}

}
