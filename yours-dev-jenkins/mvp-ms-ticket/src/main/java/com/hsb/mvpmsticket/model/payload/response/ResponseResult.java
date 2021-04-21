package com.hsb.mvpmsticket.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe the response result.
 */
@ApiModel(description = "This entity is used to describe the response result.")
@Validated
public class ResponseResult {

	public final static String CODE_SUCCESS = "SUCCESS";
	public final static String CODE_MESSAGE = "Request processed successfully.";
	public static ResponseResult DefaultSuccessResponse = new ResponseResult(CODE_SUCCESS, CODE_MESSAGE);

	@JsonProperty("code")
	private String code = null;

	@JsonProperty("message")
	private String message = null;

	public ResponseResult code(String code) {
		this.code = code;
		return this;
	}
	
	public ResponseResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * Response Result Code. It could be SUCCESS or specific error code.
	 * @return code
	 **/
	@ApiModelProperty(value = "Response Result Code. It could be SUCCESS or specific error code.")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResponseResult message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Response Result Message. It will be the detail explanation for specific error code.
	 * @return message
	 **/
	@ApiModelProperty(value = "Response Result Message. It will be the detail explanation for specific error code.")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
