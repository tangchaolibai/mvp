package com.hsb.mvpmsweb.model.payload.assistant.swiftpass;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to base product request.
 */
@Data
@ApiModel(description = "This entity is used to base product request.")
@Validated
@ToString
public class SPFindProductsRequest {
	
	private String appkey = null;
	
	private String action = null;
	
	private String timestamp = null;
	
	private String check = null;
	
	private Map<String, String> valuepairs = null;
	
}
