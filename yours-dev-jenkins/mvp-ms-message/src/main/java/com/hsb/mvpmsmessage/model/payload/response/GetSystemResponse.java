package com.hsb.mvpmsmessage.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.GetSystemResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is get system messages response.
 */
@ApiModel(description = "This entity is get system messages response.")
@Validated
public class GetSystemResponse extends ResponseBody {
	@JsonProperty("data")
	private GetSystemResponseData data = null;

	public GetSystemResponse(ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public GetSystemResponseData getData() {
		return data;
	}

	public void setData(GetSystemResponseData data) {
		this.data = data;
	}

}
