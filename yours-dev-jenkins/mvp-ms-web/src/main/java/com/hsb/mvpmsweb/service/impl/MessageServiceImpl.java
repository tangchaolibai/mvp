package com.hsb.mvpmsweb.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsweb.api.exception.MvpMessageException;
import com.hsb.mvpmsweb.api.exception.MvpMessageExceptionCode;
import com.hsb.mvpmsweb.constant.MessageConstants;
import com.hsb.mvpmsweb.mapper.CommentMapper;
import com.hsb.mvpmsweb.mapper.PhotoMapper;
import com.hsb.mvpmsweb.mapper.ReplyMapper;
import com.hsb.mvpmsweb.mapper.LikeMapper;
import com.hsb.mvpmsweb.mapper.MessageMapper;
import com.hsb.mvpmsweb.mapper.ShareMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.mapper.VideoMapper;
import com.hsb.mvpmsweb.model.domain.Comment;
import com.hsb.mvpmsweb.model.domain.Photo;
import com.hsb.mvpmsweb.model.domain.Reply;
import com.hsb.mvpmsweb.model.domain.Like;
import com.hsb.mvpmsweb.model.domain.Message;
import com.hsb.mvpmsweb.model.domain.Share;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.domain.Video;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.message.ATResponseData;
import com.hsb.mvpmsweb.model.payload.message.MsgCommentResponseData;
import com.hsb.mvpmsweb.model.payload.message.CountResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetATResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetCommentsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetLikesResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetNotificaitonsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetSystemResponseData;
import com.hsb.mvpmsweb.model.payload.message.LikeResponseData;
import com.hsb.mvpmsweb.model.payload.message.MessageResponseData;
import com.hsb.mvpmsweb.model.payload.message.SystemResponseData;
import com.hsb.mvpmsweb.model.payload.message.response.InitDataResponse;
import com.hsb.mvpmsweb.service.MessageService;
import com.hsb.mvpmsweb.util.MessageUtils;
import com.hsb.mvpmsweb.util.WebUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl implements MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Value("${jwt.base64Secret}")
	private String base64Secret;
	
	@Value("${jwt.token-prefix}")
    private String tokenPrefix;
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private ShareMapper shareMapper;
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private UserInfoMapper userMapper;
	
	private ATResponseData toReplyResponseData(Reply reply, Share AtMeshare, Integer toUserId) {
		ATResponseData responseData = new ATResponseData();
		Integer fromUserId = null;
		Share share = null;
		if(reply == null) {
			fromUserId = AtMeshare.getUserId();
			share = AtMeshare;
			
			responseData.setFromUserId(AtMeshare.getUserId());
			responseData.setCreationDateTime(AtMeshare.getCreationDateTime());
			responseData.setDateTimeAgo(WebUtils.getLocalDateTimeAgo(AtMeshare.getCreationDateTime()));
			responseData.setType(MessageConstants.MESSAGE_TYPE_AT_ME);
		}else {
			fromUserId = reply.getFromUserId();
			
			responseData.setId(reply.getEntityId());
			responseData.setCommentId(reply.getCommentId());
			responseData.setFromUserId(reply.getFromUserId());
			responseData.setReply(reply.getReply());
			responseData.setCreationDateTime(reply.getCreationDateTime());
			responseData.setDateTimeAgo(WebUtils.getLocalDateTimeAgo(reply.getCreationDateTime()));
			responseData.setType(MessageConstants.MESSAGE_TYPE_REPLY);
			
			Comment comment = commentMapper.selectById(reply.getCommentId());
			if(comment == null) {
				logger.warn("Related comment [{}] not found!", reply.getCommentId());
				return responseData;
			}
				
			share = shareMapper.selectById(comment.getShareId());
			if(share == null) {
				logger.warn("Related share [{}] not found!", comment.getShareId());
				return responseData;
			}
		}
			
		UserInfo user = userMapper.selectById(fromUserId);
		if(user == null) {
			logger.warn("Related user [{}] not found!", fromUserId);
			return responseData;
		}
		responseData.setToUserId(toUserId);
		responseData.setNickName(user.nickName());
		responseData.setUserImgPath(user.userImgPath());
		responseData.setShareId(share.getEntityId());
		responseData.setFileType(share.getFileType());
			
		switch (share.getFileType()) {
		case MessageConstants.FILE_TYPE_PHOTO:
			Photo photo = photoMapper.selectList(new QueryWrapper<Photo>().eq(MessageConstants.SHARE_ID, share.getEntityId())).
				stream().findFirst().orElse(null);
			if(photo != null)
				responseData.setThumbnailUrl(photo.photoPath());
			break;
		case MessageConstants.FILE_TYPE_VIDEO:
			Video video = videoMapper.selectOne(new QueryWrapper<Video>().eq(MessageConstants.SHARE_ID, share.getEntityId()));
			if(video != null) {
				responseData.setThumbnailUrl(video.thumbnailPath());
				responseData.setVideoPath(video.videoPath());
			}
			break;
		default:
			logger.warn("Unknown file type: [{}]!", share.getFileType());
			break;
		}
		
		return responseData;
	}
	
	private MsgCommentResponseData toCommentResponseData(Comment comment) {
		MsgCommentResponseData responseData = new MsgCommentResponseData();
		if(comment != null) {
			responseData.setId(comment.getEntityId());
			responseData.setShareId(comment.getShareId());
			responseData.setFromUserId(comment.getFromUserId());
			responseData.setComment(comment.getMessage());
			responseData.setCreationDateTime(comment.getCreationDateTime());
			responseData.setDateTimeAgo(WebUtils.getLocalDateTimeAgo(comment.getCreationDateTime()));
			
			UserInfo user = userMapper.selectById(comment.getFromUserId());
			if(user == null) {
				logger.warn("Related user [{}] not found!", comment.getFromUserId());
				return responseData;
			}
			responseData.setNickName(user.nickName());
			responseData.setUserImgPath(user.userImgPath());
			
			Share share = shareMapper.selectById(comment.getShareId());
			if(share == null) {
				logger.warn("Related share [{}] not found!", comment.getShareId());
				return responseData;
			}
			responseData.setFileType(share.getFileType());
			
			switch (share.getFileType()) {
			case MessageConstants.FILE_TYPE_PHOTO:
				Photo photo = photoMapper.selectList(new QueryWrapper<Photo>().eq(MessageConstants.SHARE_ID, share.getEntityId())).
					stream().findFirst().orElse(null);
				if(photo != null)
					responseData.setThumbnailUrl(photo.photoPath());
				break;
			case MessageConstants.FILE_TYPE_VIDEO:
				Video video = videoMapper.selectOne(new QueryWrapper<Video>().eq(MessageConstants.SHARE_ID, share.getEntityId()));
				if(video != null) {
					responseData.setThumbnailUrl(video.thumbnailPath());
					responseData.setVideoPath(video.videoPath());
				}
				break;
			default:
				logger.warn("Unknown file type: [{}]!", share.getFileType());
				break;
			}
		}
		return responseData;
	}
	
	private LikeResponseData toLikeResponseData(Like like) {
		LikeResponseData responseData = new LikeResponseData();
		if(like != null) {
			responseData.setId(like.getEntityId());
			responseData.setShareId(like.getShareId());
			responseData.setFromUserId(like.getFromUserId());
			responseData.setCreationDateTime(like.getCreationDateTime());
			responseData.setDateTimeAgo(WebUtils.getLocalDateTimeAgo(like.getCreationDateTime()));
			
			UserInfo user = userMapper.selectById(like.getFromUserId());
			if(user == null) {
				logger.warn("Related user [{}] not found!", like.getFromUserId());
				return responseData;
			}
			responseData.setNickName(user.nickName());
			responseData.setUserImgPath(user.userImgPath());
			
			Share share = shareMapper.selectById(like.getShareId());
			if(share == null) {
				logger.warn("Related share [{}] not found!", like.getShareId());
				return responseData;
			}
			responseData.setFileType(share.getFileType());
			
			switch (share.getFileType()) {
			case MessageConstants.FILE_TYPE_PHOTO:
				Photo photo = photoMapper.selectList(new QueryWrapper<Photo>().eq(MessageConstants.SHARE_ID, share.getEntityId())).
					stream().findFirst().orElse(null);
				if(photo != null)
					responseData.setThumbnailUrl(photo.photoPath());
				break;
			case MessageConstants.FILE_TYPE_VIDEO:
				Video video = videoMapper.selectOne(new QueryWrapper<Video>().eq(MessageConstants.SHARE_ID, share.getEntityId()));
				if(video != null) {
					responseData.setThumbnailUrl(video.thumbnailPath());
					responseData.setVideoPath(video.videoPath());
				}
				break;
			default:
				logger.warn("Unknown file type: [{}]!", share.getFileType());
				break;
			}
		}
		return responseData;
	}
	
	private SystemResponseData toSystemResponseData(Message message) {
		SystemResponseData responseData = new SystemResponseData();
		if(message != null) {
			responseData.setId(message.getEntityId());
			responseData.setMsg(message.getMsg());
			responseData.setCreationDateTime(message.getCreationDateTime());
			responseData.setIsRead(message.getIsRead());
		}
		return responseData;
	}
	
	private MessageResponseData toMessageResponseData(Message message) {
		MessageResponseData responseData = new MessageResponseData();
		if(message != null) {
			responseData.setId(message.getEntityId());
			responseData.setMsgType(message.getMsgType());
			responseData.setMsg(message.getMsg());
			
			Integer fromUserId = message.getFromUserId();
			responseData.setFromUserId(fromUserId);
			UserInfo user = userMapper.selectById(fromUserId);
			if(user == null) {
				logger.warn("Related user [{}] is non-exist", fromUserId);
			}
			
			responseData.setFromUserName(user.nickName());
			responseData.setFromUserImgPath(user.userImgPath());
		}
		return responseData;
	}

	@Override
	public CountResponseData getUnreadNotificationsCount(Integer userId) {
		CountResponseData result = new CountResponseData();
		Integer count = messageMapper.selectCount(new QueryWrapper<Message>().
				eq(MessageConstants.MESSAGE_TO_USER_ID, userId).
				eq(MessageConstants.MESSAGE_IS_READ, MessageConstants.NO));
		logger.info("User [{}]'s unread notificaitons count: [{}].", userId, count);
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
			logger.info("User [{}] has not any notifications!", userId);
		}
		
		messagePage.getRecords().forEach(m -> notificaitonResultList.add(toMessageResponseData(m)));
		result.setNotificaiton(notificaitonResultList);
		result.setCurrentPage(pageable.getPageNumber());
		result.setTotalCount(messagePage.getTotal());
		return result;
	}

	@Override
	public void deleteNotificaiton(Integer msgId) throws MvpMessageException {
		Message delMessage = messageMapper.selectById(msgId);
		if(delMessage != null && messageMapper.deleteById(delMessage.getEntityId()) != 0) {
			logger.info("Notification [{}] deleted successfully.", msgId);
		}else {
			logger.error("Notification [{}] not found!", msgId);
			throw new MvpMessageException(MvpMessageExceptionCode.ERROR_001_01);
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
			logger.info("User [{}] has not any system notificaiton!", userId);
		}
		
		messagePage.getRecords().forEach(m -> systemResultList.add(toSystemResponseData(m)));
		result.setMessages(systemResultList);
		result.setCurrentPage(pageable.getPageNumber());
		result.setTotalCount(messagePage.getTotal());
		return result;
	}

	@Override
	public GetLikesResponseData getLikes(Pageable pageable, Integer userId) {
		GetLikesResponseData result = new GetLikesResponseData();
		List<LikeResponseData> likeResultList = new ArrayList<>();
		
		List<Share> shareList = 
				shareMapper.selectList(new QueryWrapper<Share>().eq(MessageConstants.USER_ID, userId));
		if(CollectionUtil.isEmpty(shareList)) {
			logger.info("User [{}] has not any share!", userId);
			result.setLikes(likeResultList);
			return result;
		}
		
		List<Like> likeList = null;
		for(Share share : shareList) {
			likeList = 
					likeMapper.selectList(new QueryWrapper<Like>().
							eq(MessageConstants.SHARE_ID, share.getEntityId()));
			if(CollectionUtil.isEmpty(likeList)) continue;
			for(Like like : likeList) {
				likeResultList.add(toLikeResponseData(like));
			}
		}
		
		if(CollectionUtil.isEmpty(likeResultList) && CollectionUtil.isNotEmpty(shareList))
			logger.info("User's [{}] shares has not any like!", userId);
		
		result.setTotalCount(Integer.valueOf(likeResultList.size()).longValue());
		likeResultList = likeResultList.stream().
				sorted(Comparator.comparing(LikeResponseData::getCreationDateTime).reversed()).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		result.setLikes(likeResultList);
		result.setCurrentPage(pageable.getPageNumber());
		return result;
	}

	@Override
	public GetATResponseData getATMe(Pageable pageable, Integer userId) {
		GetATResponseData result = new GetATResponseData();
		List<ATResponseData> replyResultList = new ArrayList<>();
		List<Reply> replyList = null;
		List<Share> ATMeShareList = null;
		
		replyList = 
				replyMapper.selectList(new QueryWrapper<Reply>().
						eq(MessageConstants.MESSAGE_TO_USER_ID, userId));
		if(CollectionUtil.isNotEmpty(replyList)) {
			for(Reply reply : replyList) {
				replyResultList.add(toReplyResponseData(reply, null, userId));
			}
		}
		
		ATMeShareList = shareMapper.selectList(new QueryWrapper<Share>().isNotNull(MessageConstants.MESSAGE_TO_USER_ID));
		ATMeShareList = ATMeShareList.
				stream().filter(s -> StrUtil.contains(s.getToUserId(), userId.toString())).collect(Collectors.toList());
		if(CollectionUtil.isNotEmpty(ATMeShareList)) {
			for(Share share : ATMeShareList) {
				replyResultList.add(toReplyResponseData(null, share, userId));
			}
		}
		
		if(CollectionUtil.isEmpty(replyResultList))
			logger.info("User [{}] has not any reply or AT!", userId);
		
		result.setTotalCount(Integer.valueOf(replyResultList.size()).longValue());
		replyResultList = replyResultList.stream().distinct().
				sorted(Comparator.comparing(ATResponseData::getCreationDateTime).reversed()).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		result.setReplys(replyResultList);
		result.setCurrentPage(pageable.getPageNumber());
		return result;
	}

	@Override
	public GetCommentsResponseData getComments(Pageable pageable, Integer userId) {
		GetCommentsResponseData result = new GetCommentsResponseData();
		List<MsgCommentResponseData> commentResultList = new ArrayList<>();
		
		List<Share> shareList = 
				shareMapper.selectList(new QueryWrapper<Share>().eq(MessageConstants.USER_ID, userId));
		if(CollectionUtil.isEmpty(shareList)) {
			logger.info("User [{}] has not any share!", userId);
			result.setComments(commentResultList);
			return result;
		}
		
		List<Comment> commentList = null;
		for(Share share : shareList) {
			commentList = 
					commentMapper.selectList(new QueryWrapper<Comment>().
							eq(MessageConstants.SHARE_ID, share.getEntityId()));
			if(CollectionUtil.isEmpty(commentList)) continue;
			for(Comment comment : commentList) {
				commentResultList.add(toCommentResponseData(comment));
			}
		}
		
		if(CollectionUtil.isEmpty(commentResultList) && CollectionUtil.isNotEmpty(shareList))
			logger.info("User's [{}] shares has not any comment!", userId);
		
		result.setTotalCount(Integer.valueOf(commentResultList.size()).longValue());
		commentResultList = commentResultList.stream().
				sorted(Comparator.comparing(MsgCommentResponseData::getCreationDateTime).reversed()).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		result.setComments(commentResultList);
		result.setCurrentPage(pageable.getPageNumber());
		return result;
	}

	@Override
	public InitDataResponse initData() {
		InitDataResponse result = new InitDataResponse(ResponseResult.DefaultSuccessResponse);
		logger.info("@@@@@@@@@@ Data Initialization...");
		
		Integer dataCount = commentMapper.
				selectCount((new QueryWrapper<Comment>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ Comments had been initialized!");
		}else {
			for(Comment comment : MessageUtils.initCommentList) {
				commentMapper.insert(comment);
			}
		}
		
		dataCount = likeMapper.
				selectCount((new QueryWrapper<Like>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ Likes had been initialized!");
		}else {
			for(Like like : MessageUtils.initLikeList) {
				likeMapper.insert(like);
			}
		}
		
		dataCount = replyMapper.
				selectCount((new QueryWrapper<Reply>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ Replys had been initialized!");
		}else {
			for(Reply reply : MessageUtils.initReplyList) {
				replyMapper.insert(reply);
			}
		}
		
		dataCount = messageMapper.
				selectCount((new QueryWrapper<Message>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ Messages had been initialized!");
		}else {
			for(Message message : MessageUtils.initMessageList) {
				messageMapper.insert(message);
			}
		}
		
		logger.info("@@@@@@@@@@ Data Initialization Done!");
		return result;
	}
	
}
