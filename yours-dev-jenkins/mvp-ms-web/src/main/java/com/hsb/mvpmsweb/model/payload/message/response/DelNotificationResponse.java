package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;

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
