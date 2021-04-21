package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.CommentListResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is used to describe a discover response.
 */
@Data
@ApiModel(description = "This entity is used to describe a discover response.")
@Validated
public class CommentListResponse extends ResponseBody {
	
	@JsonProperty("data")
	private CommentListResponseData data = null;

	public CommentListResponse(ResponseResult result) {
		super(result);
	}
	
}
