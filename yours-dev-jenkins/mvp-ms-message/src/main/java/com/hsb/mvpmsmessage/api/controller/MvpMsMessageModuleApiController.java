package com.hsb.mvpmsmessage.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsmessage.api.MvpMsMessageModuleApi;
import com.hsb.mvpmsmessage.model.payload.ChatListResponseData;
import com.hsb.mvpmsmessage.model.payload.CountResponseData;
import com.hsb.mvpmsmessage.model.payload.GetATResponseData;
import com.hsb.mvpmsmessage.model.payload.GetCommentsResponseData;
import com.hsb.mvpmsmessage.model.payload.GetLikesResponseData;
import com.hsb.mvpmsmessage.model.payload.GetNotificaitonsResponseData;
import com.hsb.mvpmsmessage.model.payload.GetSystemResponseData;
import com.hsb.mvpmsmessage.model.payload.request.ChatRequest;
import com.hsb.mvpmsmessage.model.payload.response.ChatResponse;
import com.hsb.mvpmsmessage.model.payload.response.CountResponse;
import com.hsb.mvpmsmessage.model.payload.response.DelNotificationResponse;
import com.hsb.mvpmsmessage.model.payload.response.GetATResponse;
import com.hsb.mvpmsmessage.model.payload.response.GetCommentsResponse;
import com.hsb.mvpmsmessage.model.payload.response.GetLikesResponse;
import com.hsb.mvpmsmessage.model.payload.response.GetNotificaitonsResponse;
import com.hsb.mvpmsmessage.model.payload.response.GetSystemResponse;
import com.hsb.mvpmsmessage.model.payload.response.InitDataResponse;
import com.hsb.mvpmsmessage.model.payload.response.ResponseResult;
import com.hsb.mvpmsmessage.serv.ChatService;
import com.hsb.mvpmsmessage.serv.MessageService;

@RestController
public class MvpMsMessageModuleApiController implements MvpMsMessageModuleApi {

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
	public ResponseEntity<DelNotificationResponse> deleteNotificaiton(Integer msgId) {
		DelNotificationResponse response = new DelNotificationResponse(ResponseResult.DefaultSuccessResponse);
		messageService.deleteNotificaiton(msgId);
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
	public ResponseEntity<ChatResponse> chat(@Valid ChatRequest chatRequest) {
		ChatResponse response = new ChatResponse();
		ChatListResponseData data = chatService.chat(chatRequest);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<InitDataResponse> initData() {
		InitDataResponse response = messageService.initData();
		
		return ResponseEntity.ok(response);
	}
	
}
