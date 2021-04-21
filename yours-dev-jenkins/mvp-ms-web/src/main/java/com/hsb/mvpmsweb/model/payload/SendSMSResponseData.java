package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to send SMS for invitation response data.")
@Data
@Accessors(fluent = true)
@ToString
public class SendSMSResponseData {
	
	@JsonProperty("phoneNumber")
	private String phoneNumber = null;
	
	@JsonProperty("inviter")
	private String inviter = null;
	
	@JsonProperty("SMS")
	private String SMS = null;

}
