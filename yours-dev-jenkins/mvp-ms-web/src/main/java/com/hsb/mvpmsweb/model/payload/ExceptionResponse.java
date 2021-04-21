package com.hsb.mvpmsweb.model.payload;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

/**
 * This entity is used to describe an exception response.
 */
@ApiModel(description = "This entity is used to describe an exception response.")
@Validated
@ToString
public class ExceptionResponse {

	@JsonProperty("result")
	private ResponseResult result = null;

	public ExceptionResponse (ResponseResult result) {
		this.result = result;
		//    return this;
	}

	/**
	 * Response Result.
	 * @return result
	 **/
	@ApiModelProperty(value = "Response Result.")
	@Valid
	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}

}
