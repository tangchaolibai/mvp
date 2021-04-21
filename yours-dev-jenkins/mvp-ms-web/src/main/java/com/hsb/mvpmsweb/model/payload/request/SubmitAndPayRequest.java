package com.hsb.mvpmsweb.model.payload.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.hsb.mvpmsweb.api.annotation.PaymentInstance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "This entity is used to describe an SubmitAndPay Request.")
@Data
@Valid
public class SubmitAndPayRequest {
	
	@NotNull
	@ApiModelProperty(required = true, value = "4", example = "4")
	private int productId;
	
	@NotNull
	@ApiModelProperty(required = true, value = "1", example = "1")
	private int count;
	
	@ApiModelProperty(required = false)
	private String callBackUrl;
	
	@PaymentInstance
	@ApiModelProperty(required = false, notes = "Only 'ALIPAYCN' and 'ALIPAYHK' supported")
	private String paymentInstance;

}
