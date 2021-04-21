package com.hsb.mvpmsweb.model.payload.message.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.message.ChatListResponseData;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is used to describe a chat response.
 */
@Data
@ApiModel(description = "This entity is used to describe a chat response.")
@Validated
public class ChatResponse  extends ResponseBody {
	
	@JsonProperty("data")
	private ChatListResponseData data = null;

}
