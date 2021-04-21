package com.hsb.mvpmsmessage.serv;

import com.alibaba.fastjson.JSONObject;
import com.hsb.mvpmsmessage.model.payload.ChatListResponseData;
import com.hsb.mvpmsmessage.model.payload.request.ChatRequest;

import io.netty.channel.ChannelHandlerContext;

public interface ChatService {
	
	ChatListResponseData chat(ChatRequest chatRequest);
	void singleSend(JSONObject param, ChannelHandlerContext ctx);
	void online(JSONObject param, ChannelHandlerContext ctx);
	void offline(ChannelHandlerContext ctx);
	
}
