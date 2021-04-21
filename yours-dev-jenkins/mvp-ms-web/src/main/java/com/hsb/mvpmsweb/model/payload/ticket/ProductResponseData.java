package com.hsb.mvpmsweb.model.payload.ticket;

import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Product Model")
@Valid
@ToString
@Accessors(chain = true)
public class ProductResponseData {
	
	@ApiModelProperty(value = "Product ID", required = true, example = "1")
	private Integer entityId;
	
	@ApiModelProperty(value = "Product name", required = true, example = "Jay Chou Concert")
	private String name;
	 
	@ApiModelProperty(value = "Product thumbnail url", required = true, example = "/useruploadFile/product/7420200813121212jaychow.jpg")
	private String photoPath;
	
	@ApiModelProperty(value = "Currency. Only 'HKD' and 'CNY' available.", required = true, example = "HKD")
	private String currency;
}
