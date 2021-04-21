package com.hsb.mvpmsweb.model.payload.staticData.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;
import com.hsb.mvpmsweb.model.payload.staticData.SystemNotificationResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is used to Get System Notification response data.
 */
@Data
@ApiModel(description = "This entity is used to system notification response data.")
@Validated
public class SystemNotificationResponse extends ResponseBody {
	
	@JsonProperty("data")
	private SystemNotificationResponseData data = null;

	public SystemNotificationResponse (ResponseResult result) {
		super(result);
	}

}
