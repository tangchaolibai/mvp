package com.hsb.mvpmsticket.model.payload;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsticket.model.payload.response.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe an exception response.
 */
@ApiModel(description = "This entity is used to describe an exception response.")
@Validated
public class ExceptionResponse {
	@JsonProperty("result")
	private ResponseResult result = null;
	
	public ExceptionResponse (ResponseResult result) {
		this.result = result;
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
