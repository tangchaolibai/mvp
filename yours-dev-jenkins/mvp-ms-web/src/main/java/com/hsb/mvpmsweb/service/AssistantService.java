package com.hsb.mvpmsweb.service;

import org.springframework.data.domain.Pageable;

import com.hsb.mvpmsweb.api.exception.MvpAssistantException;
import com.hsb.mvpmsweb.model.payload.assistant.GetOneResultResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchPhotosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchProductsResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchUsersResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchVideosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.request.SearchRequest;
import com.hsb.mvpmsweb.model.payload.assistant.response.InitDataResponse;

public interface AssistantService {
	
	public SearchVideosResponseData searchVideos(Pageable pageable, SearchRequest searchRequest);
	public SearchPhotosResponseData searchPhotos(Pageable pageable, SearchRequest searchRequest);
	public SearchUsersResponseData searchUsers(Pageable pageable, String token, SearchRequest searchRequest) throws MvpAssistantException;
	public SearchProductsResponseData searchProducts(Pageable pageable, SearchRequest searchRequest) throws MvpAssistantException;
	public GetOneResultResponseData getOneResult(Pageable pageable, String token, SearchRequest searchRequest) throws MvpAssistantException;
	public InitDataResponse initData();
	
}
