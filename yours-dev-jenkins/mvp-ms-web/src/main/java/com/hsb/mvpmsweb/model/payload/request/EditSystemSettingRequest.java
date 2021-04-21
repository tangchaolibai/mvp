package com.hsb.mvpmsweb.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe an edit user Request.")
@Validated
@Data
@Accessors(fluent = true)
public class EditSystemSettingRequest {
	
	@JsonProperty("userId")
	@ApiModelProperty(required = true, value = "userId")
	private Integer userId = null;
	
	@JsonProperty("language")
	@ApiModelProperty(required = true, value = "language")
	private String language = null;
	
	@JsonProperty("privateAccount")
	@ApiModelProperty(required = true, value = "privateAccount")
	private String privateAccount = null;
	
	@JsonProperty("searchAllow")
	@ApiModelProperty(required = true, value = "searchAllow")
	private String searchAllow = null;
	
	@JsonProperty("onlineShow")
	@ApiModelProperty(required = true, value = "onlineShow")
	private String onlineShow = null;
	
	@JsonProperty("feedbackHelp")
	@ApiModelProperty(required = true, value = "feedbackHelp")
	private String feedbackHelp = null;
	
}
