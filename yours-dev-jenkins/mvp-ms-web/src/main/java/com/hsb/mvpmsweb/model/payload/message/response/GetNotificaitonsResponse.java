package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.GetNotificaitonsResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is get notifications response.
 */
@Data
@ApiModel(description = "This entity is get notifications response.")
@Validated
public class GetNotificaitonsResponse extends ResponseBody {
	
	@JsonProperty("data")
	private GetNotificaitonsResponseData data = null;

	public GetNotificaitonsResponse(ResponseResult result) {
		super(result);
	}

}
