package com.hsb.mvpmsassistant.model.payload.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsassistant.model.payload.SearchProductsResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is search products response.
 */
@ApiModel(description = "This entity is search products response.")
@Validated
public class SearchProductsResponse extends ResponseBody {
	@JsonProperty("data")
	private SearchProductsResponseData data = null;

	public SearchProductsResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public SearchProductsResponseData getData() {
		return data;
	}

	public void setData(SearchProductsResponseData data) {
		this.data = data;
	}

}
