package com.hsb.mvpmsmessage.model.payload.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.GetATResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is get AT me response.
 */
@ApiModel(description = "This entity is get AT me response.")
@Validated
public class GetATResponse extends ResponseBody {
	@JsonProperty("data")
	private GetATResponseData data = null;

	public GetATResponse(ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public GetATResponseData getData() {
		return data;
	}

	public void setData(GetATResponseData data) {
		this.data = data;
	}

}
