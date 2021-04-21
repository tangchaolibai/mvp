package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.GetATResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is get AT me response.
 */
@Data
@ApiModel(description = "This entity is get AT me response.")
@Validated
public class GetATResponse extends ResponseBody {
	
	@JsonProperty("data")
	private GetATResponseData data = null;

	public GetATResponse(ResponseResult result) {
		super(result);
	}

}
