package com.hsb.mvpmsweb.model.payload.message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel(description = "This entity is used to chat list response data.")
@ToString
public class ChatListResponseData {
	
	@JsonProperty("messageList")
	private List<ChatResponseData> messageList = null;

}
