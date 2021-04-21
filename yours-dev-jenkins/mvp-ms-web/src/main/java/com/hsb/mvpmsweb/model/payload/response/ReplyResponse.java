package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ReplyResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a reply response.")
@Validated
public class ReplyResponse {

	@JsonProperty("data")
	private ReplyResponseData data = null;

	@JsonProperty("result")
	private ResponseResult result = null;

	public ReplyResponseData getData() {
		return data;
	}

	public void setData(ReplyResponseData data) {
		this.data = data;
	}

	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ReplyResponse {\n");

		sb.append("    data: ").append(toIndentedString(data)).append("\n");
		sb.append("    result: ").append(toIndentedString(result)).append("\n");
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
