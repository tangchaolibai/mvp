package com.hsb.mvpmsweb.model.payload.ticket;

import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Buy tickets Response Model")
@Valid
@Accessors(chain = true)
public class PayOrderResponseData {
	
	@ApiModelProperty(value = "Result of calling pre-order intf", required = true)
	private Boolean isSuccess;
	
	@ApiModelProperty(value = "Url to call Alipay SDK", required = true)
	private String orderString;
	
	@ApiModelProperty(value = "Error code return by swiftpass", required = false)
	private String errorCode;
	
	@ApiModelProperty(value = "Error msg return by swiftpass", required = false)
	private String errorMsg;

}
