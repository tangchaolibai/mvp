package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.CountResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is unRead notificaiton count response.
 */
@Data
@ApiModel(description = "This entity is unRead notificaiton count response.")
@Validated
public class CountResponse extends ResponseBody {
	
	@JsonProperty("data")
	private CountResponseData data = null;

	public CountResponse(ResponseResult result) {
		super(result);
	}
	
}
