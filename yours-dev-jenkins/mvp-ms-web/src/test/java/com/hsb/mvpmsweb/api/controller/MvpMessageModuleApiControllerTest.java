package com.hsb.mvpmsweb.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsb.mvpmsweb.api.MvpMessageModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpMessageException;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.constant.MessageConstants;
import com.hsb.mvpmsweb.model.payload.message.response.CountResponse;
import com.hsb.mvpmsweb.model.payload.message.response.DelNotificationResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetATResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetCommentsResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetLikesResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetNotificaitonsResponse;
import com.hsb.mvpmsweb.model.payload.message.response.GetSystemResponse;
import com.hsb.mvpmsweb.model.payload.request.MessageIdRequest;
import com.hsb.mvpmsweb.service.ChatService;
import com.hsb.mvpmsweb.service.MessageService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
public class MvpMessageModuleApiControllerTest {

	@InjectMocks
	private MvpMessageModuleApi api = new MvpMessageModuleApiController();

	@Mock
	private MessageService messageService;
	
	@Mock
	private ChatService chatService;
	
	@Test
	public void testGetUnreadNotificationsCount() throws MvpMessageException {
		ResponseEntity<CountResponse> response = api.getUnreadNotificationsCount(null);
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testGetNotifications() throws MvpMessageException {
		ResponseEntity<GetNotificaitonsResponse> response = api.getNotifications(null, null);
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testDeleteNotificaiton() throws MvpMessageException {
		ResponseEntity<DelNotificationResponse> response = api.deleteNotificaiton(new MessageIdRequest());
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testGetSystemMessage() throws MvpMessageException {
		ResponseEntity<GetSystemResponse> response = api.getSystemMessage(null, null);
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testGetLikes() throws MvpMessageException, MvpWebException {
		ResponseEntity<GetLikesResponse> response = api.getLikes(null, null);
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetATMe() throws MvpMessageException, MvpWebException {
		ResponseEntity<GetATResponse> response = api.getATMe(null, null);
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testGetComments() throws MvpMessageException, MvpWebException {
		ResponseEntity<GetCommentsResponse> response = api.getComments(null, null);
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testChat() throws MvpMessageException {/*
		ResponseEntity<ChatResponse> response = api.chat(new ChatRequest());
		Assertions.assertEquals(MessageConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(MessageConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	*/}
	
}
