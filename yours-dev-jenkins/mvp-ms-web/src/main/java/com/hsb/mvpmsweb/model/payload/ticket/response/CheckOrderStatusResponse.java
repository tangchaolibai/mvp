package com.hsb.mvpmsweb.model.payload.ticket.response;

import javax.validation.Valid;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.ticket.CheckOrderStatusResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Valid
@Data
@ApiModel(description = "This entity is used to describe check order status response.")
public class CheckOrderStatusResponse extends BaseResponse {
	
	private CheckOrderStatusResponseData data;
	
	public CheckOrderStatusResponse(ResponseResult result, CheckOrderStatusResponseData data) {
		super(result);
		this.data = data;
	}

}
