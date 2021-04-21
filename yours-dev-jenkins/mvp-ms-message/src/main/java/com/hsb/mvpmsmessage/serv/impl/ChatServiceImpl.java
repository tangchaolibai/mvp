package com.hsb.mvpmsmessage.serv.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsb.mvpmsmessage.constant.MessageConstants;
import com.hsb.mvpmsmessage.constant.WebSocketConstant;
import com.hsb.mvpmsmessage.mapper.ChatMapper;
import com.hsb.mvpmsmessage.model.entity.Message;
import com.hsb.mvpmsmessage.model.payload.ChatListResponseData;
import com.hsb.mvpmsmessage.model.payload.ChatResponseData;
import com.hsb.mvpmsmessage.model.payload.request.ChatRequest;
import com.hsb.mvpmsmessage.serv.ChatService;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Service
@Transactional(rollbackFor = Exception.class)
public class ChatServiceImpl implements ChatService {

	private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
	
	@Autowired
	private ChatMapper chatMapper;

	//好友登陆提示上线
	@Override
	public void online(JSONObject param, ChannelHandlerContext ctx) {
		String userId = (String) param.get("fromUserId");
		WebSocketConstant.onlineUserMap.put(userId, ctx);
		WebSocketConstant.offlineUserMap.put(ctx, userId);
		Map<String, String> map = new HashMap<>();
		map.put("type", "online");
		map.put("userId", userId);
		logger.info("user online hint........." + JSONObject.toJSONString(map));
		sendMessage(ctx, JSONObject.toJSONString(map));
	}
	
	@Override
	public ChatListResponseData chat(ChatRequest chatRequest) {
		ChatListResponseData listResponseData = new ChatListResponseData();
		Map<String, Object> params = new HashMap<>();
		params.put(MessageConstants.MESSAGE_TYPE, chatRequest.getMsgType());
		params.put(MessageConstants.MESSAGE_FROM_USER_ID, chatRequest.getFromUserId());
		params.put(MessageConstants.MESSAGE_TO_USER_ID, chatRequest.getToUserId());
		//params.put("MSG", chatRequest.getMessage());
		Wrapper<Message> message = new QueryWrapper<Message>().allEq(params);
		List<Message> messageList = chatMapper.selectList(message);
		List<ChatResponseData> dataList = new ArrayList<>();
		messageList.forEach(e -> dataList.add(toChatResponseData(e)));
		listResponseData.setMessageList(dataList);
		return listResponseData;
	}

	private ChatResponseData toChatResponseData(Message message) {
		ChatResponseData data = new ChatResponseData();
		data.setCreateTime(LocalDateTime.now());
		Integer fromUserId = message.fromUserId();
		data.setFromUserId(fromUserId);
		
		Integer toUserId = message.toUserId();
		data.setToUserId(toUserId);
		
		data.setMessage(message.msg());
		data.setMsgType(message.msgType());
		return data;
	}
	
	@Override
	public void singleSend(JSONObject param, ChannelHandlerContext ctx) {
		Object from = param.get("fromUserId");
		Integer fromUserId = null;
		if (null != from) {
			fromUserId = Integer.parseInt((String)from);
		}
		Object to = param.get("toUserId");
		Integer toUserId = null;
		if (null != to) {
			toUserId = Integer.parseInt((String)to);
		}
        param.get("toUserId");
        String content = (String)param.get("content");
        Message message = new Message();
        message.fromUserId(fromUserId);
		message.toUserId(toUserId);
		message.msg(content);
		message.msgType(MessageConstants.MESSAGE_TYPE_CHAT);
		try {
			chatMapper.insert(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map =  new HashMap<>();
		map.put("code", 200);
		map.put("createTime", LocalDateTime.now());
		map.put("fromUserId", fromUserId);
		map.put("toUserId", toUserId);
		map.put("message", content);
		map.put("msgType", MessageConstants.MESSAGE_TYPE_CHAT);
		ChannelHandlerContext toUserCtx = WebSocketConstant.onlineUserMap.get(toUserId.toString());
		String sendMessage = JSONObject.toJSONString(map);
		sendMessage(toUserCtx, sendMessage);
	}
	
	@Override
	public void offline(ChannelHandlerContext ctx) {
		WebSocketConstant.onlineUserMap.values().removeIf(v -> v == ctx);
		Map<String, String> map = new HashMap<>();
		map.put("type", "offLine");
		String userId = WebSocketConstant.offlineUserMap.get(ctx);
		map.put("userId", userId);
		sendMessage(ctx, JSONObject.toJSONString(map));
	}

	private void sendMessage(ChannelHandlerContext ctx, String message) {
		ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
	}
	
}
