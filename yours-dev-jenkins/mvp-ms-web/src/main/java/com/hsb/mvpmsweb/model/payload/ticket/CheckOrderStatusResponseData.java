package com.hsb.mvpmsweb.model.payload.ticket;

import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Check Order Status Response Data Model")
@Valid
@Accessors(chain = true)
@AllArgsConstructor
public class CheckOrderStatusResponseData {
	
	private String orderStatus;

}
