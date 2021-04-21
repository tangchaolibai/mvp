package com.hsb.mvpmsmessage.model.payload.response;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsmessage.model.payload.ChatListResponseData;

import io.swagger.annotations.ApiModel;

/**
 * This entity is used to describe a chat response.
 */
@ApiModel(description = "This entity is used to describe a chat response.")
@Validated

public class ChatResponse  extends ResponseBody  {
	@JsonProperty("data")
	private ChatListResponseData data = null;

	public ChatListResponseData getData() {
		return data;
	}
	
	public void setData(ChatListResponseData data) {
		this.data = data;
	}

}

