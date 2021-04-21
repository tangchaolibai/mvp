package com.hsb.mvpmsmessage.model.payload.response;

import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is search users response.
 */
@ApiModel(description = "This entity is delete notification response.")
@Validated
public class DelNotificationResponse extends ResponseBody {
	
	public DelNotificationResponse(ResponseResult result) {
		super(result);
	}
	
}
