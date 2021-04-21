package com.hsb.mvpmsweb.model.payload.ticket.response;

import java.util.List;

import javax.validation.Valid;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.ticket.ProductResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Valid
@Data
@ApiModel(description = "This entity is used to describe find user's products response.")
public class FindProductResponse extends BaseResponse {
	
	@ApiModelProperty(value = "Product list")
	private List<ProductResponseData> data;

	public FindProductResponse(ResponseResult result, List<ProductResponseData> data) {
		super(result);
		this.data = data;
	}

}
