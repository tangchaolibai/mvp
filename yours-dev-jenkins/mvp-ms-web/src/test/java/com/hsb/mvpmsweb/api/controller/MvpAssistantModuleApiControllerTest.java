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

import com.hsb.mvpmsweb.api.MvpAssistantModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpAssistantException;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.constant.AssistantConstants;
import com.hsb.mvpmsweb.model.payload.assistant.request.SearchRequest;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchPhotosResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchProductsResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchUsersResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchVideosResponse;
import com.hsb.mvpmsweb.service.AssistantService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
public class MvpAssistantModuleApiControllerTest {
	
	@InjectMocks
	private MvpAssistantModuleApi api = new MvpAssistantModuleApiController();

	@Mock
	private AssistantService assistantService;

	@Test
	public void testSearchPhotos() throws MvpAssistantException {
		ResponseEntity<SearchPhotosResponse> response = api.searchPhotos(null, new SearchRequest());
		Assertions.assertEquals(AssistantConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(AssistantConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}

	@Test
	public void testSearchProducts() throws MvpAssistantException {
		ResponseEntity<SearchProductsResponse> response = api.searchProducts(null, new SearchRequest());
		Assertions.assertEquals(AssistantConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(AssistantConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}

	@Test
	public void testSearchUsers() throws MvpAssistantException, MvpWebException {
		ResponseEntity<SearchUsersResponse> response = api.searchUsers(null, "Bearer ", new SearchRequest());
		Assertions.assertEquals(AssistantConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(AssistantConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}

	@Test
	public void testSearchVideos() throws MvpAssistantException {
		ResponseEntity<SearchVideosResponse> response = api.searchVideos(null, new SearchRequest());
		Assertions.assertEquals(AssistantConstants.CODE_SUCCESS, response.getBody().getResult().getCode());
		Assertions.assertEquals(AssistantConstants.MSG_SUCCESS, response.getBody().getResult().getMessage());
	}
	
}
