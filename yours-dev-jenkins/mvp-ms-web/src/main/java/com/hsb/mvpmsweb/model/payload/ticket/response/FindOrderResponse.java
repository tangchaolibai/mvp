package com.hsb.mvpmsweb.model.payload.ticket.response;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;
import com.hsb.mvpmsweb.model.payload.ticket.FindOrderResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Find Order By No. Response")
@Valid
public class FindOrderResponse extends ResponseBody { 
	
	@JsonProperty("data")
	private FindOrderResponseData data = null;

	public FindOrderResponse (ResponseResult result) {
		super(result);
	}
	
}
