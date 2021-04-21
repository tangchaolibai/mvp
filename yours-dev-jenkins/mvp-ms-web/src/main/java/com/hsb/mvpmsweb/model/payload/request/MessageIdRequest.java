package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe message id Request.")
@Data
@Accessors(fluent = true)
public class MessageIdRequest {
	
	@JsonProperty("msgId")
	private Integer msgId = null;
	
}
