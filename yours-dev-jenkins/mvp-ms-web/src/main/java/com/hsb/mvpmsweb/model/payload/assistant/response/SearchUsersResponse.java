package com.hsb.mvpmsweb.model.payload.assistant.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.SearchUsersResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is search users response.
 */
@Data
@ApiModel(description = "This entity is search users response.")
@Validated
public class SearchUsersResponse extends ResponseBody {
	
	@JsonProperty("data")
	private SearchUsersResponseData data = null;

	public SearchUsersResponse (ResponseResult result) {
		super(result);
	}

}
