package com.hsb.mvpmsweb.model.payload.ticket.response;

import javax.validation.Valid;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.ticket.PutOnSaleResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Valid
@Data
@ApiModel(description = "This entity is used to describe put on sale response.")
public class PutOnSaleResponse extends BaseResponse {
	
	@ApiModelProperty(value = "PutOnSaleResponse Data")
	private PutOnSaleResponseData data;

	public PutOnSaleResponse(ResponseResult result, PutOnSaleResponseData data) {
		super(result);
		this.data = data;
	}

}
