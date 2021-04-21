package com.hsb.mvpmsuser.model.payload;


import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to describe an User response data.
 */
@ApiModel(description = "This entity is used to describe an Login response data.")
@Validated
@Data
@Accessors(fluent = true)
public class SystemSettingResponseData {
	@JsonProperty("entityId")
	private Integer entityId = null;
	
	@JsonProperty("userId")
	private Integer userId = null;
	
	@JsonProperty("language")
	private String language = null;
	
	@JsonProperty("privateAccount")
	private String privateAccount = null;
	
	
	@JsonProperty("searchAllow")
	private String searchAllow = null;
	
	
	@JsonProperty("onlineShow")
	private String onlineShow = null;
	
	
	@JsonProperty("feedbackHelp")
	private String feedbackHelp = null;
	
	@JsonProperty("createTime")
	private String creationDateTime = null;

	@JsonProperty("lastUpdateTime")
	private String lastUpdateDateTime = null;

}
