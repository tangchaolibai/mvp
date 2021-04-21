package com.hsb.mvpmsweb.model.payload.assistant.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.SearchProductsResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is search products response.
 */
@Data
@ApiModel(description = "This entity is search products response.")
@Validated
public class SearchProductsResponse extends ResponseBody {
	
	@JsonProperty("data")
	private SearchProductsResponseData data = null;

	public SearchProductsResponse (ResponseResult result) {
		super(result);
	}

}
