package com.hsb.mvpmsweb.model.payload.ticket.response;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;
import com.hsb.mvpmsweb.model.payload.ticket.OrderRefundResponseData;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Order Refund Model")
@Valid
public class OrderRefundResponse extends ResponseBody {

	@JsonProperty("data")
	private OrderRefundResponseData data;

	public OrderRefundResponse(ResponseResult result) {
		super(result);
	}

}
