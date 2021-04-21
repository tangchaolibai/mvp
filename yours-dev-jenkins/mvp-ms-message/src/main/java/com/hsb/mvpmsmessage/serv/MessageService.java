package com.hsb.mvpmsmessage.serv;

import org.springframework.data.domain.Pageable;

import com.hsb.mvpmsmessage.model.payload.CountResponseData;
import com.hsb.mvpmsmessage.model.payload.GetATResponseData;
import com.hsb.mvpmsmessage.model.payload.GetCommentsResponseData;
import com.hsb.mvpmsmessage.model.payload.GetLikesResponseData;
import com.hsb.mvpmsmessage.model.payload.GetNotificaitonsResponseData;
import com.hsb.mvpmsmessage.model.payload.GetSystemResponseData;
import com.hsb.mvpmsmessage.model.payload.response.InitDataResponse;

public interface MessageService {
	
	public CountResponseData getUnreadNotificationsCount(Integer userId);
	public GetNotificaitonsResponseData getNotifications(Pageable pageable, Integer userId);
	public void deleteNotificaiton(Integer msgId);
	public GetSystemResponseData getSystemMessage(Pageable pageable, Integer userId);
	public GetLikesResponseData getLikes(Pageable pageable, Integer userId);
	public GetATResponseData getATMe(Pageable pageable, Integer userId);
	public GetCommentsResponseData getComments(Pageable pageable, Integer userId);
	public InitDataResponse initData();
	
}
