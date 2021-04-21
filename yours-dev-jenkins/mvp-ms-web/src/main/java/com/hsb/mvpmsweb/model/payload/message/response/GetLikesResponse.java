package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.GetLikesResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is get likes response.
 */
@Data
@ApiModel(description = "This entity is get likes response.")
@Validated
public class GetLikesResponse extends ResponseBody {
	
	@JsonProperty("data")
	private GetLikesResponseData data = null;

	public GetLikesResponse(ResponseResult result) {
		super(result);
	}

}
