package com.hsb.mvpmsassistant.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsassistant.model.payload.SearchPhotosResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is search photos response.
 */
@ApiModel(description = "This entity is search photos response.")
@Validated
public class SearchPhotosResponse extends ResponseBody {
	@JsonProperty("data")
	private SearchPhotosResponseData data = null;

	public SearchPhotosResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public SearchPhotosResponseData getData() {
		return data;
	}

	public void setData(SearchPhotosResponseData data) {
		this.data = data;
	}

}
