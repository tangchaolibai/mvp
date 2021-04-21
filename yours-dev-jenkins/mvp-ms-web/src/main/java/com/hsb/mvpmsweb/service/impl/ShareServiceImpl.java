package com.hsb.mvpmsweb.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsweb.api.exception.MvpShareException;
import com.hsb.mvpmsweb.api.exception.MvpShareExceptionCode;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.constant.FileConstants;
import com.hsb.mvpmsweb.mapper.CommentMapper;
import com.hsb.mvpmsweb.mapper.FanMapper;
import com.hsb.mvpmsweb.mapper.LikeMapper;
import com.hsb.mvpmsweb.mapper.MessageMapper;
import com.hsb.mvpmsweb.mapper.PhotoMapper;
import com.hsb.mvpmsweb.mapper.ReplyMapper;
import com.hsb.mvpmsweb.mapper.ShareMapper;
import com.hsb.mvpmsweb.mapper.SystemSettingMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.mapper.VideoMapper;
import com.hsb.mvpmsweb.model.domain.Comment;
import com.hsb.mvpmsweb.model.domain.Fan;
import com.hsb.mvpmsweb.model.domain.Like;
import com.hsb.mvpmsweb.model.domain.Message;
import com.hsb.mvpmsweb.model.domain.Photo;
import com.hsb.mvpmsweb.model.domain.Reply;
import com.hsb.mvpmsweb.model.domain.Share;
import com.hsb.mvpmsweb.model.domain.SystemSetting;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.domain.Video;
import com.hsb.mvpmsweb.model.payload.AllFanListResponseData;
import com.hsb.mvpmsweb.model.payload.AllFollowingUserListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentResponseData;
import com.hsb.mvpmsweb.model.payload.ContentResponseData;
import com.hsb.mvpmsweb.model.payload.DiscoverResponseData;
import com.hsb.mvpmsweb.model.payload.DraftListResponseData;
import com.hsb.mvpmsweb.model.payload.FilesPathListResponseData;
import com.hsb.mvpmsweb.model.payload.FollowingListResponseData;
import com.hsb.mvpmsweb.model.payload.FollowingUserByUserNameResponseData;
import com.hsb.mvpmsweb.model.payload.FriendByMobileContactsResponseData;
import com.hsb.mvpmsweb.model.payload.FriendListResponseData;
import com.hsb.mvpmsweb.model.payload.FriendResponseData;
import com.hsb.mvpmsweb.model.payload.InviteFriendByMobileContactsResponseData;
import com.hsb.mvpmsweb.model.payload.IsFriendResponseData;
import com.hsb.mvpmsweb.model.payload.MobileContactsRequestData;
import com.hsb.mvpmsweb.model.payload.MobileContactsResponseData;
import com.hsb.mvpmsweb.model.payload.MyRelationCountResponseData;
import com.hsb.mvpmsweb.model.payload.PhotosData;
import com.hsb.mvpmsweb.model.payload.PostContentResponseData;
import com.hsb.mvpmsweb.model.payload.ProfileResponseData;
import com.hsb.mvpmsweb.model.payload.RecentlyUpdatedUserResponseData;
import com.hsb.mvpmsweb.model.payload.ReplyResponseData;
import com.hsb.mvpmsweb.model.payload.SendSMSResponseData;
import com.hsb.mvpmsweb.model.payload.ShareInfoData;
import com.hsb.mvpmsweb.model.payload.ShareListResponseData;
import com.hsb.mvpmsweb.model.payload.ShareRelationCountResponseData;
import com.hsb.mvpmsweb.model.payload.UserDetailResponseData;
import com.hsb.mvpmsweb.model.payload.VideosData;
import com.hsb.mvpmsweb.model.payload.request.AddFriendRequest;
import com.hsb.mvpmsweb.model.payload.request.AllFanListRequest;
import com.hsb.mvpmsweb.model.payload.request.CommentRequest;
import com.hsb.mvpmsweb.model.payload.request.ContentRequestData;
import com.hsb.mvpmsweb.model.payload.request.FollowingListByCreateTimeRequest;
import com.hsb.mvpmsweb.model.payload.request.FollowingUserByUserNameRequest;
import com.hsb.mvpmsweb.model.payload.request.FollowingUserListRequest;
import com.hsb.mvpmsweb.model.payload.request.FriendByMobileContactsRequest;
import com.hsb.mvpmsweb.model.payload.request.GiveFollowRequest;
import com.hsb.mvpmsweb.model.payload.request.InviteFriendByMobileContactsRequest;
import com.hsb.mvpmsweb.model.payload.request.RecentlyUpdatedUserRequest;
import com.hsb.mvpmsweb.model.payload.request.ReplyRequest;
import com.hsb.mvpmsweb.model.payload.request.ShareRelationCountRequest;
import com.hsb.mvpmsweb.model.payload.request.SocialLikeRequest;
import com.hsb.mvpmsweb.model.payload.request.SocialMsgRequest;
import com.hsb.mvpmsweb.model.payload.request.SocialShareRequest;
import com.hsb.mvpmsweb.service.ShareService;
import com.hsb.mvpmsweb.service.UserInfoService;
import com.hsb.mvpmsweb.util.JwtTokenUtil;
import com.hsb.mvpmsweb.util.Pinyin4jUtil;
import com.hsb.mvpmsweb.util.RedisUtils;
import com.hsb.mvpmsweb.util.VideoShotUtil;
import com.hsb.mvpmsweb.util.WebUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShareServiceImpl implements ShareService {
	
	private final static Logger logger = LoggerFactory.getLogger(ShareServiceImpl.class);
	
	@Value("${jwt.base64Secret}")
	private String base64Secret;
	
	@Value("${jwt.token-prefix}")
    private String tokenPrefix;
	
	@Value("${mvp.file.path}")
	private String uploadFilePath;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ShareMapper shareMapper;
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private FanMapper fanMapper;
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private SystemSettingMapper systemSettingMapper;
	
	@Autowired
	private RedisUtils redisUtils;
	
	private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	
	@PostConstruct
	private void init() {
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//set the lowerCase
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//set non-tone
	}
	
	@Override
	public FilesPathListResponseData uploadFiles(Integer userId, MultipartFile[] multipartFiles) throws MvpShareException {
		FilesPathListResponseData filesPathListResponseData = new FilesPathListResponseData();
		List<String> urlList = new ArrayList<>();

		if (multipartFiles != null && multipartFiles.length > 0) {
			File file = null;
			String filename = null;
			String savePath = null;
			String returnPath = "/useruploadFile/" + userId;
			String oriFileName = null;
			String subffix = null;
			File dir = null;
			String childPath = null;
			try {
				for (MultipartFile multipartFile : multipartFiles) {
					file = new File(System.getProperty("user.dir").replace("\\", "/"));
					savePath = file.getParent().replace("\\", "/") + uploadFilePath + "/" + userId;
					oriFileName = multipartFile.getOriginalFilename();
					subffix = oriFileName.substring(oriFileName.lastIndexOf(".") + 1, oriFileName.length());
					logger.info("File [{}] type is [{}].", oriFileName, subffix);
					if (CollectionUtil.contains(FileConstants.VIDEO_TYPES, subffix.toLowerCase())) {
						childPath = "/videos/";
					} else if (CollectionUtil.contains(FileConstants.PHOTO_TYPES, subffix.toLowerCase())) {
						childPath = "/photos/";
					} else {
						throw new MvpShareException(MvpShareExceptionCode.ERROR_011_01, new String[] { subffix });
					}
					savePath = savePath + childPath;
					returnPath = returnPath + childPath;
					dir = new File(savePath);
					if (!dir.exists())
						dir.mkdirs();
				
					filename = userId + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS")
							+ multipartFile.getOriginalFilename();
					multipartFile.transferTo(new File(savePath, filename));
					if(StrUtil.equals(childPath, "/videos/")) {
						VideoShotUtil.createVideoShot(savePath + filename, 
								savePath.replace("videos", "photos") + filename.substring(0, filename.length() - subffix.length()) + FileConstants.THUMBNAIL_TYPE);
						urlList.add(returnPath.replace("videos", "photos") + filename.substring(0, filename.length() - subffix.length()) + FileConstants.THUMBNAIL_TYPE);
					}
					urlList.add(returnPath + filename);
				}
			} catch (IOException | IllegalStateException e) {
				throw new MvpShareException(MvpShareExceptionCode.ERROR_010_01, new String[] { e.getMessage() });
			}
		}else {
			throw new MvpShareException(MvpShareExceptionCode.ERROR_006_01);
		}
		filesPathListResponseData.setFilesPathList(urlList);
		return filesPathListResponseData;
	}

	@Override
	public DraftListResponseData getDraftContent(Integer userId) {
		QueryWrapper<Share> contentWrapper = new QueryWrapper<>();
		contentWrapper.eq("USER_ID", userId);
		contentWrapper.eq("POST_STATUS", "N");
		Share socialShare = shareMapper.selectOne(contentWrapper);
		DraftListResponseData draftListResponseData = new DraftListResponseData();
		if(socialShare != null) {
			draftListResponseData.setShareId(socialShare.getEntityId());
			draftListResponseData.setMessage(socialShare.getMessage());
			draftListResponseData.setCreateTime(socialShare.getCreationDateTime());
			draftListResponseData.setFileType(socialShare.getFileType());
			draftListResponseData.setLastUpdateTime(socialShare.getLastUpdateDateTime());
			draftListResponseData.setLocation(socialShare.getLocation());
			draftListResponseData.setMessage(socialShare.getMessage());
			QueryWrapper<Photo> photoWrapper = new QueryWrapper<>();
			photoWrapper.eq("SHARE_ID", socialShare.getEntityId());
			QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
			videoWrapper.eq("SHARE_ID", socialShare.getEntityId());
			List<PhotosData> photoDataList = new ArrayList<PhotosData>();
			VideosData videosData = new VideosData();
			if("photo".equals(socialShare.getFileType()) && photoMapper.selectList(photoWrapper) != null && !photoMapper.selectList(photoWrapper).isEmpty()) {
				List<Photo> photoList = photoMapper.selectList(photoWrapper);
				for(Photo photo : photoList) {
					PhotosData photosData = new PhotosData();
					photosData.setPhotoDes(photo.description());
					photosData.setPhotoLoc(photo.location());
					photosData.setPhotoName(photo.photoName());
					photosData.setPhotoPath(photo.photoPath());
					photoDataList.add(photosData);
				}
			}else if("video".equals(socialShare.getFileType()) && videoMapper.selectOne(videoWrapper) != null) {
				Video video = videoMapper.selectOne(videoWrapper);
				videosData.setThumbnailUrl(video.thumbnailPath());
				videosData.setVideoDes(video.description());
				videosData.setVideoLoc(video.location());
				videosData.setVideoName(video.videoName());
				videosData.setVideoPath(video.videoPath());
				videosData.setVideoSeconds(video.seconds());
			}
			draftListResponseData.setPhotos(photoDataList);
			draftListResponseData.setVideos(videosData);
			draftListResponseData.setPostStatus(socialShare.getPostStatus());
			draftListResponseData.setShareCount(socialShare.getShareCount());
			draftListResponseData.setLikeCount(socialShare.getLikeCount());
			draftListResponseData.setCommentCount(socialShare.getCommentCount());
			draftListResponseData.setToUserId(socialShare.getToUserId());
			draftListResponseData.setUserId(socialShare.getUserId());
			draftListResponseData.setViewAmount(socialShare.getViewAmount());
			draftListResponseData.setProductUrl(socialShare.getProductUrl());
		}
		return draftListResponseData;
	}

	@Override
	@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public PostContentResponseData postContent(SocialShareRequest socialShareRequest) throws MvpShareException {
		PostContentResponseData result = new PostContentResponseData();

		// add a new draft
		Share socialShare = new Share();
		socialShare.setUserId(socialShareRequest.getUserId());
		socialShare.setPostStatus(socialShareRequest.getPostStatus());
		socialShare.setMessage(socialShareRequest.getMessage());
		socialShare.setFileType(socialShareRequest.getFileType());
		socialShare.setLocation(socialShareRequest.getLocation());
		List<Integer> toUserIdList = socialShareRequest.getToUserId();
		String toUserIds="";
		if(toUserIdList != null && !toUserIdList.isEmpty()) {
			for(Integer toUserId : toUserIdList) {
				toUserIds+=toUserId+",";
			}
			toUserIds=toUserIds.substring(0,toUserIds.length()-1);
		}
		socialShare.setToUserId(toUserIds);
		socialShare.setProductUrl(socialShareRequest.getProductUrl());
		// delete last update draft
		QueryWrapper<Share> fanWrapper = new QueryWrapper<>();
		fanWrapper.eq("USER_ID", socialShareRequest.getUserId());
		fanWrapper.eq("POST_STATUS", "N");
		Share socialShareDraft = shareMapper.selectOne(fanWrapper);
		
		if (socialShareDraft != null) {
			socialShare.setEntityId(socialShareDraft.getEntityId());
			QueryWrapper<Photo> photoWrapper = new QueryWrapper<>();
			QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
			photoWrapper.eq("SHARE_ID", socialShareDraft.getEntityId());
			videoWrapper.eq("SHARE_ID", socialShareDraft.getEntityId());
			photoMapper.delete(photoWrapper);
			videoMapper.delete(videoWrapper);
			shareMapper.updateById(socialShare);
		}else {
			shareMapper.insert(socialShare);
		}
		result.setShareId(socialShare.getEntityId());
		
		if(toUserIdList != null && !toUserIdList.isEmpty()) {
			for(Integer toUserId : toUserIdList) {
				Message socialMessage = new Message();
				socialMessage.setShareId(socialShare.getEntityId());
				//socialMessage.setMsg("提到了我");
				socialMessage.setMsgType("at");
				socialMessage.setFromUserId(socialShare.getUserId());
				socialMessage.setIsRead("N");
				socialMessage.setToUserId(toUserId);
				messageMapper.insert(socialMessage);
			}
		}
		if ("photo".equals(socialShareRequest.getFileType())) {
			if (socialShareRequest.getContentRequestData().size() > 9) {
				logger.info("Only support 9 photos to upload.");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_014_01);
			}
			for (ContentRequestData photoData : socialShareRequest.getContentRequestData()) {
				Photo photo = new Photo();
				photo.shareId(socialShare.getEntityId()).photoPath(photoData.getContentPath())
						.location(socialShareRequest.getLocation()).description(socialShareRequest.getMessage())
						.photoName(photoData.getContentName());
				photoMapper.insert(photo);
			}
		} else if ("video".equals(socialShareRequest.getFileType())) {
			if (socialShareRequest.getContentRequestData().size() > 1) {
				logger.info("Only support 1 video to upload.");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_015_01);
			}
			for (ContentRequestData videoData : socialShareRequest.getContentRequestData()) {
				Video video = new Video();
				video.shareId(socialShare.getEntityId()).videoName(videoData.getContentName())
						.seconds(videoData.getContentSeconds()).location(socialShareRequest.getLocation())
						.description(socialShareRequest.getMessage()).videoPath(videoData.getContentPath())
						.thumbnailPath(videoData.getContentThumbnailUrl());
				videoMapper.insert(video);
			}
		}

		return result;
	}

	@Override
	public void giveLike(SocialLikeRequest socialLikeRequest) throws MvpShareException {
		Share socialShare = shareMapper.selectById(socialLikeRequest.getShareId());
		if(socialShare == null) {
			logger.info("There is no such share in shareList");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}
		Integer likeCount = socialShare.getLikeCount();
		if("Y".equals(socialLikeRequest.getLikeStatus())){
				Like socialLike = new Like();
				socialLike.setShareId(socialLikeRequest.getShareId());
				socialLike.setFromUserId(socialLikeRequest.getFromUserId());
				likeMapper.insert(socialLike);
				socialShare.setLikeCount(likeCount + 1);
				shareMapper.updateById(socialShare);
		}else if("N".equals(socialLikeRequest.getLikeStatus())) {
			QueryWrapper<Like> socialLikeWrapper = new QueryWrapper<>();
			socialLikeWrapper.eq("SHARE_ID", socialLikeRequest.getShareId());
			socialLikeWrapper.eq("FROM_USER_ID", socialLikeRequest.getFromUserId());
			likeMapper.delete(socialLikeWrapper);
			socialShare.setLikeCount(likeCount - 1);
			shareMapper.updateById(socialShare);
		}else {
			logger.info("value of 'like' is empty");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_008_01);
		}
	}

	@Override
	public void deleteContent(Integer shareId) {
		shareMapper.deleteById(shareId);
	}

	@Override
	public void shareContent(SocialMsgRequest socialMsgRequest) throws MvpShareException {
		Share socialShare = shareMapper.selectById(socialMsgRequest.getShareId());
		if(socialShare == null) {
			logger.info("There is no such share in shareList");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}
		Message socialMessage = new Message();
		socialMessage.setShareId(socialMsgRequest.getShareId());
		socialMessage.setMsg(socialMsgRequest.getMessage());
		socialMessage.setFromUserId(socialMsgRequest.getFromUserId());
		socialMessage.setIsRead(socialMsgRequest.getIsRead());
		List<Integer> toUserIdList = socialMsgRequest.getToUserId();
		if(toUserIdList != null && !toUserIdList.isEmpty()) {
			for(Integer toUserId : toUserIdList) {
				socialMessage.setToUserId(toUserId);
				messageMapper.insert(socialMessage);
				Integer shareCount = socialShare.getShareCount();
				socialShare.setShareCount(shareCount + 1);
				shareMapper.updateById(socialShare);
			}
		}else {
			logger.info("There is no user selected to share the content");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_007_01);
		}
	}

	@Override
	public FollowingListResponseData getFollowingListByCreateTime(
			@Valid FollowingListByCreateTimeRequest followingListByCreateTimeRequest, Pageable pageable) throws MvpShareException {
		List<Share> shareList = new ArrayList<>();
		FollowingListResponseData data = new FollowingListResponseData();
		QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
		fanWrapper.eq("FAN_ID", followingListByCreateTimeRequest.getUserId());
		List<Integer> followingIdList = new ArrayList<>();
		List<Fan> followingList = fanMapper.selectList(fanWrapper);
		
		if (followingList == null || followingList.size() <= 0) {
			logger.info("There is no following List for current user");
			data.setFollowingListData(new ArrayList<ShareInfoData>());
			return data;
		}else {
			followingList.stream().forEach(x -> {
				followingIdList.add(x.getUserId());
			});
			
			QueryWrapper<Share> shareWrapper = new QueryWrapper<>();
			shareWrapper.in("USER_ID", followingIdList);
		    shareWrapper.eq("POST_STATUS", "Y");
			shareWrapper.orderByDesc("CREATION_DATE_TIME");
			Page<Share> pages = shareMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), shareWrapper);
			pages.getRecords().stream().forEach(x -> shareList.add(x));
		}
		data = toShareInfoResponseData(followingListByCreateTimeRequest.getUserId(), shareList, followingIdList/*, followingListByCreateTimeRequest.getRefreshTime()*/);
		return data;
	}

	@Override
	public ShareListResponseData getShareListByCreateTime(Integer userId, Pageable pageable) throws MvpShareException {
		QueryWrapper<Share> shareWrapper = new QueryWrapper<>();
		shareWrapper.eq("FILETYPE", "video");
		shareWrapper.orderByDesc("CREATION_DATE_TIME");
		shareWrapper.eq("POST_STATUS", "Y");
		List<Share> shareList = new ArrayList<>(); 
		if(pageable != null) {
			Page<Share> pages = shareMapper.selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()), shareWrapper);
			if(pages != null) {
				pages.getRecords().stream().forEach(x -> {
					QueryWrapper<SystemSetting> wrapper = new QueryWrapper<>();
					wrapper.eq("USER_ID", x.getUserId());
					if(systemSettingMapper.selectOne(wrapper) != null && !x.getUserId().equals(userId) && systemSettingMapper.selectOne(wrapper).privateAccount().equals("Y")) {
						FriendListResponseData friendList = getFriendList(userId,null);
						List<FriendResponseData> dataList = friendList.getFansList().stream().filter(f -> f.getFanId().equals(x.getUserId())).collect(Collectors.toList());
						if(dataList == null || dataList.size() <= 0) {
							return;
						}
					}
					shareList.add(x);
				});
			}
		}
		ShareListResponseData data = new ShareListResponseData();
		data.setShareList(toShareInfoData(userId, shareList));
		return data;
	}
	
	@Override
	public ShareInfoData getShareById(String token, Integer shareId) throws MvpShareException {
		Share shareResult = shareMapper.selectById(shareId);
		ShareInfoData data = new ShareInfoData();
		token = StringUtils.remove(token, tokenPrefix);
		try {
			data = toShareResponseData(Integer.parseInt(JwtTokenUtil.getUserId(token, base64Secret)), shareResult);
		} catch (MvpWebException e) {
			throw new MvpShareException(e.getCode());
		}
		return data;
	}
	
	@Override
	public ShareRelationCountResponseData getShareRelationCount(ShareRelationCountRequest shareRelationCountRequest) {
		Share socialShare = shareMapper.selectById(shareRelationCountRequest.getShareId());
		ShareRelationCountResponseData data = new ShareRelationCountResponseData();
		
		if(socialShare == null) {
			logger.error("There is no such share in data");
			return data;
		}
		data.setShareCount(socialShare.getShareCount());
		data.setLikeCount(socialShare.getLikeCount());
		data.setCommentCount(socialShare.getCommentCount());
		return data;
	}
	
	@Override
	public AllFanListResponseData getAllFanList(@Valid AllFanListRequest allFanListRequest) throws MvpShareException {
		AllFanListResponseData data = new AllFanListResponseData();
		List<UserDetailResponseData> userResponseDataList = new ArrayList<>();
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", allFanListRequest.getToUserId());
		List<Fan> socialFanList = fanMapper.selectList(wrapper);
		data.setUserList(userResponseDataList);
		if(socialFanList == null || socialFanList.size() <= 0) {
			logger.error("There is no fan in data for current user");
			return data;
		}
		
		List<Integer> fanIdList = new ArrayList<>();
		socialFanList.stream().forEach(x -> {
			fanIdList.add(x.getFanId());
		});
		QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
		userWrapper.in("ENTITY_ID", fanIdList);
		List<UserInfo> userInfoList = userInfoMapper.selectList(userWrapper);
		
		if(userInfoList == null || userInfoList.size() <=0) {
			logger.error("there is no such user in user data");
			return data;
		}
		
		for (UserInfo userInfo : userInfoList) {
			userResponseDataList.add(toUserInfoResponseData(userInfo,allFanListRequest.getToUserId()));
		}
		userResponseDataList = userResponseDataList.stream().sorted(Comparator.comparing(UserDetailResponseData::getCreationDateTime).reversed()).collect(Collectors.toList());
		data.setUserList(userResponseDataList);
		return data;
	}

	@Override
	public AllFollowingUserListResponseData getAllFollowingUserList(
			@Valid FollowingUserListRequest followingUserListRequest) throws MvpShareException {
		AllFollowingUserListResponseData data = new AllFollowingUserListResponseData();
		List<UserDetailResponseData> userResponseDataList = new ArrayList<>();
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", followingUserListRequest.getToUserId()).orderByAsc("CREATION_DATE_TIME");
		List<Fan> followingList = fanMapper.selectList(wrapper);
		data.setUserList(userResponseDataList);
		if(followingList == null || followingList.size() <= 0) {
			logger.info("There is no following List in fan data");
			return data;
		}
		
		List<Integer> followingIdList = new ArrayList<>();
		followingList.stream().forEach(x -> {
			followingIdList.add(x.getUserId());
		});
		
		QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
		userWrapper.in("ENTITY_ID", followingIdList);
		List<UserInfo> userInfoList = userInfoMapper.selectList(userWrapper);
		
		if(userInfoList == null || userInfoList.size() <= 0) {
			logger.info("There is no following user list in user data");
			return data;
		}
		
		for (UserInfo userInfo : userInfoList) {
			userResponseDataList.add(toUserInfoResponseData(userInfo, followingUserListRequest.getToUserId()));
		}
		userResponseDataList = userResponseDataList.stream().sorted(Comparator.comparing(UserDetailResponseData::getCreationDateTime).reversed()).collect(Collectors.toList());
		data.setUserList(userResponseDataList);
		return data;
	}

	@Override
	public RecentlyUpdatedUserResponseData getRecentlyUpdatedUser(
			@Valid RecentlyUpdatedUserRequest recentlyUpdatedUserRequest) throws MvpShareException {
		RecentlyUpdatedUserResponseData data = new RecentlyUpdatedUserResponseData();
		List<UserDetailResponseData> userListResponseData = new ArrayList<>();
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", recentlyUpdatedUserRequest.getUserId());
		List<Fan> followList = fanMapper.selectList(wrapper);
		
		if(followList == null || followList.size() <= 0) {
			logger.info("there is no following data in fan list");
			return data;
		}
		
		//Temporarily set to show the first five followings
		List<Share> recentlyShareList = new ArrayList<>(5); 
		for (Fan socialFan : followList) {
			QueryWrapper<Share> shareWrapper = new QueryWrapper<>();
			shareWrapper.eq("USER_ID", socialFan.getUserId()).eq("POST_STATUS", "Y");
			shareWrapper.orderByDesc("CREATION_DATE_TIME");
			List<Share> shareList = shareMapper.selectList(shareWrapper);
			
			if(shareList == null || shareList.size() <= 0) {
				logger.info("there is no following share in share List for userId:[{}]", socialFan.getUserId());
			}else {
				recentlyShareList.add(shareList.get(0));
			}
		}		
		List<Share> orderShareList = recentlyShareList.stream().sorted(Comparator.comparing(Share::getCreationDateTime).reversed()).collect(Collectors.toList());
		
		if(orderShareList == null || orderShareList.size() <= 0) {
			logger.info("there is no share for all following user");
			return data;
		}
		
		for (Share socialShare : orderShareList) {
			userListResponseData.add(toUserInfoResponseData(userInfoMapper.selectById(socialShare.getUserId()), recentlyUpdatedUserRequest.getUserId()));
		}
		data.setUserList(userListResponseData);
		return data;
	}
	
	@Override
	public void giveFollow(@Valid GiveFollowRequest giveFollowRequest) throws MvpShareException {
		if (giveFollowRequest.getUserId().equals(giveFollowRequest.getFollowingId())) {
			logger.error("You can not follow yourself!");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_013_01);
		}
		if(giveFollowRequest.getFollowStatus() != null) {
			if(giveFollowRequest.getFollowStatus().equals("Y")) {
				Map<String, Object> params = new HashMap<>();
				params.put("USER_ID", giveFollowRequest.getFollowingId());
				QueryWrapper<Fan> socialFanWrapper = new QueryWrapper<Fan>().allEq(params);
				List<Fan> followingList = fanMapper.selectList(socialFanWrapper);
				boolean anyMatch = followingList.stream().anyMatch(e -> e.getFanId().equals(giveFollowRequest.getUserId()));
				if (!anyMatch) {
					Fan socialFan = new Fan();
					socialFan.setUserId(giveFollowRequest.getFollowingId());
					socialFan.setFanId(giveFollowRequest.getUserId());
					socialFan.setCreationDateTime(LocalDateTime.now());
					socialFan.setIsBlack("0");
					fanMapper.insert(socialFan);
					updateFollowAndFanCount(giveFollowRequest.getUserId(), giveFollowRequest.getFollowingId(), "Y");
				}
				
			}else if(giveFollowRequest.getFollowStatus().equals("N")) {
				UpdateWrapper<Fan> wrapper = new UpdateWrapper<>();
				wrapper.eq("USER_ID", giveFollowRequest.getFollowingId());
				wrapper.eq("FAN_ID", giveFollowRequest.getUserId());
				fanMapper.delete(wrapper);
				updateFollowAndFanCount(giveFollowRequest.getUserId(), giveFollowRequest.getFollowingId(), "N");
			}else {
				logger.error("there is no correct status in params");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_009_01);
			}
		}else {
			logger.error("there is no follow status in params");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_009_01);
		}
	}
	
	@Override
	public FollowingUserByUserNameResponseData findFollowingBySearchContent(
			@Valid FollowingUserByUserNameRequest followingUserByUserNameRequest) throws MvpShareException {
		if (followingUserByUserNameRequest.getSearchContent() == null) {
			followingUserByUserNameRequest.setSearchContent("");
		}
		FollowingUserByUserNameResponseData data = new FollowingUserByUserNameResponseData();
		List<UserDetailResponseData> listUserResponseData = new ArrayList<>();
		data.setUserList(listUserResponseData);
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", followingUserByUserNameRequest.getToUserId());
		List<Fan> socialFanlist = fanMapper.selectList(wrapper);
		
		if (socialFanlist == null || socialFanlist.size() <= 0) {
			logger.info("There is no following in fan list for current user");
			return data;
		}
		
		List<Integer> followingUserList = new ArrayList<>();
		socialFanlist.stream().forEach(x -> {
			followingUserList.add(x.getUserId());
		});
		QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
		userWrapper.in("ENTITY_ID", followingUserList);
		//.and(u -> u.like("NICK_NAME", followingUserByUserNameRequest.getSearchContent()).like("MOBILE_PHONE", followingUserByUserNameRequest.getSearchContent()));
		List<UserInfo> userInfoList = userInfoMapper.selectList(userWrapper);
		
		if(userInfoList == null || userInfoList.size() <= 0) {
			logger.info("there is no such user through filter");
			return data;
		}
		List<UserInfo> tempList = new ArrayList<>();
		userInfoList.stream().forEach(x -> {
			if(contain(x.nickName(),followingUserByUserNameRequest.getSearchContent()) || x.mobilePhone().contains(followingUserByUserNameRequest.getSearchContent()) || contain(Pinyin4jUtil.getPinyin(x.nickName()), followingUserByUserNameRequest.getSearchContent())){
			//if(x.nickName().contains(followingUserByUserNameRequest.getSearchContent()) || x.mobilePhone().contains(followingUserByUserNameRequest.getSearchContent())) {
				tempList.add(x);
			}
		});
		
		for (UserInfo userInfo : tempList) {
			UserDetailResponseData userResponseData = new UserDetailResponseData();
			userResponseData = toUserInfoResponseData(userInfo, followingUserByUserNameRequest.getToUserId());
			listUserResponseData.add(userResponseData);
		}
		
		listUserResponseData = listUserResponseData.stream().distinct().collect(Collectors.toList());
		//data.setUserList(listUserResponseData);
		return data;
	}
	
	private MyRelationCountResponseData getMyRelationCount(Integer currentUserId, Integer scanUserId) {
		MyRelationCountResponseData data = new MyRelationCountResponseData();
		Integer likedCount = 0;
		QueryWrapper<Share> shareWrapper = new QueryWrapper<>();
		shareWrapper.eq("USER_ID", scanUserId).eq("POST_STATUS", "Y");
		List<Share> shareList = shareMapper.selectList(shareWrapper);
		if(shareList == null || shareList.size() <= 0) {
			logger.info("there is no share for this user");
		}else {
			for (Share socialShare : shareList) {
				QueryWrapper<Like> likeWrapper = new QueryWrapper<>();
				likeWrapper.eq("SHARE_ID", socialShare.getEntityId());
				List<Like> likedList = likeMapper.selectList(likeWrapper);
				if (likedList == null || likedList.size() <= 0) {
					logger.info("this share is not liked by anyone now");
				}else {
					likedCount = likedCount + likedList.size(); 
				}
			}
		}
		data.setLikedCount(likedCount);
		
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", scanUserId);
		List<Fan> followingList = fanMapper.selectList(wrapper);
		if(followingList != null && followingList.size() > 0) {
			data.setFollowerCount(followingList.size());
		}else {
			data.setFollowerCount(0);
		}
		wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", scanUserId);
		List<Fan> followList = fanMapper.selectList(wrapper);
		if(followList != null && followList.size() > 0) {
			data.setFollowingCount(followList.size());
		}else {
			data.setFollowingCount(0);
		}
		wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", scanUserId);
		wrapper.eq("FAN_ID", currentUserId);
		List<Fan> isFollowingList = fanMapper.selectList(wrapper);
		if(isFollowingList == null || isFollowingList.size() <= 0) {
			data.setFollowing(false);
		}else {
			data.setFollowing(true);
		}
		return data;
	}
	
	private FollowingListResponseData toShareInfoResponseData(Integer userId, List<Share> shareList, List<Integer> followingIdList/*, String refreshTime*/) throws MvpShareException {
		FollowingListResponseData data = new FollowingListResponseData();
		List<ShareInfoData> shareInfoData = null;
		
		//QueryWrapper<Share> shareWrapper = new QueryWrapper<>();
		/*
		if (followingIdList == null || followingIdList.size() <=0 ) {
			logger.error("There is no following user");
		}else {
			shareWrapper.in("USER_ID", followingIdList);
			shareWrapper.gt("CREATION_DATE_TIME", refreshTime);
			List<Share> shareRefleshList = shareMapper.selectList(shareWrapper);
			
			if(shareRefleshList == null || shareRefleshList.size() <= 0 ) {
				data.setRecentShareCount(0L);
			}else {
				data.setRecentShareCount(Long.valueOf(shareRefleshList.size()));
				shareInfoData = toShareInfoData(userId,shareList);
			}
		}
		*/
		shareInfoData = toShareInfoData(userId, shareList);
		data.setFollowingListData(shareInfoData);
		
		return data;
	}
	
	private List<ShareInfoData> toShareInfoData(Integer userId, List<Share> shareList) throws MvpShareException {
		List<ShareInfoData> shareInfoData = new ArrayList<>();
		
		if (shareList == null || shareList.size() <= 0) {
			logger.error("There is no share data for its following user");
			return shareInfoData;
		}
		for (Share share : shareList) {
			
			if(share == null) {
				logger.error("there is no such share in share data");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
			}
			
			ShareInfoData infoData = new ShareInfoData();
			infoData.setShareId(share.getEntityId());
			infoData.setUserId(share.getUserId());
			infoData.setPostStatus(share.getPostStatus());
			infoData.setMessage(share.getMessage());
			infoData.setFileType(share.getFileType());
			infoData.setLocation(share.getLocation());
			infoData.setToUserId(share.getToUserId());
			infoData.setLikeCount(share.getLikeCount());
			infoData.setCommentCount(share.getCommentCount());
			infoData.setShareCount(share.getShareCount());
			infoData.setViewAmount(share.getViewAmount());
			infoData.setCreateTime(share.getCreationDateTime());
			infoData.setLastUpdateTime(share.getLastUpdateDateTime());
			//Add for product post
			infoData.setProductUrl(share.getProductUrl());
			UserInfo user = userInfoMapper.selectById(share.getUserId());
			if(user == null) {
				logger.error("there is no such user in userData");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
			}
			infoData.setUserImgPath(user.userImgPath());
			infoData.setUserName(user.nickName());
			QueryWrapper<Like> likeWrapper = new QueryWrapper<>();
			likeWrapper.eq("SHARE_ID", share.getEntityId());
			likeWrapper.eq("FROM_USER_ID",userId);
			List<Like> likeList = likeMapper.selectList(likeWrapper);
			if(likeList != null && likeList.size() > 0) {
				infoData.setIsLike(true);
			}else {
				infoData.setIsLike(false);
			}
			QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
			fanWrapper.eq("FAN_ID", userId);
			fanWrapper.eq("USER_ID", share.getUserId());
			fanWrapper.eq("IS_BLACK", "0");
			List<Fan> fanList = fanMapper.selectList(fanWrapper);
			if(fanList != null && fanList.size() > 0) {
				infoData.setIsFollowing(true);
			}else {
				infoData.setIsFollowing(false);
			}
			
			List<ContentResponseData> contentResponseDataList = new ArrayList<>();
			String fileType = share.getFileType();

			if (fileType != null && fileType.equals("video")) {
				QueryWrapper<Video> wrapper = new QueryWrapper<>();
				wrapper.eq("SHARE_ID", share.getEntityId());
				Video video = videoMapper.selectOne(wrapper);

				if (video == null) {
					logger.error("There is no such video in data");
					throw new MvpShareException(MvpShareExceptionCode.ERROR_003_01);
				} else {
					infoData.setThumbnailUrl(video.thumbnailPath());
					ContentResponseData contentData = new ContentResponseData();
					contentData.setContentDes(video.description());
					contentData.setContentId(video.entityId());
					contentData.setContentLoc(video.location());
					contentData.setContentName(video.videoName());
					contentData.setContentPath(video.videoPath());
					contentData.setContentSeconds(video.seconds());
					contentData.setContentType("video");
					contentResponseDataList.add(contentData);
				}

			} else if (fileType != null && fileType.equals("photo")) {
				QueryWrapper<Photo> wrapper = new QueryWrapper<>();
				wrapper.eq("SHARE_ID", share.getEntityId());
				List<Photo> photo = photoMapper.selectList(wrapper);

				if (photo == null || photo.size() <= 0) {
					logger.error("There is no such photo in data");
					throw new MvpShareException(MvpShareExceptionCode.ERROR_002_01);
				} else {
					infoData.setThumbnailUrl(photo.get(0).photoPath());
					photo.stream().forEach(p -> {
						ContentResponseData contentData = new ContentResponseData();
						contentData.setContentDes(p.description());
						contentData.setContentId(p.entityId());
						contentData.setContentLoc(p.location());
						contentData.setContentName(p.photoName());
						contentData.setContentPath(p.photoPath());
						contentData.setContentType("photo");
						contentResponseDataList.add(contentData);
					});
				}
			}
			infoData.setContentResponseData(contentResponseDataList);
			shareInfoData.add(infoData);
		}
			
		return shareInfoData;
	}

	private ShareInfoData toShareResponseData(Integer userId, Share share) throws MvpShareException {
		ShareInfoData infoData = new ShareInfoData();
		if (share == null) {
			logger.warn("There is no share data!");
			return infoData;
		}
		
		infoData.setShareId(share.getEntityId());
		infoData.setUserId(share.getUserId());
		infoData.setPostStatus(share.getPostStatus());
		infoData.setMessage(share.getMessage());
		infoData.setFileType(share.getFileType());
		infoData.setLocation(share.getLocation());
		infoData.setToUserId(share.getToUserId());
		infoData.setLikeCount(share.getLikeCount());
		infoData.setCommentCount(share.getCommentCount());
		infoData.setShareCount(share.getShareCount());
		infoData.setViewAmount(share.getViewAmount());
		infoData.setCreateTime(share.getCreationDateTime());
		infoData.setLastUpdateTime(share.getLastUpdateDateTime());
		infoData.setProductUrl(share.getProductUrl());
		
		UserInfo user = userInfoMapper.selectById(share.getUserId());
		if(user == null) {
			logger.error("there is no such user in userData");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		
		infoData.setUserImgPath(user.userImgPath());
		infoData.setUserName(user.nickName());
		QueryWrapper<Like> likeWrapper = new QueryWrapper<>();
		likeWrapper.eq("SHARE_ID", share.getEntityId());
		likeWrapper.eq("FROM_USER_ID", userId);
		List<Like> likeList = likeMapper.selectList(likeWrapper);
		infoData.setIsLike(!CollectionUtil.isEmpty(likeList));
		
		QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
		fanWrapper.eq("FAN_ID", userId);
		fanWrapper.eq("USER_ID", share.getUserId());
		fanWrapper.eq("IS_BLACK", "0");
		List<Fan> fanList = fanMapper.selectList(fanWrapper);
		infoData.setIsFollowing(!CollectionUtil.isEmpty(fanList));
			
		List<ContentResponseData> contentResponseDataList = new ArrayList<>();
		String fileType = share.getFileType();
		ContentResponseData contentData = new ContentResponseData();
		if ("video".equals(fileType)) {
			QueryWrapper<Video> wrapper = new QueryWrapper<>();
			wrapper.eq("SHARE_ID", share.getEntityId());
			Video video = videoMapper.selectOne(wrapper);

			if (video == null) {
				logger.error("There is no video in data!");
			} else {
				infoData.setThumbnailUrl(video.thumbnailPath());
				contentData.setContentDes(video.description());
				contentData.setContentId(video.entityId());
				contentData.setContentLoc(video.location());
				contentData.setContentName(video.videoName());
				contentData.setContentPath(video.videoPath());
				contentData.setContentSeconds(video.seconds());
				contentData.setContentType(fileType);
				contentResponseDataList.add(contentData);
			}
		} else if ("photo".equals(fileType)) {
			QueryWrapper<Photo> wrapper = new QueryWrapper<>();
			wrapper.eq("SHARE_ID", share.getEntityId());
			List<Photo> photo = photoMapper.selectList(wrapper);

			if (CollectionUtil.isEmpty(photo)) {
				logger.error("There is no photo in data!");
			} else {
				infoData.setThumbnailUrl(photo.get(0).photoPath());
				for(Photo p : photo) {
					contentData = new ContentResponseData();
					contentData.setContentDes(p.description());
					contentData.setContentId(p.entityId());
					contentData.setContentLoc(p.location());
					contentData.setContentName(p.photoName());
					contentData.setContentPath(p.photoPath());
					contentData.setContentType(fileType);
					contentResponseDataList.add(contentData);
				}
			}
		} else {
			logger.warn("Unknown File Type: [{}]!", fileType);
		}
		
		infoData.setContentResponseData(contentResponseDataList);
		return infoData;
	}
	
	private UserDetailResponseData toUserInfoResponseData(UserInfo x, Integer currentUserId) throws MvpShareException {
		if (x == null) {
			logger.error("No Such Data In DataList");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		
		UserDetailResponseData data = new UserDetailResponseData();
		data.setUserId(x.entityId());
		data.setUserRole(x.userRole() == null ? "" : x.userRole().toString());
		data.setMobilePhone(x.mobilePhone());
		data.setAreaCode(x.areaCode());
		data.setUserName(x.userName());
		data.setNickName(x.nickName());
		data.setEmail(x.email());
		data.setIntroduction(x.introduction());
		data.setGender(x.gender() == null ? "M" : (x.gender() == 0 ? "M" : "F"));
		data.setDateOfBirth(x.dateOfBirth());
		data.setLocation(x.location());
		data.setFollowerCount(Integer.valueOf(x.followerCount() == null ? "0" : x.followerCount().toString()));
		data.setFanCount(Integer.valueOf(x.fanCount() == null ? "0" : x.fanCount().toString()));
		data.setUserImgPath(x.userImgPath());
		data.setLoginId(x.loginId());
		data.setPassword(x.password());
		data.setCreationDateTime(x.creationDateTime());
		data.setLastUpdateDateTime(x.lastUpdateDateTime());
		data.setIsFriend(isFriend(currentUserId,x.entityId()).getFlag());
		return data;
	}

	@Override
	public void insertData() {
		UserInfo entity = new UserInfo();
		entity.userRole(1);
		entity.mobilePhone("123456");
		entity.areaCode("111");
		entity.userName("testUser");
		entity.nickName("testUser");
		entity.email("user@163.com");
		entity.introduction("i am hen shuai");
		entity.gender(0);
		//entity.dateOfBirth(LocalDateTime.now());
		entity.location("location");
		entity.followerCount(1);
		entity.fanCount(1);
		entity.userImgPath("/usr/img.png");
		entity.loginId("123");
		entity.password("password");
		//entity.creationDateTime(LocalDateTime.now());
		//entity.lastUpdateDateTime(LocalDateTime.now());
		userInfoMapper.insert(entity);
		
		UserInfo anotherEntity = new UserInfo();
		anotherEntity.userRole(2);
		anotherEntity.mobilePhone("2345678");
		anotherEntity.areaCode("111");
		anotherEntity.userName("testNewUser");
		anotherEntity.nickName("testNewUser");
		anotherEntity.email("newUser@163.com");
		anotherEntity.introduction("i am fei chang shuai");
		anotherEntity.gender(0);
		//anotherEntity.dateOfBirth(LocalDateTime.now());
		anotherEntity.location("location");
		anotherEntity.followerCount(1);
		anotherEntity.fanCount(1);
		anotherEntity.userImgPath("/usr/img.png");
		anotherEntity.loginId("123");
		anotherEntity.password("password");
		//anotherEntity.creationDateTime(LocalDateTime.now());
		//anotherEntity.lastUpdateDateTime(LocalDateTime.now());
		userInfoMapper.insert(anotherEntity);
		
		Share share = new Share();
		share.setUserId(1);
		share.setPostStatus("Y");
		share.setMessage("this is a message");
		share.setFileType("photo");
		share.setLocation("shen zhen");
		share.setToUserId(null);
		share.setCreationDateTime(LocalDateTime.now());
		share.setLastUpdateDateTime(LocalDateTime.now());
		shareMapper.insert(share);
		
		Share anotherShare = new Share();
		anotherShare.setUserId(2);
		anotherShare.setPostStatus("Y");
		anotherShare.setMessage("this is a new message");
		anotherShare.setFileType("video");
		anotherShare.setLocation("shen zhen");
		anotherShare.setToUserId(null);
		anotherShare.setCreationDateTime(LocalDateTime.now());
		anotherShare.setLastUpdateDateTime(LocalDateTime.now());
		shareMapper.insert(anotherShare);
	}

	@Override
	public DiscoverResponseData getDiscover(@NotNull @Valid Integer shareId, @NotNull @Valid Integer fromUserId) throws MvpShareException {
		DiscoverResponseData data = new DiscoverResponseData();
		Share socialShare = shareMapper.selectById(shareId);
		
		if (socialShare == null) {
			logger.info("There is no such data in share data");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}
		
		data.setShareCount(socialShare.getShareCount());
		data.setLikeCount(socialShare.getLikeCount());
		data.setCommentCount(socialShare.getCommentCount());
		
		QueryWrapper<Like> wrapper = new QueryWrapper<>();
		wrapper.eq("SHARE_ID", shareId);
		wrapper.eq("FROM_USER_ID", fromUserId);
		List<Like> socialLike = likeMapper.selectList(wrapper);
		
		if(socialLike != null && socialLike.size() > 0) {
			data.setIsLike(true);
		}else {
			data.setIsLike(false);
		}
		
		return data;
	}

	@Override
	public CommentListResponseData getCommentList(@NotNull @Valid Integer shareId) throws MvpShareException {
		CommentListResponseData data = new CommentListResponseData();
		List<CommentResponseData> commentResponseDataList = new ArrayList<>();
		Integer totalSize = 0;
		QueryWrapper<Comment> wrapper = new QueryWrapper<>();
		wrapper.eq("SHARE_ID", shareId).orderByDesc("CREATION_DATE_TIME");
		List<Comment> commentList = commentMapper.selectList(wrapper);
		
		if(commentList == null || commentList.size() <= 0) {
			logger.info("There is no comment for share");
			data.setShareId(shareId);
			data.setCommentCount(0);
			data.setCommentList(commentResponseDataList);
			return data;
		}
		totalSize = commentList.size();
		data.setShareId(shareId);
		
		for (Comment comment : commentList) {
			commentResponseDataList.add(toCommentResponseData(comment));
			if(commentResponseDataList != null && commentResponseDataList.size() > 0)
			totalSize = totalSize + commentResponseDataList.get(commentResponseDataList.size() - 1).getReplyCount();
		}
		data.setCommentCount(totalSize);
		data.setCommentList(commentResponseDataList);
		return data;
	}
	
	@Override
	public List<ReplyResponseData> getReplyList(@NotNull @Valid Integer commentId) throws MvpShareException {
		List<ReplyResponseData> data = new ArrayList<>();
		QueryWrapper<Reply> wrapper = new QueryWrapper<>();
		wrapper.eq("COMMENT_ID", commentId);
		List<Reply> childReply = replyMapper.selectList(wrapper);
		if(childReply != null && childReply.size() > 0) {
			for (Reply c : childReply) {
				data.add(toReplyResponseData(c));
			}
		}
		return data;
	}
	
	@Override
	public ProfileResponseData getPostList(@Valid Integer currentUserId, @Valid Integer scanUserId) throws MvpShareException {
		ProfileResponseData data = new ProfileResponseData();
		QueryWrapper<Share> wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", scanUserId).eq("POST_STATUS", "Y");
		wrapper.orderByDesc("CREATION_DATE_TIME");
		List<Share> myShareList = shareMapper.selectList(wrapper);
		List<ShareInfoData> shareInfoData = toShareInfoData(currentUserId, myShareList);
		data.setShareList(shareInfoData);
		MyRelationCountResponseData myRelationCount = getMyRelationCount(currentUserId, scanUserId);
		data.setFollowerCount(myRelationCount.getFollowerCount());
		data.setFollowingCount(myRelationCount.getFollowingCount());
		data.setLikedCount(myRelationCount.getLikedCount());
		data.setIsFollowing(myRelationCount.isFollowing());
		return data;
	}
	
	@Override
	public ProfileResponseData getLikeList(@Valid Integer currentUserId, @Valid Integer scanUserId) throws MvpShareException {
		ProfileResponseData data = new ProfileResponseData();
		List<ShareInfoData> shareInfoData = new ArrayList<>();
		QueryWrapper<Like> likeWrapper = new QueryWrapper<>();
		likeWrapper.eq("FROM_USER_ID", scanUserId);
		likeWrapper.orderByDesc("CREATION_DATE_TIME");
		List<Like> socialLikeList = likeMapper.selectList(likeWrapper);
		List<Integer> likeList = new ArrayList<>();
		
		if (socialLikeList != null && socialLikeList.size() > 0) {
			socialLikeList.stream().forEach(x -> {
				likeList.add(x.getShareId());
			});
		}
		if (likeList != null && likeList.size() > 0) {
			List<Share> likeShareList = new ArrayList<>();
			//QueryWrapper<Share> wrapper = new QueryWrapper<>();
			//wrapper.in("ENTITY_ID", likeList);
			for (Integer id : likeList) {
				Share share = shareMapper.selectById(id);
				likeShareList.add(share);
			}
			shareInfoData = toShareInfoData(currentUserId, likeShareList);
		}
		data.setShareList(shareInfoData);
		MyRelationCountResponseData myRelationCount = getMyRelationCount(currentUserId,scanUserId);
		if(myRelationCount != null) {
			data.setFollowerCount(myRelationCount.getFollowerCount());
			data.setFollowingCount(myRelationCount.getFollowingCount());
			data.setLikedCount(myRelationCount.getLikedCount());
			data.setIsFollowing(myRelationCount.isFollowing());
		}
		return data;
	}

	private CommentResponseData toCommentResponseData(Comment comment) throws MvpShareException {
		CommentResponseData data = new CommentResponseData();
		data.setEntityId(comment.getEntityId());
		data.setComment(comment.getMessage());
		data.setCreateTime(WebUtils.getLocalDateTimeAgo(comment.getCreationDateTime()));
		data.setFromUserId(comment.getFromUserId());
		
		UserInfo user = userInfoMapper.selectById(comment.getFromUserId());
		if(user == null) {
			logger.error("there is no such data in userInfo");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		
		data.setFromUserImgPath(user.userImgPath());
		data.setFromUserName(user.nickName());
		
		List<ReplyResponseData> list = new ArrayList<>();
		QueryWrapper<Reply> wrapper = new QueryWrapper<>();
		wrapper.eq("COMMENT_ID", comment.getEntityId());
		List<Reply> childReply = replyMapper.selectList(wrapper);
		if(childReply != null && childReply.size() > 0) {
			for (Reply c : childReply) {
				list.add(toReplyResponseData(c));
			}
		}
		data.setReplyList(list);
		data.setReplyCount(list.size());
		return data;
	}

	private ReplyResponseData toReplyResponseData(Reply reply) throws MvpShareException {
		ReplyResponseData data = new ReplyResponseData();
		data.setEntityId(reply.getEntityId());
		data.setCommentId(reply.getCommentId());
		data.setFromUserId(reply.getFromUserId());
		
		UserInfo user = userInfoMapper.selectById(reply.getFromUserId());
		if(user == null) {
			logger.error("there is no such user in userData");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		
		data.setFromUserImgPath(user.userImgPath());
		data.setFromUserName(user.nickName());
		data.setComment(reply.getReply());
		data.setToUserId(reply.getToUserId());
		UserInfo toUser = userInfoMapper.selectById(reply.getToUserId());
		if(toUser == null) {
			logger.error("there is no such user in userData");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		data.setToUserName(toUser.nickName());
		data.setCreateTime(WebUtils.getLocalDateTimeAgo(reply.getCreationDateTime()));
		
		return data;
	}

	@Override
	public CommentResponseData addComment(@Valid CommentRequest commentRequest) throws MvpShareException {
		CommentResponseData data = new CommentResponseData();
		Comment comment = new Comment();
		Share share = shareMapper.selectById(commentRequest.getShareId());
		
		if(share == null) {
			logger.info("There is no such share in shareList");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}
		
		comment.setShareId(commentRequest.getShareId());
		comment.setCreationDateTime(LocalDateTime.now());
		comment.setFromUserId(commentRequest.getFromUserId());
		comment.setMessage(commentRequest.getComment());
		commentMapper.insert(comment);
		
		Integer commentCount = share.getCommentCount();
		share.setCommentCount(commentCount + 1);
		shareMapper.updateById(share);
		
		data.setEntityId(commentRequest.getEntityId());
		data.setFromUserId(commentRequest.getFromUserId());
		data.setComment(commentRequest.getComment());
		data.setCreateTime(WebUtils.getLocalDateTimeAgo(LocalDateTime.now()));
		
		UserInfo user = userInfoMapper.selectById(commentRequest.getFromUserId());
		if(user == null) {
			logger.error("there is no such user in userData");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		
		data.setFromUserImgPath(user.userImgPath());
		data.setFromUserName(user.nickName());
		//data.setReplyList(null);
		return data;
	}
	
	@Override
	public ReplyResponseData addReply(@Valid ReplyRequest replyRequest) throws MvpShareException {
		ReplyResponseData data = new ReplyResponseData();
		Reply reply = new Reply();
		Share share = shareMapper.selectById(replyRequest.getShareId());
		if(share == null) {
			logger.info("There is no such share in shareList");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}
		
		reply.setCommentId(replyRequest.getCommentId());
		reply.setCreationDateTime(LocalDateTime.now());
		reply.setFromUserId(replyRequest.getFromUserId());
		reply.setReply(replyRequest.getReply());
		reply.setToUserId(replyRequest.getToUserId());
		replyMapper.insert(reply);
		
		Integer commentCount = share.getCommentCount();
		share.setCommentCount(commentCount + 1);
		shareMapper.updateById(share);
		
		data.setCommentId(replyRequest.getCommentId());
		data.setCreateTime(WebUtils.getLocalDateTimeAgo(LocalDateTime.now()));
		data.setFromUserId(replyRequest.getFromUserId());
		
		UserInfo user = userInfoMapper.selectById(replyRequest.getFromUserId());
		if (user == null) {
			logger.error("there is no such user in userData");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
		}
		
		data.setFromUserImgPath(user.userImgPath());
		data.setFromUserName(user.nickName());
		data.setComment(replyRequest.getReply());
		data.setToUserId(replyRequest.getToUserId());
		return data;
	}

	@Override
	public void addViewAmount(Integer shareId) throws MvpShareException {
		Share socialShare = shareMapper.selectById(shareId);
		if(socialShare == null) {
			logger.error("there is no such data in social share");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}
		Integer viewAmount = socialShare.getViewAmount();
		viewAmount = viewAmount + 1;
		socialShare.setViewAmount(viewAmount);
		shareMapper.updateById(socialShare);
	}
	
	@Override
	public void deleteCommentList(@NotNull @Valid Integer shareId, @NotNull @Valid Integer entityId, @NotNull @Valid String flag) throws MvpShareException {
		Share share = shareMapper.selectById(shareId);
		
		if (share == null) {
			logger.error("there is no such share in share data");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_001_01);
		}

		if(flag.equals("COMMENT")) {
			commentMapper.deleteById(entityId);
			UpdateWrapper<Reply> wrapper = new UpdateWrapper<>();
			wrapper.eq("COMMENT_ID", entityId);
			replyMapper.delete(wrapper);
		}else if (flag.equals("REPLY")) {
			replyMapper.deleteById(entityId);
		}
		
		Integer commentCount = share.getCommentCount();
		if(commentCount <= 0) {
			logger.info("There is no comment for share");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_005_01);
		}
		commentCount = commentCount - 1;
		share.setCommentCount(commentCount);
		shareMapper.updateById(share);
	}
	
	@Override
	public void deleteFan(@NotNull @Valid Integer userId, @NotNull @Valid Integer fanId) throws MvpShareException {
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", userId).eq("FAN_ID", fanId);
		Fan f = fanMapper.selectOne(wrapper);
		if(f == null) {
			logger.info("there is no such data in fan data");
			return;
		}
		fanMapper.delete(wrapper);
		updateFollowAndFanCount(fanId, userId, "N");
	}

	@Override
	public FriendListResponseData findFriend(@NotNull @Valid Integer userId, @NotNull @Valid String searchContent) throws MvpShareException {
		if(searchContent == null) {
			searchContent = "";
		}
		FriendListResponseData data = new FriendListResponseData();
		List<FriendResponseData> list = getNonFriendList(userId).getFansList();
		List<FriendResponseData> deleteList = new ArrayList<>();
		if(list != null && list.size() > 0) {
			for (FriendResponseData friendResponseData : list) {
				UserInfo user = userInfoMapper.selectById(friendResponseData.getFanId());
				QueryWrapper<SystemSetting> settingWrapper = new QueryWrapper<>();
				settingWrapper.eq("USER_ID", friendResponseData.getFanId());
				SystemSetting systemSetting = systemSettingMapper.selectOne(settingWrapper);
				if(systemSetting != null && systemSetting.searchAllow().equals("N")) {
					deleteList.add(friendResponseData);
				}
				if(user == null) {
					logger.error("there is no such user in userInfo data");
					throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
				}
				
				if( !(contain(user.nickName(),searchContent) || user.mobilePhone().contains(searchContent) || contain(Pinyin4jUtil.getPinyin(user.nickName()), searchContent))) {
					deleteList.add(friendResponseData);
				}
			}
		}
		deleteList = deleteList.stream().distinct().collect(Collectors.toList());
		list.removeAll(deleteList);
		list.removeIf(l -> l.getFanId().equals(userId));
		/*
		if(searchContent == "") {
			getRecommend(list,userId);
		}
		*/
		data.setFansList(list);
		return data;
	}

	private FriendResponseData toFriendResponseData(UserInfo x) {
		FriendResponseData data = new FriendResponseData();
		//data.setRegisterFlag();
		data.setCreateTime(x.creationDateTime());
		data.setFanId(x.entityId());
		data.setFanImgPath(x.userImgPath());
        data.setInitials(Pinyin4jUtil.getFirstToUpperCase(x.nickName()));
		data.setFanName(x.nickName());
		data.setFanMobilePhone(x.mobilePhone());
		return data;
	}

	@Override
	public FriendByMobileContactsResponseData findFriendByMobileContacts(@NotNull @Valid FriendByMobileContactsRequest findFriendByMobileContactsRequest) {
		List<String> mobileContacts = new ArrayList<>();
		findFriendByMobileContactsRequest.mobileContactsList().stream().forEach(x -> mobileContacts.add(x.getMobilePhone()));
		redisUtils.set("mobileContacts", mobileContacts);
		if(findFriendByMobileContactsRequest.searchContent() == null) {
			findFriendByMobileContactsRequest.searchContent("");
		}
		FriendByMobileContactsResponseData data = new FriendByMobileContactsResponseData();
		List<MobileContactsResponseData> responseData = new ArrayList<>();
		List<String> phoneList = new ArrayList<>();
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		if(findFriendByMobileContactsRequest != null && findFriendByMobileContactsRequest.mobileContactsList().size() !=0) {
			for (MobileContactsRequestData requestData : findFriendByMobileContactsRequest.mobileContactsList()) {
				phoneList.add(requestData.getMobilePhone());
			}
			wrapper.in("MOBILE_PHONE", phoneList);
			List<UserInfo> userList = userInfoMapper.selectList(wrapper);
			if(userList != null && userList.size() > 0) {
				for (UserInfo userInfo : userList) {
					findFriendByMobileContactsRequest.mobileContactsList().removeIf(remove -> remove.getMobilePhone().equals(userInfo.mobilePhone()));
				}
			}
		}
		
		for (MobileContactsRequestData requestData : findFriendByMobileContactsRequest.mobileContactsList()) {
			if(contain(requestData.getUserName(),findFriendByMobileContactsRequest.searchContent()) || requestData.getMobilePhone().contains(findFriendByMobileContactsRequest.searchContent()) || contain(Pinyin4jUtil.getPinyin(requestData.getUserName()), findFriendByMobileContactsRequest.searchContent())) {
				MobileContactsResponseData resultData = new MobileContactsResponseData();
				resultData.setUserName(requestData.getUserName());
				resultData.setMobilePhone(requestData.getMobilePhone());
				resultData.setInitials(Pinyin4jUtil.getFirstToUpperCase(requestData.getUserName()));
				responseData.add(resultData);
			}
		}
		
		data.setMobileContacts(responseData);
		return data;
	}

	@Override
	public FriendListResponseData getFriendList(@NotNull @Valid Integer userId, String searchContent) {
		if(searchContent == null) {
			searchContent = "";
		}
		FriendListResponseData data = new FriendListResponseData();
		List<FriendResponseData> responseDatas = new ArrayList<>();
		QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
		fanWrapper.eq("USER_ID", userId);
		List<Fan> fanList = fanMapper.selectList(fanWrapper);
		List<Integer> fanIdList = new ArrayList<>();
		if(fanList != null && fanList.size() > 0) {
			fanList.stream().forEach(x -> {
				fanIdList.add(x.getFanId());
			});
		}
		if(fanIdList == null || fanIdList.size() <= 0) {
			logger.info("there is no friend for this user now");
			data.setFansList(responseDatas);
			return data;
		}
		fanWrapper.clear();
		fanWrapper.in("USER_ID", fanIdList);
		List<Fan> fans = fanMapper.selectList(fanWrapper);
		List<UserInfo> userInfos = new ArrayList<>();
		if(fans != null && fans.size() > 0) {
			for (Fan fan : fans) {
				if (fan.getFanId().equals(userId)) {
					QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
					userWrapper.eq("ENTITY_ID", fan.getUserId());
					UserInfo user = userInfoMapper.selectOne(userWrapper);
					if(contain(user.nickName(),searchContent) || user.mobilePhone().contains(searchContent) || contain(Pinyin4jUtil.getPinyin(user.nickName()), searchContent)) {
						userInfos.add(user);
					}
				}
			}
		}
		userInfos.removeIf(Objects::isNull);
		if(userInfos != null && userInfos.size() > 0) {
			userInfos = userInfos.stream().sorted(Comparator.comparing(UserInfo::nickName)).collect(Collectors.toList());
			userInfos.stream().forEach(x -> {
				responseDatas.add(toFriendResponseData(x));
			});
		}
		data.setFansList(responseDatas);
		return data;
	}
	
	@Override
	public FriendResponseData addFriend(@Valid AddFriendRequest addFriendRequest) throws MvpShareException {
		if (addFriendRequest.getUserId().equals(addFriendRequest.getFanId())) {
			logger.error("You can not add yourself as friend");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_013_01);
		}
		FriendResponseData data = new FriendResponseData();
		if(addFriendRequest != null) {
			UserInfo user = userInfoMapper.selectById(addFriendRequest.getUserId());
			if(user == null) {
				logger.error("there is no such user in user data");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
			}
			UserInfo fan = userInfoMapper.selectById(addFriendRequest.getFanId());
			if(fan == null) {
				logger.error("there is no such fan in user data");
				throw new MvpShareException(MvpShareExceptionCode.ERROR_004_01);
			}
			Fan socialFan = new Fan();
			socialFan.setUserId(addFriendRequest.getUserId());
			socialFan.setFanId(addFriendRequest.getFanId());
			socialFan.setCreationDateTime(LocalDateTime.now());
			socialFan.setIsBlack("0");
			
			QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
			fanWrapper.eq("USER_ID", addFriendRequest.getUserId());
			fanWrapper.eq("FAN_ID", addFriendRequest.getFanId());
			Fan localFan = fanMapper.selectOne(fanWrapper);
			if(localFan == null) {
				fanMapper.insert(socialFan);
				updateFollowAndFanCount(addFriendRequest.getFanId(), addFriendRequest.getUserId(), "Y");
			}
			socialFan.setUserId(addFriendRequest.getFanId());
			socialFan.setFanId(addFriendRequest.getUserId());
			fanWrapper.clear();
			fanWrapper.eq("USER_ID", addFriendRequest.getFanId());
			fanWrapper.eq("FAN_ID", addFriendRequest.getUserId());
			localFan = fanMapper.selectOne(fanWrapper);
			if(localFan == null) {
				fanMapper.insert(socialFan);
				updateFollowAndFanCount(addFriendRequest.getUserId(), addFriendRequest.getFanId(), "Y");
			}
			data.setFanId(fan.entityId());
			data.setFanImgPath(fan.userImgPath());
			data.setFanName(fan.nickName());
			data.setRegisterFlag(true);
			data.setUserId(user.entityId());
			data.setUserImgPath(user.userImgPath());
			data.setUserName(user.nickName());
			data.setCreateTime(LocalDateTime.now());
		}
		return data;
	}

	@Override
	public IsFriendResponseData isFriend(@NotNull @Valid Integer userId, @NotNull @Valid Integer fanId) {
		IsFriendResponseData data = new IsFriendResponseData();
		data.setUserId(userId);
		data.setFanId(fanId);
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", userId).eq("FAN_ID", fanId);
		Fan fan = fanMapper.selectOne(wrapper);
		if(fan == null) {
			data.setFlag(false);
			return data;
		}
		wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", fanId).eq("FAN_ID", userId);
		Fan userFan = fanMapper.selectOne(wrapper);
		if(userFan != null) {
			data.setFlag(true);
		}else {
			data.setFlag(false);
		}
		return data;
	}
	
	@Override
	public InviteFriendByMobileContactsResponseData inviteFriendByMobileContacts(InviteFriendByMobileContactsRequest request, String token) throws MvpShareException {
		InviteFriendByMobileContactsResponseData data = new InviteFriendByMobileContactsResponseData();
		SendSMSResponseData smsData = new SendSMSResponseData();
		//TODO through the mobile number to notice friends
		try {
			smsData = userInfoService.sendSMS(token, request.getMobilePhone());
		} catch (MvpUserException e) {
			logger.error("Send Invitation Failure");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_012_01);
		}
		data.setMessage(smsData.SMS());
		return data;
	}
	
	private FriendListResponseData getNonFriendList(@NotNull @Valid Integer userId) {
		FriendListResponseData data = new FriendListResponseData();
		List<FriendResponseData> fansList = getFriendList(userId, null).getFansList();
		List<UserInfo> userInfos = new ArrayList<>();
		if(fansList != null && fansList.size() > 0) {
			for (FriendResponseData friendResponseData : fansList) {
				userInfos.add(userInfoMapper.selectById(friendResponseData.getFanId()));
			}
		}
		
		List<FriendResponseData> responseDatas = new ArrayList<>();
		List<UserInfo> allUserList = userInfoMapper.selectList(new QueryWrapper<>());
		if(userInfos != null && userInfos.size() > 0) {
			userInfos.stream().forEach(x -> {
				allUserList.removeIf(a -> a.entityId().equals(x.entityId()));
			});
		}
		if(allUserList != null && allUserList.size() > 0) {
			allUserList.stream().forEach(x ->{
				responseDatas.add(toFriendResponseData(x));
			});
		}
		data.setFansList(responseDatas);
		return data;
	}
	
	private boolean contain(String input, String search) {  
        Pattern p = Pattern.compile(search, Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(input);  
        boolean result = m.find();  
        return result;  
    }
	
	private void updateFollowAndFanCount(Integer currentUserId, Integer scanUserId, String flag) throws MvpShareException {
		UserInfo currentUser = userInfoMapper.selectById(currentUserId);
		UserInfo scanUser = userInfoMapper.selectById(scanUserId);
		Integer followerCount = Optional.ofNullable(currentUser.followerCount()).orElse(0);
		Integer fanCount = Optional.ofNullable(scanUser.fanCount()).orElse(0);
		if(flag.equals("Y")) {
			followerCount ++;
			fanCount ++;
		}else {
			followerCount --;
			fanCount --;
		}
		if(followerCount < 0 || fanCount < 0) {
			logger.error("there is no follow or fan count for delete");
			throw new MvpShareException(MvpShareExceptionCode.ERROR_005_01);
		}
		currentUser.followerCount(followerCount);
		userInfoMapper.updateById(currentUser);
		scanUser.fanCount(fanCount);
		userInfoMapper.updateById(scanUser);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private List<FriendResponseData> getRecommend(List<FriendResponseData> list,Integer userId) {
		List<FriendResponseData> deleteData = new ArrayList<>();
		QueryWrapper<Fan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", userId);
		List<Fan> myFollows = fanMapper.selectList(wrapper);
		List<Integer> compare = new ArrayList<>();
		for (Fan  myFollw: myFollows) {
			QueryWrapper<Fan> fanWrapper = new QueryWrapper<>();
			fanWrapper.eq("FAN_ID", myFollw.getUserId());
			List<Fan> otherFollows = fanMapper.selectList(fanWrapper);
			for (Fan otherFollow : otherFollows) {
				compare.add(otherFollow.getUserId());
			}
		}
		for (FriendResponseData data : list) {
			List<String> mobileContacts = (List<String>)redisUtils.get("mobileContacts");
			if(mobileContacts == null || mobileContacts.size() <=0) {
				logger.info("there is no mobileList in redis");
				list.clear();
				return list;
			}
			if(mobileContacts.contains(data.getFanMobilePhone())) {
				//set the type to someone you might know
				data.setType(0);
			}else if(compare.contains(data.getFanId())){
				//set the type to your address book friend
				data.setType(1);
			}else {
				//delete the non-type
				deleteData.add(data);
			}
			
		}
		list.removeAll(deleteData);
		return list;
	}

}
