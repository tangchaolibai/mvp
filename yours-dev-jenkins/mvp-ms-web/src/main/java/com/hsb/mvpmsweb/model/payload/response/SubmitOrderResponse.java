package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.ticket.OrderResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe submit order response.")
@Validated
@Data
public class SubmitOrderResponse extends BaseResponse {
	
	private OrderResponseData data;

	public SubmitOrderResponse(ResponseResult result, OrderResponseData data) {
		super(result);
		this.data = data;
	}

}
