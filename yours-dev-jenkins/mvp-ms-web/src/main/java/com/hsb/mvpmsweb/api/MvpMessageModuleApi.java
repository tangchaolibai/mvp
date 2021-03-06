/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.hsb.mvpmsweb.api;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.hsb.mvpmsweb.api.annotation.JwtIgnore;
import com.hsb.mvpmsweb.api.annotation.NeedToken;
import com.hsb.mvpmsweb.api.annotation.NeedTokenAndPageable;
import com.hsb.mvpmsweb.api.annotation.ResponseConfig;
import com.hsb.mvpmsweb.api.exception.MvpMessageException;
import com.hsb.mvpmsweb.constant.HttpStatusCodeConstants;
import com.hsb.mvpmsweb.constant.Swagger2Constants;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = Swagger2Constants.TAG_NM_MESSAGE, description = Swagger2Constants.TAG_DESC_MESSAGE)
public interface MvpMessageModuleApi {

	@ApiOperation(value = "Get Unread Notifications Count", nickname = "getUnreadNotificationsCount", notes = "Get Unread Notifications Count", response = CountResponse.class)
	@ApiOperationSupport(order = 2, author = "mojianheng@formssi.com")
	@NeedToken
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = CountResponse.class))
    @GetMapping(value = "/notifications/count", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CountResponse> getUnreadNotificationsCount(@ApiParam(value = "user Id", required = true) @RequestParam(value = "userId", required = true) Integer userId);
	

	@ApiOperation(value = "Get Notifications", nickname = "getNotifications", notes = "Get Notifications", response = GetNotificaitonsResponse.class)
	@ApiOperationSupport(order = 3, author = "mojianheng@formssi.com")
	@NeedTokenAndPageable
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = GetNotificaitonsResponse.class))
	@GetMapping(value = "/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetNotificaitonsResponse> getNotifications(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "user Id", required = true) @RequestParam(value = "userId", required = true) Integer userId);
	
	
	@ApiOperation(value = "Delete Notificaiton", nickname = "deleteNotificaiton", notes = "Delete Notificaiton", response = DelNotificationResponse.class)
	@ApiOperationSupport(order = 4, author = "mojianheng@formssi.com")
	@NeedToken
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = DelNotificationResponse.class))
	@DeleteMapping(value = "/notification/del", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DelNotificationResponse> deleteNotificaiton(@ApiParam(value = "message id", required = true) @Validated @RequestBody MessageIdRequest request) throws MvpMessageException;

	
	@ApiOperation(value = "Get System Message", nickname = "getSystemMessage", notes = "Get System Message", response = GetSystemResponse.class)
	@ApiOperationSupport(order = 5, author = "mojianheng@formssi.com")
	@NeedTokenAndPageable
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = GetSystemResponse.class))
	@GetMapping(value = "/notifications/system", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetSystemResponse> getSystemMessage(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "user id", required = true) @RequestParam(value = "userId", required = true) Integer userId);
	
	
	@ApiOperation(value = "Get Likes", nickname = "getLikes", notes = "Get Likes", response = GetLikesResponse.class)
	@ApiOperationSupport(order = 6, author = "mojianheng@formssi.com")
	@NeedTokenAndPageable
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = GetLikesResponse.class))
	@GetMapping(value = "/likes", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetLikesResponse> getLikes(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "user Id", required = true) @RequestParam(value = "userId", required = true) Integer userId);
	
	
	@ApiOperation(value = "Get AT Me", nickname = "getATMe", notes = "Get AT Me", response = GetATResponse.class)
	@ApiOperationSupport(order = 7, author = "mojianheng@formssi.com")
	@NeedTokenAndPageable
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = GetATResponse.class))
	@GetMapping(value = "/ATme", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetATResponse> getATMe(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "user Id", required = true) @RequestParam(value = "userId", required = true) Integer userId);

		
	@ApiOperation(value = "Get Comments", nickname = "getComments", notes = "Get Comments", response = GetCommentsResponse.class)
	@ApiOperationSupport(order = 8, author = "mojianheng@formssi.com")
	@NeedTokenAndPageable
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = GetCommentsResponse.class))
    @GetMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetCommentsResponse> getComments(@ApiIgnore @NonNull Pageable pageable, @ApiParam(value = "user Id", required = true) @RequestParam(value = "userId", required = true) Integer userId);
	
	
	@ApiOperation(value = "Chat", nickname = "chat", notes = "Chat", response = ChatResponse.class)
	@ApiOperationSupport(order = 9)
	@NeedToken
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = ChatResponse.class))
    @GetMapping(value = "/message/chat", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ChatResponse> chat(Integer fromUserId, Integer toUserId, String messageType);
	
	
	@JwtIgnore
	@ApiOperation(value = "Run Data Initialization", nickname = "initData", notes = "Run Data Initialization", response = InitDataResponse.class)
	@ApiOperationSupport(order = 1, author = "mojianheng@formssi.com")
	@ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = InitDataResponse.class))
	@GetMapping(value = "/loadInitData", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InitDataResponse> initData();
    
}
