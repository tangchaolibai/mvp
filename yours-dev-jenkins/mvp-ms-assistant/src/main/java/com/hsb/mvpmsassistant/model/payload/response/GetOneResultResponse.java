package com.hsb.mvpmsassistant.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsassistant.model.payload.GetOneResultResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is get one search result response.
 */
@ApiModel(description = "This entity is get one search result response.")
@Validated
public class GetOneResultResponse extends ResponseBody {
	@JsonProperty("data")
	private GetOneResultResponseData data = null;

	public GetOneResultResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public GetOneResultResponseData getData() {
		return data;
	}

	public void setData(GetOneResultResponseData data) {
		this.data = data;
	}

}
