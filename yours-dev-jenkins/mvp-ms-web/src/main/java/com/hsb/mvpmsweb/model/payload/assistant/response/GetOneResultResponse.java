package com.hsb.mvpmsweb.model.payload.assistant.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.GetOneResultResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is get one search result response.
 */
@Data
@ApiModel(description = "This entity is get one search result response.")
@Validated
public class GetOneResultResponse extends ResponseBody {
	
	@JsonProperty("data")
	private GetOneResultResponseData data = null;

	public GetOneResultResponse (ResponseResult result) {
		super(result);
	}

}
