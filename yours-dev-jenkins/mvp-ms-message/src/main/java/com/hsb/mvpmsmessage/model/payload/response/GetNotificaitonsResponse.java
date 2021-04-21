package com.hsb.mvpmsmessage.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.GetNotificaitonsResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is get notifications response.
 */
@ApiModel(description = "This entity is get notifications response.")
@Validated
public class GetNotificaitonsResponse extends ResponseBody {
	@JsonProperty("data")
	private GetNotificaitonsResponseData data = null;

	public GetNotificaitonsResponse(ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public GetNotificaitonsResponseData getData() {
		return data;
	}

	public void setData(GetNotificaitonsResponseData data) {
		this.data = data;
	}

}
