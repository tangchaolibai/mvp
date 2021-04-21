package com.hsb.mvpmsweb.model.payload.message;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to unRead notificaiton count response data.
 */
@Data
@ApiModel(description = "This entity is used to unRead notificaiton count response data.")
@Validated
@ToString
public class CountResponseData {
	
	private Integer count = null;

}
