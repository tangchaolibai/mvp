package com.hsb.mvpmsweb.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.api.exception.MvpUserExceptionCode;
import com.hsb.mvpmsweb.constant.MessageConstants;
import com.hsb.mvpmsweb.constant.WebSocketConstant;
import com.hsb.mvpmsweb.mapper.ChatMapper;
import com.hsb.mvpmsweb.mapper.SystemNotificationMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.model.domain.Message;
import com.hsb.mvpmsweb.model.domain.SystemNotification;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.payload.FriendListResponseData;
import com.hsb.mvpmsweb.model.payload.FriendResponseData;
import com.hsb.mvpmsweb.model.payload.message.ChatListResponseData;
import com.hsb.mvpmsweb.model.payload.message.ChatResponseData;
import com.hsb.mvpmsweb.model.payload.request.AddFriendRequest;
import com.hsb.mvpmsweb.service.ChatService;
import com.hsb.mvpmsweb.service.ShareService;
import com.hsb.mvpmsweb.websocket.listener.ChanneListener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Service
@Transactional(rollbackFor = Exception.class)
public class ChatServiceImpl implements ChatService {

	private static final Logger logger = LoggerFactory.getLogger("WebSocket");
	
	@Autowired
	private ChatMapper chatMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private ShareService shareService;
	
	@Autowired
	private SystemNotificationMapper systemNotificationMapper;
	
	@Override
	public void like(JSONObject param, ChannelHandlerContext ctx) {
		singleSend(param, ctx);
	}
	
	@Override
	public void comments(JSONObject param, ChannelHandlerContext ctx) {
		singleSend(param, ctx);
	}
	
	@Override
	public void followMe(JSONObject param, ChannelHandlerContext ctx) {
		singleSend(param, ctx);
	}
	
	@Override
	public void atFriend(JSONObject param, ChannelHandlerContext ctx) {
		singleSend(param, ctx);
	}
	
	@Override
	public void addFriendRequest(JSONObject param, ChannelHandlerContext ctx) {
		singleSend(param, ctx);
	}
	
	@Override
	public void addFriendResponse(JSONObject param, ChannelHandlerContext ctx) {
		try {
			String fid = (String) param.get("fromUserId");
			Integer fromUserId = Integer.parseInt(fid);
			
			String toId = (String)param.get("toUserId");
			Integer toUserId = Integer.parseInt(toId);
			logger.info("{} add friend to {}", fid, toId);
			
			String confirm = (String)param.get("confirm");
			if ("Y".equals(confirm)) {
				AddFriendRequest addFriendRequest = new AddFriendRequest();
				addFriendRequest.setUserId(fromUserId);
				addFriendRequest.setFanId(toUserId);
				addFriendRequest.setComfirm(true);
				shareService.addFriend(addFriendRequest);
			}
			singleSend(param, ctx);
			
		} catch (Exception e) {
			logger.error("add friend fail", e);
			ctx.channel().writeAndFlush(new TextWebSocketFrame("add friend fail"));
		}
	}
	
	@Override
	public void online(JSONObject param, ChannelHandlerContext ctx) {
		try {
			String id = (String) param.get("fromUserId");
			Integer userId = Integer.parseInt(id);
			WebSocketConstant.onlineUserMap.put(userId, ctx);
			WebSocketConstant.offlineUserMap.put(ctx, userId);
			logger.info("{} online", userId);
			
			//send system notice
			sendSystemMessage(userId, ctx);
			
			//上下线提示
			hintToFriend(userId, ctx);
			
			//接收离线消息
			List<Message> socialList = getChatRecodeByIsRead(userId, "N");
			if (socialList.size() > 0) {
				sendOfflineMessage(socialList, ctx);
			}
		} catch (Exception e) {
			logger.error("online fail", e);
		}
	}
	
