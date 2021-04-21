package com.hsb.mvpmsweb.model.payload.ticket.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This entity is used to find order request.
 */
@Data
@ApiModel(description = "This entity is used to refund order request.")
@Validated
@ToString
public class OrderRefundRequest {

	@ApiModelProperty(required = true, value = "transaction_id", example = "001")
	public Integer transaction_id = null;

	@ApiModelProperty(required = true, value = "refund_fee", example = "400")
	public BigDecimal refund_fee = null;
	
	@JsonProperty(required = true, value = "out_trade_no", defaultValue = "141903606228")
	public String outTradeNo = null;
}
