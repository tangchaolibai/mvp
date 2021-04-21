package com.hsb.mvpmsweb.service;

import org.springframework.data.domain.Pageable;

import com.hsb.mvpmsweb.api.exception.MvpMessageException;
import com.hsb.mvpmsweb.model.payload.message.CountResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetATResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetCommentsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetLikesResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetNotificaitonsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetSystemResponseData;
import com.hsb.mvpmsweb.model.payload.message.response.InitDataResponse;

public interface MessageService {
	
	public CountResponseData getUnreadNotificationsCount(Integer userId);
	public GetNotificaitonsResponseData getNotifications(Pageable pageable, Integer userId);
	public void deleteNotificaiton(Integer msgId) throws MvpMessageException;
	public GetSystemResponseData getSystemMessage(Pageable pageable, Integer userId);
	public GetLikesResponseData getLikes(Pageable pageable, Integer userId);
	public GetATResponseData getATMe(Pageable pageable, Integer userId);
	public GetCommentsResponseData getComments(Pageable pageable, Integer userId);
	public InitDataResponse initData();
	
}