	private void sendSystemMessage(Integer userId, ChannelHandlerContext ctx) {
		try {
			//all system message
			List<SystemNotification> notificaitonList = systemNotificationMapper.selectList(new QueryWrapper<SystemNotification>());
			
			Map<String, Object> map = new HashMap<>();
			map.put("nickName", "Yours Message");
			map.put("userImgPath", "no path");
			map.put("createTime", LocalDateTime.now());
			map.put("toUserId", userId);
			map.put("type", MessageConstants.MESSAGE_TYPE_SYSTEM);
			//already send system message
			List<Message> messageList = getChatMessage(null, userId, MessageConstants.MESSAGE_TYPE_SYSTEM);
			if (messageList.size() > 0) {
				List<String> messages = new ArrayList<String>();
				messageList.forEach(m -> messages.add(m.getMsg()));
				//not send system message
				List<SystemNotification> notSendMessages = notificaitonList.stream().filter(nt -> !messages.contains(nt.getNotification())).collect(Collectors.toList());
				notSendMessages.forEach(ns -> {
					map.put("content", ns.getNotification());
					sendMessage(ctx, JSONObject.toJSONString(map));
					insertChat(null, userId, ns.getNotification(), "Y", MessageConstants.MESSAGE_TYPE_SYSTEM);
				});
			}else {
				//send system to new user
				notificaitonList.forEach(m -> {
					map.put("content", m.getNotification());
					sendMessage(ctx, JSONObject.toJSONString(map));
					insertChat(null, userId, m.getNotification(), "Y", MessageConstants.MESSAGE_TYPE_SYSTEM);
				});
			}
		} catch (Exception e) {
			logger.error("send system message fail", e);
		}
	}
	
/*	
	private Map<String, Object> systemMessage(String message){
		Map<String, Object> map = new HashMap<>();
		try {
			
		} catch (Exception e2) {
			logger.error("get all system message "+ e2.getMessage());
		}
		return map;
	}*/
	
	@Override
	public void downline(JSONObject param) {
		String id = (String) param.get("fromUserId");
		Integer userId = Integer.parseInt(id);
		WebSocketConstant.onlineUserMap.remove(userId);
	}
	
	@Override
	public void offline(ChannelHandlerContext ctx) {
		try {
			WebSocketConstant.onlineUserMap.values().removeIf(v -> v == ctx);
			/*Map<String, Object> map = new HashMap<>();
			map.put("type", "offline");*/
			Integer userId = WebSocketConstant.offlineUserMap.get(ctx);
			//map.put("userId", userId);
			if (userId != null) {
				hintToFriend(userId, ctx);
			}
		} catch (Exception e) {
			logger.error("offline fail", e);
		}
	}
	
	private void hintToFriend(Integer userId, ChannelHandlerContext ctx) {
		try {
			List<FriendResponseData> onlineFriendList = getOnlineFriendList(userId);
			logger.info("Hint To Friend Online Or Offline oline Friend List:{}", onlineFriendList);
			
			if (onlineFriendList != null && onlineFriendList.size() > 0) {
				UserInfo userInfo = null;
				try {
					userInfo = getUserInfo(String.valueOf(userId));
				} catch (MvpUserException e1) {
					logger.error("UserInfo Find Exception", e1);
				}
				//Set<Integer> set = new HashSet<Integer>();
				for (FriendResponseData e : onlineFriendList) {
					 
					Integer fanId = e.getFanId();
					
					logger.info("UserInfo value:{}", userInfo);
					//在线好友
				//	set.add(fanId);
					//好友上/下线
					ChannelHandlerContext ctxToFriend = WebSocketConstant.onlineUserMap.get(fanId);
					
					Map<String, Object> map = new HashMap<>();
					if (!WebSocketConstant.onlineUserMap.containsKey(WebSocketConstant.offlineUserMap.get(ctx))) {
						map.put("type", "offline");
					}else{
						map.put("type", "online");
					}
					map.put("userId", userId);
					map.put("nickName", userInfo.nickName());
					map.put("userImgPath", userInfo.userImgPath());
					logger.info("send message to hint friend my user id {} {}", userId, map.get("type"));
					sendMessage(ctxToFriend, JSONObject.toJSONString(map));
				}
			//	Map<String, Set<Integer>> map = new HashMap<>();
				//map.put("userId", set);
				//logger.info("hint friend online friends {}", JSONObject.toJSONString(map));
				//sendMessage(ctx, JSONObject.toJSONString(map));
			}
		} catch (Exception e2) {
			logger.error("hintToFriend fail", e2);
		}
	}
	
	private void sendOfflineMessage(List<Message> socialList, ChannelHandlerContext ctx) {
		try {
			logger.info("send offline message to friend");
			for (Message socialMessage : socialList) {
				Integer id = socialMessage.getFromUserId();
				String fromUserId = String.valueOf(id);
				Map<String, Object> map = new HashMap<>();
				map.put("createTime", LocalDateTime.now());
				map.put("fromUserId", socialMessage.getFromUserId());
				map.put("toUserId", socialMessage.getToUserId());
				map.put("content", socialMessage.getMsg());
				String type = socialMessage.getMsgType();
				map.put("type", type);
				
				UserInfo userInfo = getUserInfo(fromUserId);
				map.put("nickName", userInfo.nickName());
				map.put("userImgPath", userInfo.userImgPath());
				
				sendMessage(ctx, JSONObject.toJSONString(map));
				
				Message message = new Message();
				Integer entityId = socialMessage.getEntityId();
				message.setEntityId(entityId);
				if (!"chat".equals(type)) {
					chatMapper.deleteById(entityId);
				}else {
					message.setIsRead("Y");
					chatMapper.updateById(message);
				}
			}
		} catch (Exception e) {
			logger.error("send offline message fail", e);
		}
	}
	
