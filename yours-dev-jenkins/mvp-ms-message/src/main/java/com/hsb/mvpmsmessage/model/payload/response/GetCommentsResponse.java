package com.hsb.mvpmsmessage.model.payload.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.GetCommentsResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is get comments response.
 */
@ApiModel(description = "This entity is get comments response.")
@Validated
public class GetCommentsResponse extends ResponseBody {
	@JsonProperty("data")
	private GetCommentsResponseData data = null;

	public GetCommentsResponse(ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public GetCommentsResponseData getData() {
		return data;
	}

	public void setData(GetCommentsResponseData data) {
		this.data = data;
	}

}
