package com.hsb.mvpmsweb.model.payload.staticData.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;
import com.hsb.mvpmsweb.model.payload.staticData.SystemNotificationResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to Get System Notificaiton List response data.
 */
@Data
@ApiModel(description = "This entity is used to get system notificaiton List response data.")
@Validated
public class SystemNotificationListResponse extends ResponseBody {
	
	@JsonProperty("data")
	private List<SystemNotificationResponseData> data = null;

	public SystemNotificationListResponse (ResponseResult result) {
		super(result);
	}

}
