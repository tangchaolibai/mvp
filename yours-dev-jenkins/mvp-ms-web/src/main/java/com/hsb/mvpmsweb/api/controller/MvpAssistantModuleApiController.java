package com.hsb.mvpmsweb.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsweb.api.MvpAssistantModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpAssistantException;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.GetOneResultResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchPhotosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchProductsResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchUsersResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchVideosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.request.SearchRequest;
import com.hsb.mvpmsweb.model.payload.assistant.response.GetOneResultResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.InitDataResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchPhotosResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchProductsResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchUsersResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchVideosResponse;
import com.hsb.mvpmsweb.service.AssistantService;

@RestController
@RequestMapping(value = "search")
public class MvpAssistantModuleApiController implements MvpAssistantModuleApi {

	@Autowired
	private AssistantService assistantService;
	
	@Override
	public ResponseEntity<SearchPhotosResponse> searchPhotos(Pageable pageable, SearchRequest searchRequest) {
		SearchPhotosResponseData data = assistantService.searchPhotos(pageable, searchRequest);
		SearchPhotosResponse response = new SearchPhotosResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SearchProductsResponse> searchProducts(Pageable pageable, SearchRequest searchRequest) throws MvpAssistantException {
		SearchProductsResponseData data = assistantService.searchProducts(pageable, searchRequest);
		SearchProductsResponse response = new SearchProductsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SearchUsersResponse> searchUsers(Pageable pageable, String token, SearchRequest searchRequest) throws MvpAssistantException {
		SearchUsersResponseData data = assistantService.searchUsers(pageable, token, searchRequest);
		SearchUsersResponse response = new SearchUsersResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SearchVideosResponse> searchVideos(Pageable pageable, SearchRequest searchRequest) {
		SearchVideosResponseData data = assistantService.searchVideos(pageable, searchRequest);
		SearchVideosResponse response = new SearchVideosResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<InitDataResponse> initData() {
		InitDataResponse response = assistantService.initData();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<GetOneResultResponse> getOneResult(Pageable pageable, String token, SearchRequest searchRequest) throws MvpAssistantException {
		GetOneResultResponseData data = assistantService.getOneResult(pageable, token, searchRequest);
		GetOneResultResponse response = new GetOneResultResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

}
