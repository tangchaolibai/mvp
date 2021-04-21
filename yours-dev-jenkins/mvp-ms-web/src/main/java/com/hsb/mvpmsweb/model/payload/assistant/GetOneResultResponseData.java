package com.hsb.mvpmsweb.model.payload.assistant;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get one search result response data.
 */
@Data
@ApiModel(description = "This entity is used to get one search result response data.")
@Validated
@ToString
public class GetOneResultResponseData {
	
	private Object result = null;

}
