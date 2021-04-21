package com.hsb.mvpmsmessage.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.GetLikesResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is get likes response.
 */
@ApiModel(description = "This entity is get likes response.")
@Validated
public class GetLikesResponse extends ResponseBody {
	@JsonProperty("data")
	private GetLikesResponseData data = null;

	public GetLikesResponse(ResponseResult result) {
		super(result);
	}
	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public GetLikesResponseData getData() {
		return data;
	}

	public void setData(GetLikesResponseData data) {
		this.data = data;
	}

}
