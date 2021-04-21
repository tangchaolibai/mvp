package com.hsb.mvpmsshare.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.ResponseResult;
import com.hsb.mvpmsshare.model.payload.ShareResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Share Response.
 */
@ApiModel(description = "This entity is used to describe a Share Response.")
@Validated
public class ShareResponse {
	@JsonProperty("data")
	private ShareResponseData data = null;

	@JsonProperty("result")
	private ResponseResult result = null;

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public ShareResponseData getData() {
		return data;
	}

	public void setData(ShareResponseData data) {
		this.data = data;
	}

	public ShareResponse result(ResponseResult result) {
		this.result = result;
		return this;
	}

	/**
	 * Response Result.
	 * @return result
	 **/
	@ApiModelProperty(value = "Response Result.")
	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}

}
