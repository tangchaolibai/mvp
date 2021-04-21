package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "This entity is used to describe an Login response data.")
@Data
@ToString
public class IsFriendResponseData {
	
	@JsonProperty("flag")
	private Boolean flag;
	
	@JsonProperty("userId")
	private Integer userId;
	
	@JsonProperty("fanId")
	private Integer fanId;
	
}
