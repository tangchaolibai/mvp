package com.hsb.mvpmsweb.model.payload.ticket.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This entity is used to find order request by trade No..
 */
@Data
@ApiModel(description = "This entity is used to find order by trade No. request.")
@Validated
@ToString
public class FindOrderRequest {
	
	@JsonProperty(required = true, value = "out_trade_no", defaultValue = "141903606228")
	public String outTradeNo = null;
	
}
