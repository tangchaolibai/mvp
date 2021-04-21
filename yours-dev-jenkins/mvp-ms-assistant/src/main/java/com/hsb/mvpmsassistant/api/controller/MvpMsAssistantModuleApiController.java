package com.hsb.mvpmsassistant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsassistant.api.MvpMsAssistantModuleApi;
import com.hsb.mvpmsassistant.model.payload.GetOneResultResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchPhotosResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchProductsResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchUsersResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchVideosResponseData;
import com.hsb.mvpmsassistant.model.payload.request.SearchRequest;
import com.hsb.mvpmsassistant.model.payload.response.GetOneResultResponse;
import com.hsb.mvpmsassistant.model.payload.response.InitDataResponse;
import com.hsb.mvpmsassistant.model.payload.response.ResponseResult;
import com.hsb.mvpmsassistant.model.payload.response.SearchPhotosResponse;
import com.hsb.mvpmsassistant.model.payload.response.SearchProductsResponse;
import com.hsb.mvpmsassistant.model.payload.response.SearchUsersResponse;
import com.hsb.mvpmsassistant.model.payload.response.SearchVideosResponse;
import com.hsb.mvpmsassistant.serv.AssistantService;

@RestController
@RequestMapping(value = "search")
public class MvpMsAssistantModuleApiController implements MvpMsAssistantModuleApi {

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
	public ResponseEntity<SearchProductsResponse> searchProducts(Pageable pageable, SearchRequest searchRequest) {
		SearchProductsResponseData data = assistantService.searchProducts(pageable, searchRequest);
		
		SearchProductsResponse response = new SearchProductsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SearchUsersResponse> searchUsers(Pageable pageable, SearchRequest searchRequest) {
		SearchUsersResponseData data = assistantService.searchUsers(pageable, searchRequest);
		
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
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GetOneResultResponse> getOneResult(Pageable pageable, SearchRequest searchRequest) {
		GetOneResultResponseData data = assistantService.getOneResult(pageable, searchRequest);
		
		GetOneResultResponse response = new GetOneResultResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		
		return ResponseEntity.ok(response);
	}

}
