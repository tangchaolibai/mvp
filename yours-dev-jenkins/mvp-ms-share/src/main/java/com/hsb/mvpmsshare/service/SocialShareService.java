package com.hsb.mvpmsshare.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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
import com.hsb.mvpmsshare.model.payload.RecentlyUpdatedUserResponseData;
import com.hsb.mvpmsshare.model.payload.ShareListResponseData;
import com.hsb.mvpmsshare.model.payload.ShareRelationCountResponseData;
import com.hsb.mvpmsshare.model.payload.ShareResponseData;
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

public interface SocialShareService {
	
	FilesPathListResponseData uploadFiles(Integer userId,MultipartFile[] multipartFiles);
	
	DraftListResponseData  getDraftContent(Integer userId);	
	
	void postContent(SocialShareRequest socialShareRequest);
	
	void likeContent(SocialLikeRequest socialLikeRequest);
	
	void deleteContent(Integer shareId);
	
	void cancelLike(Integer likeId);
	
	void shareContent(SocialMsgRequest socialMsgRequest);
	
	FollowingListResponseData getFollowingListByCreateTime(@Valid FollowingListByCreateTimeRequest followingListByCreateTimeRequest);
	
	ShareListResponseData getShareListByCreateTime();
	
	ShareResponseData getShareById(Integer shareId);
	
	ShareRelationCountResponseData getShareRelationCount(ShareRelationCountRequest shareRelationCountRequest);
	
	void cancelFan(@Valid CancelFanRequest cancelFanRequest);

	void cancelFollow(@Valid CancelFollowRequest cancelFollowRequest);

	FollowingUserByUserNameResponseData findFollowingByUserName(@Valid FollowingUserByUserNameRequest followingUserByUserNameRequest);

	void follow(@Valid FollowRequest followRequest);

	AllFanListResponseData getAllFanList(@Valid AllFanListRequest allFanListRequest);

	AllFollowingUserListResponseData getAllFollowingUserList(@Valid FollowingUserListRequest followingUserListRequest);

	RecentlyUpdatedUserResponseData getRecentlyUpdatedUser(@Valid RecentlyUpdatedUserRequest recentlyUpdatedUserRequest);
	
	void insertData();
	
	DiscoverResponseData getDiscover(@NotNull @Valid Integer shareId, @NotNull @Valid Integer fromUserId);

	CommentListResponseData getCommentList(@NotNull @Valid Integer shareId);
	
	CommentResponseData addComment(@Valid CommentRequest commentRequest);

	void deleteCommentList(@NotNull @Valid Integer shareId, @NotNull @Valid Integer entityId, @NotNull @Valid String flag);

	FriendListResponseData findFriend(@Valid Integer userId, @NotNull @Valid String...searchContent);

	FriendByMobileContactsResponseData findFriendByMobileContacts(@NotNull @Valid List<String> userId,@NotNull @Valid String...phoneArray);

	FriendListResponseData getFriendList(@NotNull @Valid Integer userId);

	FriendResponseData addFriend(@Valid AddFriendRequest addFriendRequest);

	InviteFriendByMobileContactsResponseData inviteFriendByMobileContacts(@NotNull @Valid String...userId);
	
}
	