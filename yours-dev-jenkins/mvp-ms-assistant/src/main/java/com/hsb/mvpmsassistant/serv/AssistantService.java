package com.hsb.mvpmsassistant.serv;

import org.springframework.data.domain.Pageable;

import com.hsb.mvpmsassistant.model.payload.GetOneResultResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchPhotosResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchProductsResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchUsersResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchVideosResponseData;
import com.hsb.mvpmsassistant.model.payload.request.SearchRequest;
import com.hsb.mvpmsassistant.model.payload.response.InitDataResponse;

public interface AssistantService {
	
	public SearchVideosResponseData searchVideos(Pageable pageable, SearchRequest searchRequest);
	public SearchPhotosResponseData searchPhotos(Pageable pageable, SearchRequest searchRequest);
	public SearchUsersResponseData searchUsers(Pageable pageable, SearchRequest searchRequest);
	public SearchProductsResponseData searchProducts(Pageable pageable, SearchRequest searchRequest);
	public GetOneResultResponseData getOneResult(Pageable pageable, SearchRequest searchRequest);
	public InitDataResponse initData();
	
}
