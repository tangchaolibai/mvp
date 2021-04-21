package com.hsb.mvpmsweb.model.payload.ticket;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel(description = "Order Refund Model")
@Valid
@ToString
public class OrderRefundResponseData {

	@JsonProperty("version")
	private String version;

	@JsonProperty("charset")
	private String charset;

	@JsonProperty("sign_type")
	private String sign_type;

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("result_code")
	private String result_code;

	@JsonProperty("mch_id")
	private String mch_id;

	@JsonProperty("device_info")
	private String device_info;

	@JsonProperty("nonce_str")
	private String nonce_str;

	@JsonProperty("err_code")
	private String err_code;

	@JsonProperty("err_msg")
	private String err_msg;

	@JsonProperty("sign")
	private String sign;

	@JsonProperty("transaction_id")
	private String transaction_id;

	@JsonProperty("out_trade_no")
	private String out_trade_no;

	@JsonProperty("out_refund_no")
	private String out_refund_no;

	@JsonProperty("refund_id")
	private String refund_id;

	@JsonProperty("refund_channel")
	private String refund_channel;

	@JsonProperty("refund_fee")
	private String refund_fee;

	@JsonProperty("coupon_refund_fee")
	private String coupon_refund_fee;

}
