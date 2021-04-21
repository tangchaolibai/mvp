package com.hsb.mvpmsweb.model.payload.assistant.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.SearchVideosResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is search videos response.
 */
@Data
@ApiModel(description = "This entity is search videos response.")
@Validated
public class SearchVideosResponse extends ResponseBody {
	
	@JsonProperty("data")
	private SearchVideosResponseData data = null;

	public SearchVideosResponse (ResponseResult result) {
		super(result);
	}

}
