/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.hsb.mvpmsweb.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hsb.mvpmsweb.api.annotation.JwtIgnore;
import com.hsb.mvpmsweb.api.annotation.NeedToken;
import com.hsb.mvpmsweb.api.annotation.NeedTokenAndPageable;
import com.hsb.mvpmsweb.api.annotation.ResponseConfig;
import com.hsb.mvpmsweb.api.exception.MvpAssistantException;
import com.hsb.mvpmsweb.constant.HttpStatusCodeConstants;
import com.hsb.mvpmsweb.constant.Swagger2Constants;
import com.hsb.mvpmsweb.model.payload.assistant.request.SearchRequest;
import com.hsb.mvpmsweb.model.payload.assistant.response.GetOneResultResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.InitDataResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchPhotosResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchProductsResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchUsersResponse;
import com.hsb.mvpmsweb.model.payload.assistant.response.SearchVideosResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = Swagger2Constants.TAG_NM_ASSISTANT, description = Swagger2Constants.TAG_DESC_ASSISTANT)
public interface MvpAssistantModuleApi {
	
    @ApiOperation(value = "Search Photos", nickname = "searchPhotos", notes = "Search Photos(User NickName)", response = SearchPhotosResponse.class)
    @ApiOperationSupport(order = 3, author = "mojianheng@formssi.com")
    @NeedTokenAndPageable
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = SearchPhotosResponse.class))
    @PostMapping(value = "/photos", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SearchPhotosResponse> searchPhotos(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "keyWord of search(User NickName)", required = true)@Validated @RequestBody SearchRequest searchRequest);

    
    @ApiOperation(value = "Search Products", nickname = "searchProducts", notes = "Search Products", response = SearchProductsResponse.class)
    @ApiOperationSupport(order = 5, author = "mojianheng@formssi.com")
    @NeedTokenAndPageable
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = SearchProductsResponse.class))
    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SearchProductsResponse> searchProducts(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "keyWord of search", required = true)@Validated @RequestBody SearchRequest searchRequest) throws MvpAssistantException;
    
    
    @ApiOperation(value = "Search Users", nickname = "searchUsers", notes = "Search Users(User NickName)", response = SearchUsersResponse.class)
    @ApiOperationSupport(order = 4, author = "mojianheng@formssi.com")
    @NeedTokenAndPageable
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = SearchUsersResponse.class))
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SearchUsersResponse> searchUsers(@ApiIgnore @NonNull Pageable pageable, @ApiIgnore @RequestHeader("Authorization") String token, @ApiParam(value = "keyWord of search(User NickName)", required = true)@Validated @RequestBody SearchRequest searchRequest) throws MvpAssistantException;
    
    
    @ApiOperation(value = "Search Videos", nickname = "searchVideos", notes = "Search Videos(User NickName)", response = SearchVideosResponse.class)
    @ApiOperationSupport(order = 2, author = "mojianheng@formssi.com")
    @NeedTokenAndPageable
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = SearchVideosResponse.class))
    @PostMapping(value = "/videos", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SearchVideosResponse> searchVideos(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "keyWord of search(User NickName)", required = true)@Validated @RequestBody SearchRequest searchRequest);
    
    
    @ApiOperation(value = "Get one search result", nickname = "getOneResult", notes = "Get one search result(User NickName)", response = GetOneResultResponse.class)
    @ApiOperationSupport(order = 6, author = "mojianheng@formssi.com")
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = GetOneResultResponse.class))
    @PostMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetOneResultResponse> getOneResult(@ApiIgnore @PageableDefault(page = 0, size = 1) Pageable pageable, @ApiIgnore @RequestHeader("Authorization") String token, @ApiParam(value = "keyWord of search(User NickName)", required = true)@Validated @RequestBody SearchRequest searchRequest) throws MvpAssistantException;
    
    
    @JwtIgnore
    @ApiOperationSupport(order = 1, author = "mojianheng@formssi.com")
    @ApiOperation(value = "Run Data Initialization", nickname = "initData", notes = "Run Data Initialization", response = InitDataResponse.class)
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = InitDataResponse.class))
    @GetMapping(value = "/loadInitData", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InitDataResponse> initData();
    
}