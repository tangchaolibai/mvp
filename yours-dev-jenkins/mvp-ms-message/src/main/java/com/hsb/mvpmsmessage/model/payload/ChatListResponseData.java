package com.hsb.mvpmsmessage.model.payload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatListResponseData   {
	@JsonProperty("messageList")
	private List<ChatResponseData> messageList = null;

	public List<ChatResponseData> getMessageList() {
		return messageList;
	}
	
	public void setMessageList(List<ChatResponseData> messageList) {
		this.messageList = messageList;
	}
 
}

