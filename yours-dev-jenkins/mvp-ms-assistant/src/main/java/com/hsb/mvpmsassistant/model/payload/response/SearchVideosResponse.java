package com.hsb.mvpmsassistant.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsassistant.model.payload.SearchVideosResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is search videos response.
 */
@ApiModel(description = "This entity is search videos response.")
@Validated
public class SearchVideosResponse extends ResponseBody {
	@JsonProperty("data")
	private SearchVideosResponseData data = null;

	public SearchVideosResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public SearchVideosResponseData getData() {
		return data;
	}

	public void setData(SearchVideosResponseData data) {
		this.data = data;
	}

}
