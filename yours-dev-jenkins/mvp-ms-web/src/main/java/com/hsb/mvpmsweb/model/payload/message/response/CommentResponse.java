package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.CommentResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is used to describe a comment response.
 */
@Data
@ApiModel(description = "This entity is used to describe a comment response.")
@Validated
public class CommentResponse extends ResponseBody {

	@JsonProperty("data")
	private CommentResponseData data = null;

	public CommentResponse(ResponseResult result) {
		super(result);
	}
	
}
