package com.hsb.mvpmsweb.model.payload.assistant.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.SearchPhotosResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is search photos response.
 */
@Data
@ApiModel(description = "This entity is search photos response.")
@Validated
public class SearchPhotosResponse extends ResponseBody {
	
	@JsonProperty("data")
	private SearchPhotosResponseData data = null;

	public SearchPhotosResponse (ResponseResult result) {
		super(result);
	}

}
