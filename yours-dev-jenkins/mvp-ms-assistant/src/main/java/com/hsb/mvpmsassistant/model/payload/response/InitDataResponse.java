package com.hsb.mvpmsassistant.model.payload.response;

import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.ApiModel;

/**
 * This entity is data initialization response.
 */
@ApiModel(description = "This entity is data initialization response.")
@Validated
public class InitDataResponse extends ResponseBody {
	
	public InitDataResponse(ResponseResult result) {
		super(result);
	}
	
}
