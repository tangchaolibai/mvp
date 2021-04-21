package com.hsb.mvpmsweb.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsweb.api.MvpMessageModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpMessageException;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.ChatListResponseData;
import com.hsb.mvpmsweb.model.payload.message.CountResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetATResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetCommentsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetLikesResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetNotificaitonsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetSystemResponseData;
import com.hsb.mvpmsweb.model.payload.message.response.ChatResponse;
import com.hsb.mvpmsweb.model.payload.message.response.CountResponse;
import com.hsb.mvpmsweb.model.payload.message.response.DelNotificationResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetATResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetCommentsResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetLikesResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetNotificaitonsResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetSystemResponse;
import com.hsb.mvpmsweb.model.payload.message.response.InitDataResponse;
import com.hsb.mvpmsweb.model.payload.request.MessageIdRequest;
import com.hsb.mvpmsweb.service.ChatService;
import com.hsb.mvpmsweb.service.MessageService;

@RestController
public class MvpMessageModuleApiController implements MvpMessageModuleApi {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ChatService chatService;

	@Override
	public ResponseEntity<CountResponse> getUnreadNotificationsCount(Integer userId) {
		CountResponseData data = messageService.getUnreadNotificationsCount(userId);
		CountResponse response = new CountResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetNotificaitonsResponse> getNotifications(Pageable pageable, Integer userId) {
		GetNotificaitonsResponseData data = messageService.getNotifications(pageable, userId);
		GetNotificaitonsResponse response = new GetNotificaitonsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<DelNotificationResponse> deleteNotificaiton(MessageIdRequest request) throws MvpMessageException {
		DelNotificationResponse response = new DelNotificationResponse(ResponseResult.DefaultSuccessResponse);
		messageService.deleteNotificaiton(request.msgId());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetSystemResponse> getSystemMessage(Pageable pageable, Integer userId) {
		GetSystemResponseData data = messageService.getSystemMessage(pageable, userId);
		GetSystemResponse response = new GetSystemResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetLikesResponse> getLikes(Pageable pageable, Integer userId) {
		GetLikesResponseData data = messageService.getLikes(pageable, userId);
		GetLikesResponse response = new GetLikesResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetATResponse> getATMe(Pageable pageable, Integer userId) {
		GetATResponseData data = messageService.getATMe(pageable, userId);
		GetATResponse response = new GetATResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetCommentsResponse> getComments(Pageable pageable, Integer userId) {
		GetCommentsResponseData data = messageService.getComments(pageable, userId);
		GetCommentsResponse response = new GetCommentsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ChatResponse> chat(Integer fromUserId, Integer toUserId, String messageType) {
		ChatResponse response = new ChatResponse();
		ChatListResponseData data = chatService.getChatRecode(fromUserId, toUserId, messageType);
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<InitDataResponse> initData() {
		InitDataResponse response = messageService.initData();
		return ResponseEntity.ok(response);
	}
	
}