	@Override
	public void singleSend(JSONObject param, ChannelHandlerContext ctx) {
		try {
			String fid = (String) param.get("fromUserId");
			Integer fromUserId = Integer.parseInt(fid);
			
			String toId = (String)param.get("toUserId");
			Integer toUserId = Integer.parseInt(toId);
	        String content = (String)param.get("content");
	        
	        String type = (String)param.get("type");
	        
	        String confirm = (String)param.get("confirm");
	        
			String sendMessage = null;
			ChannelHandlerContext toUserCtx = WebSocketConstant.onlineUserMap.get(toUserId);

			Map<String, Object> map =  new HashMap<>();
			map.put("createTime", LocalDateTime.now());
			map.put("fromUserId", fromUserId);
			map.put("toUserId", toUserId);
			map.put("content", content);
			map.put("type", type);
			map.put("confirm", confirm);
			UserInfo userInfo = getUserInfo(fid);
			map.put("nickName", userInfo.nickName());
			map.put("userImgPath", userInfo.userImgPath());
			
			String isRead ="N";
			logger.info("toUserCtx info {}", toUserCtx);
			logger.info("online user list: {}", WebSocketConstant.onlineUserMap);
			logger.info("{} send message to {} : {}", fid, toId, JSONObject.toJSONString(map));
			if (toUserCtx != null) {
				sendMessage = JSONObject.toJSONString(map);
				logger.info("toUserCtx != null {} send message to {} : {}", fid, toId, sendMessage);
				sendMessage(toUserCtx, sendMessage);
				if (ChanneListener.isSuccess) {
					isRead ="Y";
				}
			}
			
			if (("chat".equals(type) || toUserCtx == null)) {
				List<Message> chatMessage = new ArrayList<>();
				if (!"chat".equals(type)) {
					chatMessage = getChatMessage(fromUserId, toUserId, type);
				}
				if ("chat".equals(type) || chatMessage.size() == 0) {
					insertChat(fromUserId, toUserId, content, isRead, type);
				}
			}
			
		/*	if (!(("at".equals(type)
					||"follower".equals(type)
					||"comments".equals(type)
					||"like".equals(type)
					||"add_request".equals(type)) 
					&& toUserCtx != null)) {
				List<Message> chatMessage = new ArrayList<>();
				if ("add_request".equals(type)) {
					chatMessage = getChatMessage(fromUserId, toUserId, type);
				}
				if (chatMessage.size() == 0 ) {
					insertChat(fromUserId, toUserId, content, isRead, type);
				}
			}*/
			
		} catch (Exception e) {
			logger.error("send message fail", e);
		}
	}
	
