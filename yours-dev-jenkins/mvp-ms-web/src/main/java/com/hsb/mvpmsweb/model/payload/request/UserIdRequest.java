package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe user id Request.")
@Data
@Accessors(fluent = true)
public class UserIdRequest {
	
	@JsonProperty("userId")
	private Integer userId = null;
	
}
