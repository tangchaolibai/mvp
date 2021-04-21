package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.GetSystemResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is get system messages response.
 */
@Data
@ApiModel(description = "This entity is get system messages response.")
@Validated
public class GetSystemResponse extends ResponseBody {
	
	@JsonProperty("data")
	private GetSystemResponseData data = null;

	public GetSystemResponse(ResponseResult result) {
		super(result);
	}

}
