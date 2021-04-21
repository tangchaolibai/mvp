package com.hsb.mvpmsassistant.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsassistant.model.payload.SearchUsersResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is search users response.
 */
@ApiModel(description = "This entity is search users response.")
@Validated
public class SearchUsersResponse extends ResponseBody {
	@JsonProperty("data")
	private SearchUsersResponseData data = null;

	public SearchUsersResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public SearchUsersResponseData getData() {
		return data;
	}

	public void setData(SearchUsersResponseData data) {
		this.data = data;
	}

}
