package com.hsb.mvpmsweb.service;

import com.alibaba.fastjson.JSONObject;
import com.hsb.mvpmsweb.model.payload.message.ChatListResponseData;

import io.netty.channel.ChannelHandlerContext;

public interface ChatService {
	
	ChatListResponseData getChatRecode(Integer fromUserId, Integer toUserId, String messageType);
	boolean checkIsFriend(JSONObject param);
	//List<SocialMessage> getChatRecodeByIsRead(Integer userId, String isRead);
	void singleSend(JSONObject param, ChannelHandlerContext ctx);
	void online(JSONObject param, ChannelHandlerContext ctx);
	void offline(ChannelHandlerContext ctx);
	void downline(JSONObject param);
	void addFriendRequest(JSONObject param, ChannelHandlerContext ctx);
	void addFriendResponse(JSONObject param, ChannelHandlerContext ctx);
	boolean checkUser(JSONObject param, ChannelHandlerContext ctx);
	void atFriend(JSONObject param, ChannelHandlerContext ctx);
	void followMe(JSONObject param, ChannelHandlerContext ctx);
	void comments(JSONObject param, ChannelHandlerContext ctx);
	void like(JSONObject param, ChannelHandlerContext ctx);
	
}
