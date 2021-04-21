package com.hsb.mvpmsweb.model.payload.ticket;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel(description = "Find Order By No. Response Data")
@Valid
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindOrderResponseData { 
	
	private String status = null;
	
	private String errCode = null;
	
	private String errMsg = null;
	
	private String mchId = null;
	
	private String resultCode = null;
	
	private String tradeState = null;
	
	private String openid = null;
	
	private String tradeType = null;
	
	private String transactionId = null;
	
	private String outTransactionId = null;
	
	private String outTradeNo = null;
	
	private String totalFee = null;
	
	private String timeEnd = null;
	
}
