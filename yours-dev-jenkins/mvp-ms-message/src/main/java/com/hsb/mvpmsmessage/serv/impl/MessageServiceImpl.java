package com.hsb.mvpmsmessage.serv.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsmessage.constant.MessageConstants;
import com.hsb.mvpmsmessage.mapper.CommentMapper;
import com.hsb.mvpmsmessage.mapper.LikeMapper;
import com.hsb.mvpmsmessage.mapper.MessageMapper;
import com.hsb.mvpmsmessage.mapper.ReplyMapper;
import com.hsb.mvpmsmessage.mapper.UserMapper;
import com.hsb.mvpmsmessage.model.entity.Comment;
import com.hsb.mvpmsmessage.model.entity.Like;
import com.hsb.mvpmsmessage.model.entity.Message;
import com.hsb.mvpmsmessage.model.entity.Reply;
import com.hsb.mvpmsmessage.model.payload.ATResponseData;
import com.hsb.mvpmsmessage.model.payload.CommentResponseData;
import com.hsb.mvpmsmessage.model.payload.CountResponseData;
import com.hsb.mvpmsmessage.model.payload.GetATResponseData;
import com.hsb.mvpmsmessage.model.payload.GetCommentsResponseData;
import com.hsb.mvpmsmessage.model.payload.GetLikesResponseData;
import com.hsb.mvpmsmessage.model.payload.GetNotificaitonsResponseData;
import com.hsb.mvpmsmessage.model.payload.GetSystemResponseData;
import com.hsb.mvpmsmessage.model.payload.LikeResponseData;
import com.hsb.mvpmsmessage.model.payload.MessageResponseData;
import com.hsb.mvpmsmessage.model.payload.SystemResponseData;
import com.hsb.mvpmsmessage.model.payload.response.InitDataResponse;
import com.hsb.mvpmsmessage.model.payload.response.ResponseResult;
import com.hsb.mvpmsmessage.serv.MessageService;
import com.hsb.mvpmsmessage.util.MessageUtils;
import com.hsb.mvpmsuser.model.domain.UserInfo;

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	private ATResponseData toReplyResponseData(Reply reply) {
		ATResponseData responseData = new ATResponseData();
		if(reply != null) {
			responseData.setId(reply.entityId());
			responseData.setCommentId(reply.commentId());
			responseData.setFromUserId(reply.fromUserId());
			responseData.setToUserId(reply.toUserId());
			responseData.setReply(reply.reply());
			responseData.setCreationDateTime(reply.creationDateTime());
		}
		return responseData;
	}
	
	private CommentResponseData toCommentResponseData(Comment comment) {
		CommentResponseData responseData = new CommentResponseData();
		if(comment != null) {
			responseData.setId(comment.entityId());
			responseData.setShareId(comment.shareId());
			responseData.setFromUserId(comment.fromUserId());
			responseData.setMsg(comment.content());
			responseData.setCreationDateTime(comment.creationDateTime());
		}
		return responseData;
	}
	
	private LikeResponseData toLikeResponseData(Like like) {
		LikeResponseData responseData = new LikeResponseData();
		if(like != null) {
			responseData.setId(like.entityId());
			responseData.setShareId(like.shareId());
			responseData.setFromUserId(like.fromUserId());
			responseData.setCreationDateTime(like.creationDateTime());
		}
		return responseData;
	}
	
	private SystemResponseData toSystemResponseData(Message message) {
		SystemResponseData responseData = new SystemResponseData();
		if(message != null) {
			responseData.setId(message.entityId());
			responseData.setMsg(message.msg());
			responseData.setCreationDateTime(message.creationDateTime());
			responseData.setIsRead(message.isRead());
		}
		return responseData;
	}
	
	private MessageResponseData toMessageResponseData(Message message) {
		MessageResponseData responseData = new MessageResponseData();
		if(message != null) {
			responseData.setId(message.entityId());
			responseData.setMsgType(message.msgType());
			responseData.setMsg(message.msg());
			
			Integer fromUserId = message.fromUserId();
			responseData.setFromUserId(fromUserId);
			UserInfo user = userMapper.selectById(fromUserId);
			if(user != null) {
				responseData.setFromUserName(user.userName());
				responseData.setFromUserImgPath(user.userImgPath());
			}
		}
		return responseData;
	}

	@Override
	public CountResponseData getUnreadNotificationsCount(Integer userId) {
		CountResponseData result = new CountResponseData();
		
		Integer count = messageMapper.selectList(new QueryWrapper<Message>().
				eq(MessageConstants.MESSAGE_TO_USER_ID, userId).
				eq(MessageConstants.MESSAGE_IS_READ, MessageConstants.NO)).
					size();
		logger.info("Unread notificaitons count: [{}].", count);
		result.setCount(count);
		
		return result;
	}

	@Override
	public GetNotificaitonsResponseData getNotifications(Pageable pageable, Integer userId) {
		GetNotificaitonsResponseData result = new GetNotificaitonsResponseData();
		
		List<MessageResponseData> notificaitonResultList = new ArrayList<>();
		IPage<Message> messagePage = 
				messageMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), 
					new QueryWrapper<Message>().
						eq(MessageConstants.MESSAGE_TO_USER_ID, userId).
						orderByDesc(MessageConstants.CREATION_DATE_TIME));
		
		if(messagePage.getTotal() <= 0L) {
			logger.info("[Notifications] not found.");
		}
		
		messagePage.getRecords().forEach(m -> notificaitonResultList.add(toMessageResponseData(m)));
		result.setNotificaiton(notificaitonResultList);
		
		return result;
	}

	@Override
	public void deleteNotificaiton(Integer msgId) {
		Message delMessage = messageMapper.selectById(msgId);
		
		if(delMessage != null) {
			messageMapper.deleteById(delMessage.entityId());
			logger.info("Notification: [{}] deleted successfully.", delMessage.entityId());
		}else {
			logger.warn("[Notification] not found.");
		}
	}

	@Override
	public GetSystemResponseData getSystemMessage(Pageable pageable, Integer userId) {
		GetSystemResponseData result = new GetSystemResponseData();
		
		List<SystemResponseData> systemResultList = new ArrayList<>();
		IPage<Message> messagePage = 
				messageMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), 
					new QueryWrapper<Message>().
						eq(MessageConstants.MESSAGE_TO_USER_ID, userId).
						eq(MessageConstants.MESSAGE_TYPE, MessageConstants.MESSAGE_TYPE_SYSTEM).
						orderByDesc(MessageConstants.CREATION_DATE_TIME));
		
		if(messagePage.getTotal() <= 0L) {
			logger.info("[System message] not found.");
		}
		
		messagePage.getRecords().forEach(m -> systemResultList.add(toSystemResponseData(m)));
		result.setMessages(systemResultList);
		
		return result;
	}

	@Override
	public GetLikesResponseData getLikes(Pageable pageable, Integer userId) {
		GetLikesResponseData result = new GetLikesResponseData();
		
		List<LikeResponseData> likeResultList = new ArrayList<>();
		IPage<Like> likePage = 
				likeMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), 
					new QueryWrapper<Like>().
						eq(MessageConstants.MESSAGE_TO_USER_ID, userId).
						orderByDesc(MessageConstants.CREATION_DATE_TIME));
		
		if(likePage.getTotal() <= 0L) {
			logger.info("Related [likes] not found.");
		}
		
		likePage.getRecords().forEach(l -> likeResultList.add(toLikeResponseData(l)));
		result.setLikes(likeResultList);
		
		return result;
	}

	@Override
	public GetATResponseData getATMe(Pageable pageable, Integer userId) {
		GetATResponseData result = new GetATResponseData();
		
		List<ATResponseData> replyResultList = new ArrayList<>();
		IPage<Reply> replyPage = 
				replyMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), 
					new QueryWrapper<Reply>().
						eq(MessageConstants.MESSAGE_TO_USER_ID, userId).
						orderByDesc(MessageConstants.CREATION_DATE_TIME));
		
		if(replyPage.getTotal() <= 0L) {
			logger.info("[@ me] not found.");
		}
		
		replyPage.getRecords().forEach(r -> replyResultList.add(toReplyResponseData(r)));
		result.setReplys(replyResultList);
		
		return result;
	}

	@Override
	public GetCommentsResponseData getComments(Pageable pageable, Integer userId) {
		GetCommentsResponseData result = new GetCommentsResponseData();
		
		List<CommentResponseData> commentResultList = new ArrayList<>();
		IPage<Comment> commentPage = 
				commentMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), 
					new QueryWrapper<Comment>().
						eq(MessageConstants.MESSAGE_FROM_USER_ID, userId).
						orderByDesc(MessageConstants.CREATION_DATE_TIME));
		
		if(commentPage.getTotal() <= 0L) {
			logger.info("[Comments] not found.");
		}
		
		commentPage.getRecords().forEach(c -> commentResultList.add(toCommentResponseData(c)));
		result.setComments(commentResultList);
		
		return result;
	}

	@Override
	public InitDataResponse initData() {
		InitDataResponse result = new InitDataResponse(ResponseResult.DefaultSuccessResponse);
		logger.info("@@@@@@@@@@ Data Initialization...");
		
		Integer dataCount = commentMapper.
				selectCount((new QueryWrapper<Comment>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Comments had been initialized!");
		}else {
			for(Comment comment : MessageUtils.initCommentList) {
				commentMapper.insert(comment);
			}
		}
		
		dataCount = likeMapper.
				selectCount((new QueryWrapper<Like>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Likes had been initialized!");
		}else {
			for(Like like : MessageUtils.initLikeList) {
				likeMapper.insert(like);
			}
		}
		
		dataCount = replyMapper.
				selectCount((new QueryWrapper<Reply>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Replys had been initialized!");
		}else {
			for(Reply reply : MessageUtils.initReplyList) {
				replyMapper.insert(reply);
			}
		}
		
		dataCount = messageMapper.
				selectCount((new QueryWrapper<Message>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Messages had been initialized!");
		}else {
			for(Message message : MessageUtils.initMessageList) {
				messageMapper.insert(message);
			}
		}
		
		logger.info("@@@@@@@@@@ Data Initialization Done!");
		return result;
	}
	
}
