package com.hsb.mvpmsweb.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.hsb.mvpmsweb.api.exception.MvpShareException;
import com.hsb.mvpmsweb.model.payload.AllFanListResponseData;
import com.hsb.mvpmsweb.model.payload.AllFollowingUserListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentResponseData;
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
import com.hsb.mvpmsweb.model.payload.PostContentResponseData;
import com.hsb.mvpmsweb.model.payload.ProfileResponseData;
import com.hsb.mvpmsweb.model.payload.RecentlyUpdatedUserResponseData;
import com.hsb.mvpmsweb.model.payload.ReplyResponseData;
import com.hsb.mvpmsweb.model.payload.ShareInfoData;
import com.hsb.mvpmsweb.model.payload.ShareListResponseData;
import com.hsb.mvpmsweb.model.payload.ShareRelationCountResponseData;
import com.hsb.mvpmsweb.model.payload.request.AddFriendRequest;
import com.hsb.mvpmsweb.model.payload.request.AllFanListRequest;
import com.hsb.mvpmsweb.model.payload.request.CommentRequest;
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

public interface ShareService {
	
	FilesPathListResponseData uploadFiles(Integer userId,MultipartFile[] multipartFiles) throws MvpShareException;
	
	DraftListResponseData  getDraftContent(Integer userId);	
	
	PostContentResponseData postContent(SocialShareRequest socialShareRequest) throws MvpShareException;
	
	void giveLike(SocialLikeRequest socialLikeRequest)  throws MvpShareException;
	
	void deleteContent(Integer shareId);
	
	void addViewAmount(Integer shareId) throws MvpShareException;
	
	void shareContent(SocialMsgRequest socialMsgRequest) throws MvpShareException;
	
	FollowingListResponseData getFollowingListByCreateTime(@Valid FollowingListByCreateTimeRequest followingListByCreateTimeRequest,Pageable pageable) throws MvpShareException;
	
	ShareListResponseData getShareListByCreateTime(Integer userId,Pageable pageable) throws MvpShareException;
	
	ShareInfoData getShareById(String token, Integer shareId) throws MvpShareException;
	
	ShareRelationCountResponseData getShareRelationCount(ShareRelationCountRequest shareRelationCountRequest);
	
	void giveFollow(@Valid GiveFollowRequest giveFollowRequest) throws MvpShareException;

	FollowingUserByUserNameResponseData findFollowingBySearchContent(@Valid FollowingUserByUserNameRequest followingUserByUserNameRequest) throws MvpShareException;

	AllFanListResponseData getAllFanList(@Valid AllFanListRequest allFanListRequest) throws MvpShareException;

	AllFollowingUserListResponseData getAllFollowingUserList(@Valid FollowingUserListRequest followingUserListRequest) throws MvpShareException;

	RecentlyUpdatedUserResponseData getRecentlyUpdatedUser(@Valid RecentlyUpdatedUserRequest recentlyUpdatedUserRequest) throws MvpShareException;
	
	void insertData();
	
	DiscoverResponseData getDiscover(@NotNull @Valid Integer shareId, @NotNull @Valid Integer fromUserId) throws MvpShareException;

	CommentListResponseData getCommentList(@NotNull @Valid Integer shareId) throws MvpShareException;
	
	CommentResponseData addComment(@Valid CommentRequest commentRequest) throws MvpShareException;
	
	ReplyResponseData addReply(@Valid ReplyRequest replyRequest) throws MvpShareException;

	void deleteCommentList(@NotNull @Valid Integer shareId, @NotNull @Valid Integer entityId, @NotNull @Valid String flag) throws MvpShareException;

	FriendListResponseData findFriend(@Valid Integer userId, @NotNull @Valid String searchContent) throws MvpShareException;

	FriendByMobileContactsResponseData findFriendByMobileContacts(@NotNull @Valid FriendByMobileContactsRequest findFriendByMobileContactsRequest);

	FriendListResponseData getFriendList(@NotNull @Valid Integer userId,String searchContent);

	FriendResponseData addFriend(@Valid AddFriendRequest addFriendRequest) throws MvpShareException;

	InviteFriendByMobileContactsResponseData inviteFriendByMobileContacts(InviteFriendByMobileContactsRequest request,String token) throws MvpShareException;

	ProfileResponseData getPostList(@Valid Integer currentUserId,@Valid Integer scanUserId) throws MvpShareException;

	ProfileResponseData getLikeList(@Valid Integer currentUserId,@Valid Integer scanUserId) throws MvpShareException;

	IsFriendResponseData isFriend(@NotNull @Valid Integer userId, @NotNull @Valid Integer fanId);

	void deleteFan(@NotNull @Valid Integer userId, @NotNull @Valid Integer fanId) throws MvpShareException;

	List<ReplyResponseData> getReplyList(@NotNull @Valid Integer commentId) throws MvpShareException;

}
	