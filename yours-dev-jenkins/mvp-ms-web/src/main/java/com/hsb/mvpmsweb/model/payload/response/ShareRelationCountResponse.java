package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.ShareRelationCountResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Share Relation Count Response.
 */
@ApiModel(description = "This entity is used to describe a Share Relation Count Response.")
@Validated

public class ShareRelationCountResponse {
	@JsonProperty("data")
	private ShareRelationCountResponseData data = null;

	@JsonProperty("result")
	private ResponseResult result = null;

	public ShareRelationCountResponse data(ShareRelationCountResponseData data) {
		this.data = data;
		return this;
	}

	/**
	 * Get data
	 * 
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public ShareRelationCountResponseData getData() {
		return data;
	}

	public void setData(ShareRelationCountResponseData data) {
		this.data = data;
	}

	public ShareRelationCountResponse result(ResponseResult result) {
		this.result = result;
		return this;
	}

	/**
	 * Response Result.
	 * 
	 * @return result
	 **/
	@ApiModelProperty(value = "Response Result.")
	public ResponseResult getResult() {
		return result;
	}

	public void setResult(ResponseResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ShareRelationCountResponse {\n");

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
