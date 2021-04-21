package com.hsb.mvpmsweb.model.payload.ticket.response;

import javax.validation.Valid;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.ticket.ProductDetailResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Valid
@Data
@ApiModel(description = "This entity is used to describe find product's detail response.")
public class FindProductDetailResponse extends BaseResponse {
	
	@ApiModelProperty(value = "Product Detail")
	private ProductDetailResponseData data;

	public FindProductDetailResponse(ResponseResult result, ProductDetailResponseData data) {
		super(result);
		this.data = data;
	}

}
