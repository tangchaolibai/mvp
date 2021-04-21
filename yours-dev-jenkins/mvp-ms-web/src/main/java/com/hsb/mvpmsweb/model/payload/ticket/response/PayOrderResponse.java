package com.hsb.mvpmsweb.model.payload.ticket.response;

import javax.validation.Valid;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.ticket.PayOrderResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Valid
@Data
@ApiModel(description = "This entity is used to describe buy ticket response.")
public class PayOrderResponse extends BaseResponse {
	
	@ApiModelProperty(value = "BuyTicketResponse Data")
	private PayOrderResponseData data;

	public PayOrderResponse(ResponseResult result, PayOrderResponseData data) {
		super(result);
		this.data = data;
	}

}
