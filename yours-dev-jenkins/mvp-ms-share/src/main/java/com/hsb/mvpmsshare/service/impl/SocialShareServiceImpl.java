package com.hsb.mvpmsshare.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hsb.mvpmsshare.dao.CommentMapper;
import com.hsb.mvpmsshare.dao.PhotoMapper;
import com.hsb.mvpmsshare.dao.ReplyMapper;
import com.hsb.mvpmsshare.dao.SocialFanMapper;
import com.hsb.mvpmsshare.dao.SocialLikeMapper;
import com.hsb.mvpmsshare.dao.SocialMessageMapper;
import com.hsb.mvpmsshare.dao.SocialShareMapper;
import com.hsb.mvpmsshare.dao.UserInfoMapper;
import com.hsb.mvpmsshare.dao.VideoMapper;
import com.hsb.mvpmsshare.model.domain.Comment;
import com.hsb.mvpmsshare.model.domain.Photo;
import com.hsb.mvpmsshare.model.domain.Reply;
import com.hsb.mvpmsshare.model.domain.SocialFan;
import com.hsb.mvpmsshare.model.domain.SocialLike;
import com.hsb.mvpmsshare.model.domain.SocialMessage;
import com.hsb.mvpmsshare.model.domain.SocialShare;
import com.hsb.mvpmsshare.model.domain.UserInfo;
import com.hsb.mvpmsshare.model.domain.Video;
import com.hsb.mvpmsshare.model.payload.AllFanListResponseData;
import com.hsb.mvpmsshare.model.payload.AllFollowingUserListResponseData;
import com.hsb.mvpmsshare.model.payload.CommentListResponseData;
import com.hsb.mvpmsshare.model.payload.CommentResponseData;
import com.hsb.mvpmsshare.model.payload.DiscoverResponseData;
import com.hsb.mvpmsshare.model.payload.DraftListResponseData;
import com.hsb.mvpmsshare.model.payload.FilesPathListResponseData;
import com.hsb.mvpmsshare.model.payload.FollowingListResponseData;
import com.hsb.mvpmsshare.model.payload.FollowingUserByUserNameResponseData;
import com.hsb.mvpmsshare.model.payload.FriendByMobileContactsResponseData;
import com.hsb.mvpmsshare.model.payload.FriendListResponseData;
import com.hsb.mvpmsshare.model.payload.FriendResponseData;
import com.hsb.mvpmsshare.model.payload.InviteFriendByMobileContactsResponseData;
import com.hsb.mvpmsshare.model.payload.PhotosData;
import com.hsb.mvpmsshare.model.payload.RecentlyUpdatedUserResponseData;
import com.hsb.mvpmsshare.model.payload.ReplyResponseData;
import com.hsb.mvpmsshare.model.payload.ShareInfoData;
import com.hsb.mvpmsshare.model.payload.ShareListResponseData;
import com.hsb.mvpmsshare.model.payload.ShareRelationCountResponseData;
import com.hsb.mvpmsshare.model.payload.ShareResponseData;
import com.hsb.mvpmsshare.model.payload.UserDetailResponseData;
import com.hsb.mvpmsshare.model.payload.VideosData;
import com.hsb.mvpmsshare.model.payload.request.AddFriendRequest;
import com.hsb.mvpmsshare.model.payload.request.AllFanListRequest;
import com.hsb.mvpmsshare.model.payload.request.CancelFanRequest;
import com.hsb.mvpmsshare.model.payload.request.CancelFollowRequest;
import com.hsb.mvpmsshare.model.payload.request.CommentRequest;
import com.hsb.mvpmsshare.model.payload.request.FollowRequest;
import com.hsb.mvpmsshare.model.payload.request.FollowingListByCreateTimeRequest;
import com.hsb.mvpmsshare.model.payload.request.FollowingUserByUserNameRequest;
import com.hsb.mvpmsshare.model.payload.request.FollowingUserListRequest;
import com.hsb.mvpmsshare.model.payload.request.RecentlyUpdatedUserRequest;
import com.hsb.mvpmsshare.model.payload.request.ShareRelationCountRequest;
import com.hsb.mvpmsshare.model.payload.request.SocialLikeRequest;
import com.hsb.mvpmsshare.model.payload.request.SocialMsgRequest;
import com.hsb.mvpmsshare.model.payload.request.SocialShareRequest;
import com.hsb.mvpmsshare.service.SocialShareService;

