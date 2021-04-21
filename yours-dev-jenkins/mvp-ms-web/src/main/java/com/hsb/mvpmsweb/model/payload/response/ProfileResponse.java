package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ProfileResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This entity is used to describe a profile Response.")
@Validated
public class ProfileResponse {

	@JsonProperty("data")
	private ProfileResponseData data = null;

	@JsonProperty("result")
	private ResponseResult result = null;

	public ProfileResponseData getData() {
		return data;
	}

	public void setData(ProfileResponseData data) {
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
		sb.append("class ProfileResponse {\n");

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
