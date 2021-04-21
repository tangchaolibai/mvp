package com.hsb.mvpmsmessage.model.payload.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.CountResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is unRead notificaiton count response.
 */
@ApiModel(description = "This entity is unRead notificaiton count response.")
@Validated
public class CountResponse extends ResponseBody {
	@JsonProperty("data")
	private CountResponseData data = null;

	public CountResponse(ResponseResult result) {
		super(result);
	}
	
	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public CountResponseData getData() {
		return data;
	}

	public void setData(CountResponseData data) {
		this.data = data;
	}

}