	public void insertChat(Integer fromUserId, Integer toUserId, String content, String isRead, String type) {
		try {
			logger.info("Add Chat Message To Databases");
	        Message message = new Message();
	        message.setFromUserId(fromUserId);
			message.setToUserId(toUserId);
			message.setMsg(content);
			message.setMsgType(type);
			message.setIsRead(isRead);
			chatMapper.insert(message);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void sendMessage(ChannelHandlerContext ctx, String message) {
		try {
			ChannelFuture future = ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
			future.addListener(new ChanneListener());
		} catch (Exception e) {
			logger.info("send message fail", e);
		}
	}
	
	private List<Message> getChatMessage(Integer fromUserId, Integer toUserId, String messageType) {
		List<Message> messageList = new ArrayList<>();
		try {
			Map<String, Object> params = new HashMap<>();
			params.put(MessageConstants.MESSAGE_TYPE, messageType);
			if (null != fromUserId) {
				params.put(MessageConstants.MESSAGE_FROM_USER_ID, fromUserId);
			}
			if (null != toUserId) {
				params.put(MessageConstants.MESSAGE_TO_USER_ID, toUserId);
			}
			Wrapper<Message> message = new QueryWrapper<Message>().allEq(params);
			messageList = chatMapper.selectList(message);
		} catch (Exception e) {
			logger.error("Not Get Chat Message", e);
		}
		return messageList;
	}
	
	@Override
	public ChatListResponseData getChatRecode(Integer fromUserId, Integer toUserId, String messageType) {
		ChatListResponseData listResponseData = null;
		try {
			listResponseData = new ChatListResponseData();
			List<Message> messageList = getChatMessage(fromUserId, toUserId, messageType);
			if (messageList.size() > 0) {
				List<ChatResponseData> dataList = new ArrayList<>();
				messageList.forEach(e -> dataList.add(toChatResponseData(e)));
				listResponseData.setMessageList(dataList);
			}
		} catch (Exception e2) {
			logger.error("Not Get Chat Recode", e2);
		}
		return listResponseData;
	}

	private ChatResponseData toChatResponseData(Message message) {
		ChatResponseData data = new ChatResponseData();
		data.setCreateTime(LocalDateTime.now());
		Integer fromUserId = message.getFromUserId();
		data.setFromUserId(fromUserId);
		Integer toUserId = message.getToUserId();
		data.setToUserId(toUserId);
		data.setMessage(message.getMsg());
		data.setMsgType(message.getMsgType());
		return data;
	}

	private List<Message> getChatRecodeByIsRead(Integer userId, String isRead) {
		List<Message> socialList = new ArrayList<>();
		try {
			logger.info("start getChatRecodeByIsRead socialList value: {}", socialList);
			Map<String, Object> params = new HashMap<>();
			//params.put(MessageConstants.MESSAGE_TYPE, MessageConstants.MESSAGE_TYPE_CHAT);
			params.put(MessageConstants.MESSAGE_TO_USER_ID, userId);
			params.put(MessageConstants.MESSAGE_IS_READ, isRead);
			Wrapper<Message> message = new QueryWrapper<Message>().allEq(params).and(m->m.or().ne("MSG_TYPE", "system"));
			socialList = chatMapper.selectList(message);
		} catch (Exception e) {
			logger.error("Not Get Chat Recode", e);
		}
		logger.info("start getChatRecodeByIsRead end");
		return socialList;
	}

	private List<FriendResponseData> getOnlineFriendList(Integer userId) {
		List<FriendResponseData> friendList = new ArrayList<>();
		try {
			logger.info("start getOnlineFriendList friendList value: {}", friendList);
			FriendListResponseData friends = shareService.getFriendList(userId, null);
			friendList = friends.getFansList();
			Set<Integer> keys = WebSocketConstant.onlineUserMap.keySet();
			for (int i = 0; i < friendList.size(); i++) {
				Integer id = friendList.get(i).getFanId();
				if (!keys.contains(id)) {
					friendList.remove(i);
					i--;
				}
			}
		} catch (Exception e) {
			logger.error("Not Get Online Friend", e);
		}
		logger.info("end getOnlineFriendList");
		return friendList;
	}
	
	@Override
	public boolean checkIsFriend(JSONObject param) {
		boolean flag = false;
		try {
			logger.info("start checkIsFriend JSONObject: {}",param);
			String fid = (String) param.get("fromUserId");
			Integer fromUserId = Integer.parseInt(fid);
			
			String toId = (String)param.get("toUserId");
			Integer toUserId = Integer.parseInt(toId);
			FriendListResponseData friends = shareService.getFriendList(fromUserId, null);
			List<FriendResponseData> friendList = friends.getFansList();
			flag = friendList.stream().anyMatch(t -> t.getFanId().equals(toUserId));
		} catch (Exception e) {
			logger.error("check is or not friedn fail", e);
		}
		logger.info("end checkIsFriend JSONObject: {}",param);
		return flag;
	}
	
	private UserInfo getUserInfo(String userId) throws MvpUserException {
		UserInfo userInfo = userInfoMapper.selectById(userId);
		if (userInfo != null) {
			return userInfo;
		} else {
			throw new MvpUserException(MvpUserExceptionCode.ERROR_998_11);
		}
	}
	
	@Override
	public boolean checkUser(JSONObject param, ChannelHandlerContext ctx) {
		boolean flag = false;
		try {
			logger.info("start checkUser JSONObject: {}, ChannelHandlerContext: {}",param,ctx);
			String fid = (String) param.get("fromUserId");
			String toId = (String)param.get("toUserId");
			if (fid != null) {
				Integer fromUserId = Integer.parseInt(fid);
				UserInfo userInfo = userInfoMapper.selectById(fromUserId);
				if (null == userInfo) {
					sendMessage(ctx, "fromUserId is not exist");
					return flag;
				}
			}
			if (toId != null) {
				Integer toUserId = Integer.parseInt(toId);
				UserInfo userInfo = userInfoMapper.selectById(toUserId);
				if (null == userInfo) {
					sendMessage(ctx, "toUserId is not exist");
					return flag;
				}
			}
			flag = true;
		} catch (Exception e) {
			logger.error("checker user", e);
		}
		logger.info("end checkUser flag :{}",flag);
		return flag;
	}
	
}