import cn.hutool.core.date.DateUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class SocialShareServiceImpl implements SocialShareService {
	
	private final static Logger logger = LoggerFactory.getLogger(SocialShareServiceImpl.class);
	
	@Autowired
	private SocialShareMapper socialShareMapper;
	
	@Autowired
	private SocialLikeMapper socialLikeMapper;
	
	@Autowired
	private SocialMessageMapper socialMessageMapper;
	
	@Autowired
	private SocialFanMapper socialFanMapper;
	
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

	@Override
	public FilesPathListResponseData uploadFiles(Integer userId, MultipartFile[] multipartFiles) {
		FilesPathListResponseData filesPathListResponseData = new FilesPathListResponseData();
		List<String> urlList = new ArrayList<String>();

		if (multipartFiles.length > 0) {

			String filename = null;
			for (MultipartFile multipartFile : multipartFiles) {
				
				File file = new File(System.getProperty("user.dir").replace("\\", "/"));
		        String savePath = file.getParent().replace("\\", "/")+"/webapps/manager/useruploadFile";
		        String returnPath = "/manager/useruploadFile";
				String name = multipartFile.getOriginalFilename();
				String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());
				if (!"".equals(subffix) && (subffix.equals("mp4") || subffix.equals("mov") || subffix.equals("avi")
						|| subffix.equals("wmv") || subffix.equals("m4v") || subffix.equals("dat")
						|| subffix.equals("flv") || subffix.equals("mkv"))) {
					savePath = savePath + "/" + userId + "/videos/";
					returnPath = returnPath + "/" + userId + "/videos/";
				} else if (!"".equals(subffix) && (subffix.equals("jpg") || subffix.equals("png"))) {
					savePath = savePath + "/" + userId + "/photos/";
					returnPath = returnPath + "/" + userId + "/photos/";
				} else {

				}
				File dir = new File(savePath);
				if (!dir.exists())
					dir.mkdirs();
				try {
					filename = userId + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss")
							+ multipartFile.getOriginalFilename();
					multipartFile.transferTo(new File(savePath, filename));
					urlList.add(returnPath + filename);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		filesPathListResponseData.setFilesPathList(urlList);
		return filesPathListResponseData;
	}

	@Override
	public DraftListResponseData getDraftContent(Integer userId) {
		QueryWrapper<SocialShare> contentWrapper = new QueryWrapper<>();
		contentWrapper.eq("USER_ID", userId);
		contentWrapper.eq("POST_STATUS", "N");
		SocialShare socialShare = socialShareMapper.selectOne(contentWrapper);
		DraftListResponseData draftListResponseData = new DraftListResponseData();
		if(socialShare!=null) {
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
			if("photo".equals(socialShare.getFileType()) && !photoMapper.selectList(photoWrapper).isEmpty()) {
				List<Photo> photoList = photoMapper.selectList(photoWrapper);
				for(Photo photo : photoList) {
					PhotosData photosData = new PhotosData();
					photosData.setPhotoDes(photo.description());
					photosData.setPhotoLoc(photo.location());
					photosData.setPhotoName(photo.photoName());
					photosData.setPhotoPath(photo.photoPath());
					photoDataList.add(photosData);
				}
			}else if("video".equals(socialShare.getFileType()) && videoMapper.selectOne(videoWrapper)!=null) {
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
		}
		return draftListResponseData;
	}

	@Override
	@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public void postContent(SocialShareRequest socialShareRequest) {
		// add a new draft
		SocialShare socialShare = new SocialShare();
		socialShare.setUserId(socialShareRequest.getUserId());
		socialShare.setPostStatus(socialShareRequest.getPostStatus());
		socialShare.setMessage(socialShareRequest.getMessage());
		socialShare.setFileType(socialShareRequest.getFileType());
		socialShare.setLocation(socialShareRequest.getLocation());
		socialShare.setLikeCount(socialShareRequest.getLikeCount());
		socialShare.setCommentCount(socialShareRequest.getCommentCount());
		socialShare.setShareCount(socialShareRequest.getShareCount());
		socialShare.setViewAmount(socialShareRequest.getViewAmount());
		socialShare.setCreationDateTime(LocalDateTime.now());
		socialShare.setLastUpdateDateTime(LocalDateTime.now());
		if (!"".equals(socialShareRequest.getPostStatus()) && "N".equals(socialShareRequest.getPostStatus())) {
			// delete last update draft
			QueryWrapper<SocialShare> fanWrapper = new QueryWrapper<>();
			fanWrapper.eq("USER_ID", socialShareRequest.getUserId());
			fanWrapper.eq("POST_STATUS", "N");
			SocialShare socialShareDraft = socialShareMapper.selectOne(fanWrapper);
			if (socialShareDraft != null) {
				socialShare.setEntityId(socialShareDraft.getEntityId());
				QueryWrapper<Photo> photoWrapper = new QueryWrapper<>();
				QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
				photoWrapper.eq("SHARE_ID", socialShareDraft.getEntityId());
				videoWrapper.eq("SHARE_ID", socialShareDraft.getEntityId());
				photoMapper.delete(photoWrapper);	
				videoMapper.delete(videoWrapper);
				socialShareMapper.deleteById(socialShareDraft.getEntityId());
			}	
			socialShareMapper.insert(socialShare);
		}else {
			// if @someones
			if (!socialShareRequest.getToUserIds().isEmpty()) {
				for (Integer toUserId : socialShareRequest.getToUserIds()) {
					socialShare.setToUserId(toUserId);
					socialShareMapper.insert(socialShare);
				}
			}
		}
		
		if ("photo".equals(socialShareRequest.getFileType())) {
			for (PhotosData photoData : socialShareRequest.getPhotos()) {
				Photo photo = new Photo();
				photo.shareId(socialShare.getEntityId())
					.photoPath(photoData.getPhotoPath())
					.location(photoData.getPhotoLoc())
					.description(photoData.getPhotoDes())
					.photoName(photoData.getPhotoName())
					.creationDateTime(LocalDateTime.now());
				photoMapper.insert(photo);
			}
		} else if ("video".equals(socialShareRequest.getFileType())) {
			Video video = new Video();
			video.shareId(socialShare.getEntityId())
				.videoName(socialShareRequest.getVideos().getVideoName())
				.seconds(socialShareRequest.getVideos().getVideoSeconds())
				.location(socialShareRequest.getVideos().getVideoLoc())
				.description(socialShareRequest.getVideos().getVideoDes())
				.videoPath(socialShareRequest.getVideos().getVideoPath())
				.thumbnailPath(socialShareRequest.getVideos().getThumbnailUrl())
				.creationDateTime(LocalDateTime.now());
			videoMapper.insert(video);
		}

	}

	@Override
	public void likeContent(SocialLikeRequest socialLikeRequest) {
		SocialLike socialLike = new SocialLike();
		socialLike.setShareId(socialLikeRequest.getShareId());
		socialLike.setFromUserId(socialLikeRequest.getFromUserId());
		socialLike.setCreationDateTime(LocalDateTime.now());
		socialLikeMapper.insert(socialLike);

	}

	@Override
	public void deleteContent(Integer shareId) {
		socialShareMapper.deleteById(shareId);
	}

	@Override
	public void cancelLike(Integer likeId) {
		socialLikeMapper.deleteById(likeId);

	}

	@Override
	public void shareContent(SocialMsgRequest socialMsgRequest) {
		SocialMessage socialMessage = new SocialMessage();
		socialMessage.setShareId(socialMsgRequest.getShareId());
		socialMessage.setMsg(socialMsgRequest.getMessage());
		socialMessage.setFromUserId(socialMsgRequest.getFromUserId());
		socialMessage.setToUserId(socialMsgRequest.getToUserId());
		socialMessage.setIsRead(socialMsgRequest.getIsRead());
		socialMessage.setCreationDateTime(LocalDateTime.now());
		socialMessageMapper.insert(socialMessage);

	}

	@Override
	public FollowingListResponseData getFollowingListByCreateTime(
			@Valid FollowingListByCreateTimeRequest followingListByCreateTimeRequest) {
		List<SocialShare> shareList = null;
		FollowingListResponseData data = new FollowingListResponseData();
		QueryWrapper<SocialFan> fanWrapper = new QueryWrapper<>();
		fanWrapper.eq("FAN_ID", followingListByCreateTimeRequest.getUserId());
		List<Integer> followingIdList = new ArrayList<>();
		List<SocialFan> followingList = socialFanMapper.selectList(fanWrapper);
		
		if (followingList ==null || followingList.size() <= 0) {
			logger.info("There is no following List for current user");
			return data;
		}else {
			followingList.stream().forEach(x -> {
				followingIdList.add(x.getUserId());
			});
			
			QueryWrapper<SocialShare> shareWrapper = new QueryWrapper<>();
			shareWrapper.in("USER_ID", followingIdList);
			shareWrapper.orderByDesc("CREATION_DATE_TIME");
			shareList = socialShareMapper.selectList(shareWrapper);
		}
		data = toShareInfoResponseData(shareList,followingIdList,followingListByCreateTimeRequest.getRefreshTime());
		return data;
	}

	@Override
	public ShareListResponseData getShareListByCreateTime() {
		QueryWrapper<SocialShare> shareWrapper = new QueryWrapper<>();
		shareWrapper.orderByDesc("CREATION_DATE_TIME");
		List<SocialShare> shareList = socialShareMapper.selectList(shareWrapper);
		ShareListResponseData data = new ShareListResponseData();
		data.setShareList(toShareInfoData(shareList));
		return data;
	}
	
	@Override
	public ShareResponseData getShareById(Integer shareId) {
		SocialShare shareResult = socialShareMapper.selectById(shareId);
		ShareResponseData data = new ShareResponseData();
		data = toShareResponseData(shareResult);
		return data;
	}
	
	@Override
	public ShareRelationCountResponseData getShareRelationCount(ShareRelationCountRequest shareRelationCountRequest) {
		SocialShare socialShare = socialShareMapper.selectById(shareRelationCountRequest.getShareId());
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
	public void follow(@Valid FollowRequest followRequest) {
		SocialFan socialFan = new SocialFan();
		socialFan.setUserId(followRequest.getFanId());
		socialFan.setFanId(followRequest.getUserId());
		socialFan.setCreationDateTime(LocalDateTime.now());
		socialFan.setIsBlack("0");
		socialFanMapper.insert(socialFan);
	}

	@Override
	public AllFanListResponseData getAllFanList(@Valid AllFanListRequest allFanListRequest) {
		AllFanListResponseData data = new AllFanListResponseData();
		List<UserDetailResponseData> userResponseDataList = new ArrayList<>();
		QueryWrapper<SocialFan> wrapper = new QueryWrapper<>();
		wrapper.eq("USER_ID", allFanListRequest.getUserId());
		List<SocialFan> socialFanList = socialFanMapper.selectList(wrapper);
		
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
		
		userInfoList.stream().forEach(x -> {
			userResponseDataList.add(toUserInfoResponseData(x));
		});
		data.setUserList(userResponseDataList);
		
		return data;
	}

	@Override
	public AllFollowingUserListResponseData getAllFollowingUserList(
			@Valid FollowingUserListRequest followingUserListRequest) {
		AllFollowingUserListResponseData data = new AllFollowingUserListResponseData();
		List<UserDetailResponseData> userResponseDataList = new ArrayList<>();
		QueryWrapper<SocialFan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", followingUserListRequest.getUserId());
		List<SocialFan> followingList = socialFanMapper.selectList(wrapper);
		
		if(followingList == null || followingList.size() <=0) {
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
		
		if(userInfoList == null || userInfoList.size() <=0 ) {
			logger.info("There is no following user list in user data");
			return data;
		}
		
		userInfoList.stream().forEach(x -> {
			userResponseDataList.add(toUserInfoResponseData(x));
		});
		
		data.setUserList(userResponseDataList);
		return data;
	}

	@Override
	public RecentlyUpdatedUserResponseData getRecentlyUpdatedUser(
			@Valid RecentlyUpdatedUserRequest recentlyUpdatedUserRequest) {
		RecentlyUpdatedUserResponseData data = new RecentlyUpdatedUserResponseData();
		List<UserDetailResponseData> userListResponseData = new ArrayList<>();
		QueryWrapper<SocialFan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", recentlyUpdatedUserRequest.getUserId());
		List<SocialFan> followList = socialFanMapper.selectList(wrapper);
		
		if(followList == null || followList.size() <= 0) {
			logger.info("there is no following data in fan list");
			return data;
		}
		
		//Temporarily set to show the first five followings
		List<SocialShare> recentlyShareList = new ArrayList<>(5); 
		followList.stream().forEach(x -> {
			QueryWrapper<SocialShare> shareWrapper = new QueryWrapper<>();
			shareWrapper.eq("USER_ID", x.getEntityId());
			shareWrapper.orderByDesc("CREATION_DATE_TIME");
			List<SocialShare> shareList = socialShareMapper.selectList(shareWrapper);
			
			if(shareList == null || shareList.size()  <=0 ) {
				logger.info("there is no following share in share List for userId:{[]}",x.getEntityId());
			}else {
				recentlyShareList.add(shareList.get(0));
			}
		});
		
		
		List<SocialShare> orderShareList = recentlyShareList.stream().sorted(Comparator.comparing(SocialShare::getCreationDateTime).reversed()).collect(Collectors.toList());
		
		if(orderShareList == null || orderShareList.size() <= 0 ) {
			logger.info("there is no share for all following user");
			return data;
		}
		
		orderShareList.stream().forEach(x -> {
			userListResponseData.add(toUserInfoResponseData(userInfoMapper.selectById(x.getUserId())));
		});
		
		data.setUserList(userListResponseData);
		return data;
	}
	
	@Override
	public void cancelFan(@Valid CancelFanRequest cancelFanRequest) {
		socialFanMapper.deleteById(cancelFanRequest.getEntityId());
	}

	@Override
	public void cancelFollow(@Valid CancelFollowRequest cancelFollowRequest) {
		socialFanMapper.deleteById(cancelFollowRequest.getEntityId());
	}

	@Override
	public FollowingUserByUserNameResponseData findFollowingByUserName(
			@Valid FollowingUserByUserNameRequest followingUserByUserNameRequest) {
		FollowingUserByUserNameResponseData data = new FollowingUserByUserNameResponseData();
		List<UserDetailResponseData> listUserResponseData= new ArrayList<>();
		
		QueryWrapper<SocialFan> wrapper = new QueryWrapper<>();
		wrapper.eq("FAN_ID", followingUserByUserNameRequest.getUserId());
		List<SocialFan> socialFanlist = socialFanMapper.selectList(wrapper);
		
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
		userWrapper.like("USER_NAME", followingUserByUserNameRequest.getSearchContent());
		List<UserInfo> userInfoList = userInfoMapper.selectList(userWrapper);
		
		if(userInfoList == null || userInfoList.size() == 0) {
			logger.info("there is no such user through filter");
			return data;
		}
		
		userInfoList.stream().forEach(x -> {
			UserDetailResponseData userResponseData = toUserInfoResponseData(x);
			listUserResponseData.add(userResponseData);
		});
		data.setUserList(listUserResponseData);
		return data;
	}
	
	private FollowingListResponseData toShareInfoResponseData(List<SocialShare> shareList,List<Integer> followingIdList,LocalDateTime refreshTime) {
		FollowingListResponseData data = new FollowingListResponseData();
		List<ShareInfoData> shareInfoData = null;
		
		QueryWrapper<SocialShare> shareWrapper = new QueryWrapper<>();
		
		if (followingIdList == null || followingIdList.size() <=0 ) {
			logger.error("There is no following user");
		}else {
			shareWrapper.in("USER_ID", followingIdList);
			shareWrapper.gt("CREATION_DATE_TIME", refreshTime);
			List<SocialShare> shareRefleshList = socialShareMapper.selectList(shareWrapper);
			
			if(shareRefleshList == null || shareRefleshList.size() <= 0 ) {
				data.setRecentShareCount(0);
			}else {
				data.setRecentShareCount(shareRefleshList.size());
				shareInfoData = toShareInfoData(shareList);
			}
		}
		data.setFollowingListData(shareInfoData);
		
		return data;
	}
	
	private List<ShareInfoData> toShareInfoData(List<SocialShare> shareList) {
		List<ShareInfoData> shareInfoData = new ArrayList<>();
		
		if (shareList == null || shareList.size() <=0 ) {
			logger.error("There is no share data for its following user");
			return shareInfoData;
		}
		
		shareList.stream().forEach(x -> {
			ShareResponseData shareResponseData = toShareResponseData(x);
			ShareInfoData infoData = new ShareInfoData();
			infoData.setShare(shareResponseData);
			String thumbnailUrl = "";
			//String fileType = x.getFileType();
			/*
			if(fileType != null && fileType.equals("video")) {
				QueryWrapper<Video> wrapper = new QueryWrapper<>();
				wrapper.eq("SHARE_ID", x.getEntityId());
				Video video = videoMapper.selectOne(wrapper);
				
				if (video == null) {
					logger.error("There is no such video in data");
					throw new RuntimeException();
				}else {
					thumbnailUrl = video.getThumbnailPath();
				}
				
			}else if(fileType != null && fileType.equals("photo")) {
				QueryWrapper<Photo> wrapper = new QueryWrapper<>();
				wrapper.eq("SHARE_ID", x.getEntityId());
				Photo photo = photoMapper.selectOne(wrapper);
				
				if (photo == null) {
					logger.error("There is no such photo in data");
					throw new RuntimeException();
				}else {
					thumbnailUrl = photo.getPath();
				}
			}
			*/
			infoData.setThumbnailUrl(thumbnailUrl);
			shareInfoData.add(infoData);
		});
		
		return shareInfoData;
	}

	private ShareResponseData toShareResponseData(SocialShare x) {
		if (x == null) {
			//throw new MvpMsShareException(MvpMsShareExceptionCode.ERROR_001_01);
			throw new RuntimeException();
		}
		
		ShareResponseData data = new ShareResponseData();
		data.setShareId(x.getEntityId());
		data.setUserId(x.getUserId());
		data.setPostStatus(x.getPostStatus());
		data.setMessage(x.getMessage());
		data.setFileType(x.getFileType());
		data.setLocation(x.getLocation());
		//data.setToUserId(Long.valueOf(x.getToUserId()));
		data.setLikeCount(x.getLikeCount());
		data.setCommentCount(x.getCommentCount());
		data.setShareCount(x.getShareCount());
		data.setViewAmount(x.getViewAmount());
		data.setCreateTime(x.getCreationDateTime());
		data.setLastUpdateTime(x.getLastUpdateDateTime());
		return data;
	}
	
	private UserDetailResponseData toUserInfoResponseData(UserInfo x) {
		
		if (x == null) {
			logger.error("No Such Data In DataList");
			throw new RuntimeException();
		}
		
		UserDetailResponseData data = new UserDetailResponseData();
		data.setUserId(x.getEntityId());
		data.setUserRole(x.getUserRole());
		data.setMobilePhone(x.getMobilePhone());
		data.setAreaCode(x.getAreaCode());
		data.setUserName(x.getUserName());
		data.setNickName(x.getNickName());
		data.setEmail(x.getEmail());
		data.setIntroduction(x.getIntroduction());
		data.setGender(x.getGender());
		data.setDateOfBirth(x.getDateOfBirth());
		data.setLocation(x.getLocation());
		data.setFollowerCount(Long.valueOf(x.getFollowerCount()));
		data.setFanCount(Long.valueOf(x.getFanCount()));
		data.setUserImgPath(x.getUserImgPath());
		data.setLoginId(x.getLoginId());
		data.setPassword(x.getPassword());
		data.setCreationDateTime(x.getCreationDateTime());
		data.setLastUpdateDateTime(x.getLastUpdateDateTime());
		return data;
	}

	@Override
	public void insertData() {
		UserInfo entity = new UserInfo();
		entity.setUserRole("1");
		entity.setMobilePhone("123456");
		entity.setAreaCode("111");
		entity.setUserName("testUser");
		entity.setNickName("testUser");
		entity.setEmail("user@163.com");
		entity.setIntroduction("i am hen shuai");
		entity.setGender("0");
		entity.setDateOfBirth(LocalDateTime.now());
		entity.setLocation("location");
		entity.setFollowerCount(1);
		entity.setFanCount(1);
		entity.setUserImgPath("/usr/img.png");
		entity.setLoginId("123");
		entity.setPassword("password");
		entity.setCreationDateTime(LocalDateTime.now());
		entity.setLastUpdateDateTime(LocalDateTime.now());
		userInfoMapper.insert(entity);
		
		UserInfo anotherEntity = new UserInfo();
		anotherEntity.setUserRole("2");
		anotherEntity.setMobilePhone("2345678");
		anotherEntity.setAreaCode("111");
		anotherEntity.setUserName("testNewUser");
		anotherEntity.setNickName("testNewUser");
		anotherEntity.setEmail("newUser@163.com");
		anotherEntity.setIntroduction("i am fei chang shuai");
		anotherEntity.setGender("0");
		anotherEntity.setDateOfBirth(LocalDateTime.now());
		anotherEntity.setLocation("location");
		anotherEntity.setFollowerCount(1);
		anotherEntity.setFanCount(1);
		anotherEntity.setUserImgPath("/usr/img.png");
		anotherEntity.setLoginId("123");
		anotherEntity.setPassword("password");
		anotherEntity.setCreationDateTime(LocalDateTime.now());
		anotherEntity.setLastUpdateDateTime(LocalDateTime.now());
		userInfoMapper.insert(anotherEntity);
		
		SocialShare share = new SocialShare();
		share.setUserId(1);
		share.setPostStatus("Y");
		share.setMessage("this is a message");
		share.setFileType("photo");
		share.setLocation("shen zhen");
		share.setToUserId(null);
		share.setCreationDateTime(LocalDateTime.now());
		share.setLastUpdateDateTime(LocalDateTime.now());
		socialShareMapper.insert(share);
		
		SocialShare anotherShare = new SocialShare();
		anotherShare.setUserId(2);
		anotherShare.setPostStatus("Y");
		anotherShare.setMessage("this is a new message");
		anotherShare.setFileType("video");
		anotherShare.setLocation("shen zhen");
		anotherShare.setToUserId(null);
		anotherShare.setCreationDateTime(LocalDateTime.now());
		anotherShare.setLastUpdateDateTime(LocalDateTime.now());
		socialShareMapper.insert(anotherShare);
	}

	@Override
	public DiscoverResponseData getDiscover(@NotNull @Valid Integer shareId,@NotNull @Valid Integer fromUserId) {
		DiscoverResponseData data = new DiscoverResponseData();
		SocialShare socialShare = socialShareMapper.selectById(shareId);
		
		if (socialShare == null) {
			logger.info("There is no such data in share data");
			throw new RuntimeException();
		}
		
		data.setShareCount(socialShare.getShareCount());
		data.setLikeCount(socialShare.getLikeCount());
		data.setCommentCount(socialShare.getCommentCount());
		
		QueryWrapper<SocialLike> wrapper = new QueryWrapper<>();
		wrapper.eq("SHARE_ID", shareId);
		wrapper.eq("FROM_USER_ID", fromUserId);
		List<SocialLike> socialLike = socialLikeMapper.selectList(wrapper);
		
		if(socialLike != null && socialLike.size() > 0) {
			data.setIsLike(true);
		}else {
			data.setIsLike(false);
		}
		
		return data;
	}

	@Override
	public CommentListResponseData getCommentList(@NotNull @Valid Integer shareId) {
		CommentListResponseData data = new CommentListResponseData();
		QueryWrapper<Comment> wrapper = new QueryWrapper<>();
		wrapper.eq("SHARE_ID", shareId);
		List<Comment> commentList = commentMapper.selectList(wrapper);
		
		if(commentList == null || commentList.size() <= 0 ) {
			logger.info("There is no comment for share");
			return data;
		}
		data.setCommentCount(commentList.size());
		data.setShareId(shareId);
		List<CommentResponseData> commentResponseDataList = new ArrayList<>();
		commentList.stream().forEach(x -> {
			commentResponseDataList.add(toCommentResponseData(x));
		});
		data.setCommentList(commentResponseDataList);
		return data;
	}

	private CommentResponseData toCommentResponseData(Comment x) {
		CommentResponseData data = new CommentResponseData();
		data.setEntityId(x.getEntityId());
		data.setComment(x.getMessage());
		data.setCreateTime(x.getCreationDateTime());
		data.setFromUserId(x.getFromUserId());
		
		UserInfo user = userInfoMapper.selectById(x.getFromUserId());
		
		data.setFromUserImgPath(user.getUserImgPath());
		data.setFromUserName(user.getUserName());
		
		List<ReplyResponseData> list = new ArrayList<>();
		QueryWrapper<Reply> wrapper = new QueryWrapper<>();
		wrapper.eq("COMMENT_ID", x.getEntityId());
		List<Reply> childReply = replyMapper.selectList(wrapper);
		childReply.stream().forEach(c -> {
			list.add(toReplyResponseData(c));
		});
		data.setReplyList(list);
		return data;
	}

	private ReplyResponseData toReplyResponseData(Reply c) {
		ReplyResponseData data = new ReplyResponseData();
		data.setEntityId(c.getEntityId());
		data.setCommentId(c.getCommentId());
		data.setFromUserId(c.getFromUserId());
		
		UserInfo user = userInfoMapper.selectById(c.getFromUserId());
		data.setFromUserImgPath(user.getUserImgPath());
		data.setFromUserName(user.getUserName());
		
		data.setReply(c.getReply());
		data.setToUserId(c.getToUserId());
		data.setCreateTime(c.getCreationDateTime());
		
		return data;
	}

	@Override
	public CommentResponseData addComment(@Valid CommentRequest commentRequest) {
		CommentResponseData data = new CommentResponseData();
		Comment comment = new Comment();
		Reply reply = new Reply();
		SocialShare share = socialShareMapper.selectById(commentRequest.getShareId());
		
		if(share == null) {
			logger.info("There is no such share in shareList");
			throw new RuntimeException();
		}
		
		if (commentRequest.getToUserId().equals(share.getUserId())) {
			comment.setShareId(commentRequest.getShareId());
			comment.setCreationDateTime(LocalDateTime.now());
			comment.setFromUserId(commentRequest.getFromUserId());
			comment.setMessage(commentRequest.getComment());
			commentMapper.insert(comment);
		}else {
			reply.setCommentId(commentRequest.getEntityId());
			reply.setCreationDateTime(LocalDateTime.now());
			reply.setFromUserId(commentRequest.getFromUserId());
			reply.setReply(commentRequest.getComment());
			reply.setToUserId(commentRequest.getToUserId());
			replyMapper.insert(reply);
		}
		Integer commentCount = share.getCommentCount();
		share.setCommentCount(commentCount + 1);
		socialShareMapper.updateById(share);
		
		data.setEntityId(commentRequest.getEntityId());
		data.setFromUserId(commentRequest.getFromUserId());
		data.setComment(commentRequest.getComment());
		data.setCreateTime(LocalDateTime.now());
		
		UserInfo user = userInfoMapper.selectById(commentRequest.getFromUserId());
		data.setFromUserImgPath(user.getUserImgPath());
		data.setFromUserName(user.getUserName());
		
		//data.setReplyList(null);
		return data;
	}

	@Override
	public void deleteCommentList(@NotNull @Valid Integer shareId, @NotNull @Valid Integer entityId, @NotNull @Valid String flag) {
		
		SocialShare share = socialShareMapper.selectById(shareId);

		if(flag.equals("COMMENT")) {
			commentMapper.deleteById(entityId);
			UpdateWrapper<Reply> wrapper = new UpdateWrapper<>();
			wrapper.eq("COMMENT_ID", entityId);
			replyMapper.delete(wrapper);
		}else if (flag.equals("REPLY")) {
			replyMapper.deleteById(entityId);
		}
		
		Integer commentCount = share.getCommentCount();
		if(commentCount <= 0 ) {
			logger.info("There is no comment for share");
			throw new RuntimeException();
		}
		commentCount = commentCount-1;
		share.setCommentCount(commentCount);
		socialShareMapper.updateById(share);
	}

	@Override
	public FriendListResponseData findFriend(@NotNull @Valid Integer userId, @NotNull @Valid String...phoneArray) {
		FriendListResponseData data = new FriendListResponseData();
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		List<String> searchPhoneList = new ArrayList<>();
		for (String phone : phoneArray) {
			searchPhoneList.add(phone);
		}
		wrapper.in("MOBILE_PHONE", searchPhoneList);
		List<UserInfo> userList = userInfoMapper.selectList(wrapper);
		List<FriendResponseData> fansList = new ArrayList<>();
		userList.stream().forEach(x -> {
			fansList.add(toFriendResponseData(x));
		});
		
		data.setFansList(fansList);
		return data;
	}

	private FriendResponseData toFriendResponseData(UserInfo x) {
		FriendResponseData data = new FriendResponseData();
		data.setFanId(x.getEntityId());
		data.setFanImgPath(x.getUserImgPath());
		data.setFanName(x.getUserName());
		return data;
	}

	@Override
	public FriendByMobileContactsResponseData findFriendByMobileContacts(@NotNull @Valid List<String> userId, @NotNull @Valid String...phoneArray) {
		FriendByMobileContactsResponseData data = new FriendByMobileContactsResponseData();
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		List<String> phoneList = new ArrayList<>();
		for (String phone : phoneArray) {
			phoneList.add(phone);
		}
		wrapper.in("MOBILE_PHONE", phoneList);
		List<UserInfo> userList = userInfoMapper.selectList(wrapper);
		for (UserInfo userInfo : userList) {
			phoneList.remove(userInfo.getMobilePhone());
		}
		data.setMobileContacts(phoneList);
		return data;
	}

	@Override
	public FriendListResponseData getFriendList(@NotNull @Valid Integer userId) {
		FriendListResponseData data = new FriendListResponseData();
		QueryWrapper<SocialFan> fanWrapper = new QueryWrapper<>();
		fanWrapper.eq("USER_ID", userId);
		List<SocialFan> fanList = socialFanMapper.selectList(fanWrapper);
		List<Integer> fanIdList = new ArrayList<>();
		fanList.stream().forEach(x -> {
			fanIdList.add(x.getFanId());
		});
		fanWrapper.clear();
		fanWrapper.in("USER_ID", fanIdList);
		List<SocialFan> fans = socialFanMapper.selectList(fanWrapper);
		List<UserInfo> userInfos = new ArrayList<>();
		
		fans.stream().forEach(x ->{
			if (x.getFanId().equals(userId)) {
				userInfos.add(userInfoMapper.selectById(userId));
			}
		});
		
		List<FriendResponseData> responseDatas = new ArrayList<>();
		userInfos.stream().forEach(x -> {
			responseDatas.add(toFriendResponseData(x));
		});
		data.setFansList(responseDatas);
		return data;
	}

	@Override
	public FriendResponseData addFriend(@Valid AddFriendRequest addFriendRequest) {
		FriendResponseData data = new FriendResponseData();
		UserInfo user = userInfoMapper.selectById(addFriendRequest.getUserId());
		UserInfo fan = userInfoMapper.selectById(addFriendRequest.getFanId());
		SocialFan socialFan = new SocialFan();
		socialFan.setUserId(addFriendRequest.getUserId());
		socialFan.setFanId(addFriendRequest.getFanId());
		socialFan.setCreationDateTime(LocalDateTime.now());
		socialFan.setIsBlack("N");
		socialFanMapper.insert(socialFan);
		socialFan.setUserId(addFriendRequest.getFanId());
		socialFan.setFanId(addFriendRequest.getUserId());
		socialFanMapper.insert(socialFan);
		data.setFanId(fan.getEntityId());
		data.setFanImgPath(fan.getUserImgPath());
		data.setFanName(fan.getUserName());
		data.setRegisterFlag(true);
		data.setUserId(user.getEntityId());
		data.setUserImgPath(user.getUserImgPath());
		data.setUserName(user.getUserName());
		data.setCreateTime(LocalDateTime.now());
		return data;
	}

	@Override
	public InviteFriendByMobileContactsResponseData inviteFriendByMobileContacts(@NotNull @Valid String...phoneArray) {
		//TODO through the mobile number to notice friends
		return null;
	}

}
