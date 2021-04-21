package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.GetCommentsResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is get comments response.
 */
@Data
@ApiModel(description = "This entity is get comments response.")
@Validated
public class GetCommentsResponse extends ResponseBody {
	
	@JsonProperty("data")
	private GetCommentsResponseData data = null;

	public GetCommentsResponse(ResponseResult result) {
		super(result);
	}

}
