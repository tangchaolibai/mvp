package com.hsb.mvpmsuser.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsuser.model.payload.ResponseResult;
import com.hsb.mvpmsuser.model.payload.SendSMSResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to send SMS for invitation response.")
@Validated
@Data
public class SendSMSResponse extends ResponseBody {
	
	@JsonProperty("data")
	private SendSMSResponseData data = null;
	
	public SendSMSResponse(ResponseResult result) {
		super(result);
	}
	
}
