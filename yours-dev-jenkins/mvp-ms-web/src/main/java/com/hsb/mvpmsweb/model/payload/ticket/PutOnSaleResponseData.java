package com.hsb.mvpmsweb.model.payload.ticket;

import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Put On Sale Response Model")
@Valid
@Accessors(chain = true)
public class PutOnSaleResponseData {
	
	@ApiModelProperty(value = "Product ID", required = true, example = "1")
	private Integer entityId;
	
}
