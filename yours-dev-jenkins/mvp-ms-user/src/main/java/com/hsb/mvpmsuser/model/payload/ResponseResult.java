package com.hsb.mvpmsuser.model.payload;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe the response result.
 */
@ApiModel(description = "This entity is used to describe the response result.")
@Validated

public class ResponseResult {

	public static final String CODE_SUCCESS = "SUCCESS";
	public static final String CODE_MESSAGE = "Request processed successfully.";
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
	 * 
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
	 * Response Result Message. It will be the detail explanation for specific error
	 * code.
	 * 
	 * @return message
	 **/
	@ApiModelProperty(value = "Response Result Message. It will be the detail explanation for specific error code.")

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResponseResult responseResult = (ResponseResult) o;
		return Objects.equals(this.code, responseResult.code) && Objects.equals(this.message, responseResult.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseResult {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
