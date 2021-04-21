package com.hsb.mvpmsassistant.model.payload.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This abstract entity is used to represent a standard Response Body which includes the result and data.")
abstract public class ResponseBody {
	@ApiModelProperty(value = "Response Result.")
	private ResponseResult result;

	public ResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseBody(ResponseResult result) {
		super();
		this.result = result;
	}

	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}

}
