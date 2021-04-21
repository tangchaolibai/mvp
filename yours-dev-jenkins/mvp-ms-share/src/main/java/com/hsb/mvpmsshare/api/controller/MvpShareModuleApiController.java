package com.hsb.mvpmsshare.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hsb.mvpmsshare.api.MvpShareModuleApi;
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
import com.hsb.mvpmsshare.model.payload.ResponseResult;
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
import com.hsb.mvpmsshare.model.payload.response.AllFanListResponse;
import com.hsb.mvpmsshare.model.payload.response.AllFollowingUserListResponse;
import com.hsb.mvpmsshare.model.payload.response.CancelFanResponse;
import com.hsb.mvpmsshare.model.payload.response.CancelFollowResponse;
import com.hsb.mvpmsshare.model.payload.response.CommentListResponse;
import com.hsb.mvpmsshare.model.payload.response.CommentResponse;
import com.hsb.mvpmsshare.model.payload.response.DeleteCommentResponse;
import com.hsb.mvpmsshare.model.payload.response.DiscoverResponse;
import com.hsb.mvpmsshare.model.payload.response.DraftListResponse;
import com.hsb.mvpmsshare.model.payload.response.FilesPathListResponse;
import com.hsb.mvpmsshare.model.payload.response.FollowResponse;
import com.hsb.mvpmsshare.model.payload.response.FollowingListResponse;
import com.hsb.mvpmsshare.model.payload.response.FollowingUserByUserNameResponse;
import com.hsb.mvpmsshare.model.payload.response.FriendByMobileContactsResponse;
import com.hsb.mvpmsshare.model.payload.response.FriendListResponse;
import com.hsb.mvpmsshare.model.payload.response.FriendResponse;
import com.hsb.mvpmsshare.model.payload.response.InviteFriendByMobileContactsResponse;
import com.hsb.mvpmsshare.model.payload.response.RecentlyUpdatedUserResponse;
import com.hsb.mvpmsshare.model.payload.response.ShareListResponse;
import com.hsb.mvpmsshare.model.payload.response.ShareRelationCountResponse;
import com.hsb.mvpmsshare.model.payload.response.ShareResponse;
import com.hsb.mvpmsshare.model.payload.response.SuccessResponse;
import com.hsb.mvpmsshare.service.SocialShareService;

@RestController
public class MvpShareModuleApiController implements MvpShareModuleApi {

    @Autowired
    private SocialShareService socialShareService;
    
    @Override
   	public ResponseEntity<FilesPathListResponse> uploadFiles(@Valid Integer userId,@RequestParam(required=false,value="files")@Valid MultipartFile[] multipartFiles) {
       	FilesPathListResponseData  filesPathListResponseData = socialShareService.uploadFiles(userId,multipartFiles);
       	FilesPathListResponse response = new FilesPathListResponse();
   		response.setData(filesPathListResponseData);
   		response.setResult(ResponseResult.DefaultSuccessResponse);
   		return ResponseEntity.ok(response);
   	}
    
	@Override
	public ResponseEntity<SuccessResponse> cancelLike(@Valid Integer likeId) {
		socialShareService.cancelLike(likeId);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<DraftListResponse> getDraftContent(@Valid Integer userId) {
		DraftListResponseData data = socialShareService.getDraftContent(userId);
		DraftListResponse response = new DraftListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SuccessResponse> shareContent(@Valid List<SocialMsgRequest> socialMsg) {
		if(!socialMsg.isEmpty()) {
			for(SocialMsgRequest socialMsgRequest:socialMsg)
			socialShareService.shareContent(socialMsgRequest);
		}
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SuccessResponse> deleteContent(@Valid Integer shareId) {
		socialShareService.deleteContent(shareId);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SuccessResponse> postContent(@Valid SocialShareRequest socialShare) {
		socialShareService.postContent(socialShare);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SuccessResponse> likeContent(@Valid List<SocialLikeRequest> socialLike) {
		if(!socialLike.isEmpty()) {
			for(SocialLikeRequest socialLikeRequest:socialLike)
				socialShareService.likeContent(socialLikeRequest);
		}
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FollowingListResponse> getFollowingListByCreateTime(
			@Valid FollowingListByCreateTimeRequest followingListByCreateTimeRequest) {
		FollowingListResponseData data = socialShareService.getFollowingListByCreateTime(followingListByCreateTimeRequest);
		FollowingListResponse response = new FollowingListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ShareListResponse> getShareListByCreateTime() {
		ShareListResponseData data = socialShareService.getShareListByCreateTime();
		ShareListResponse response = new ShareListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<ShareResponse> getShareById(Integer shareId) {
		ShareResponseData data = socialShareService.getShareById(shareId);
		ShareResponse response = new ShareResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ShareRelationCountResponse> getShareRelationCount(
			@Valid ShareRelationCountRequest shareRelationCountRequest) {
		ShareRelationCountResponseData data = socialShareService.getShareRelationCount(shareRelationCountRequest);
		ShareRelationCountResponse response = new ShareRelationCountResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<CancelFanResponse> cancelFan(@Valid CancelFanRequest cancelFanRequest) {
		socialShareService.cancelFan(cancelFanRequest);
		CancelFanResponse response = new CancelFanResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CancelFollowResponse> cancelFollow(@Valid CancelFollowRequest cancelFollowRequest) {
		socialShareService.cancelFollow(cancelFollowRequest);
		CancelFollowResponse response = new CancelFollowResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FollowingUserByUserNameResponse> findFollowingByUserName(
			@Valid FollowingUserByUserNameRequest followingUserByUserNameRequest) {
		FollowingUserByUserNameResponseData data = socialShareService.findFollowingByUserName(followingUserByUserNameRequest);
		FollowingUserByUserNameResponse response = new FollowingUserByUserNameResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FollowResponse> follow(@Valid FollowRequest followRequest) {
		socialShareService.follow(followRequest);
		FollowResponse response = new FollowResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<AllFanListResponse> getAllFanList(@Valid AllFanListRequest allFanListRequest) {
		AllFanListResponseData data = socialShareService.getAllFanList(allFanListRequest);
		AllFanListResponse response = new AllFanListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<AllFollowingUserListResponse> getAllFollowingUserList(
			@Valid FollowingUserListRequest followingUserListRequest) {
		AllFollowingUserListResponseData data = socialShareService.getAllFollowingUserList(followingUserListRequest);
		AllFollowingUserListResponse response = new AllFollowingUserListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<RecentlyUpdatedUserResponse> getRecentlyUpdatedUser(
			@Valid RecentlyUpdatedUserRequest recentlyUpdatedUserRequest) {
		RecentlyUpdatedUserResponseData data = socialShareService.getRecentlyUpdatedUser(recentlyUpdatedUserRequest);
		RecentlyUpdatedUserResponse response = new RecentlyUpdatedUserResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Void> insertData() {
		socialShareService.insertData();
		return null;
	}

	@Override
	public ResponseEntity<DiscoverResponse> getDiscover(@NotNull @Valid Integer shareId,
			@NotNull @Valid Integer fromUserId) {
		DiscoverResponseData data = socialShareService.getDiscover(shareId,fromUserId);
		DiscoverResponse response = new DiscoverResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CommentListResponse> getCommentList(@NotNull @Valid Integer shareId) {
		CommentListResponseData data = socialShareService.getCommentList(shareId);
		CommentListResponse response = new CommentListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<CommentResponse> addComment(@Valid CommentRequest commentRequest) {
		CommentResponseData data = socialShareService.addComment(commentRequest);
		CommentResponse response = new CommentResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<DeleteCommentResponse> deleteCommentList(@NotNull @Valid Integer shareId,
			@NotNull @Valid Integer entityId,@NotNull @Valid String flag) {
		socialShareService.deleteCommentList(shareId,entityId,flag);
		DeleteCommentResponse response = new DeleteCommentResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendListResponse> findFriend(@Valid Integer userId,
			@NotNull @Valid String...searchContent) {
		FriendListResponseData data = socialShareService.findFriend(userId,searchContent);
		FriendListResponse response = new FriendListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendByMobileContactsResponse> findFriendByMobileContacts(
			@NotNull @Valid List<String> userId,@NotNull @Valid String...phoneArray) {
		FriendByMobileContactsResponseData data = socialShareService.findFriendByMobileContacts(userId,phoneArray);
		FriendByMobileContactsResponse response = new FriendByMobileContactsResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendListResponse> getFriendList(@NotNull @Valid Integer userId) {
		FriendListResponseData data = socialShareService.getFriendList(userId);
		FriendListResponse response = new FriendListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendResponse> addFriend(@Valid AddFriendRequest addFriendRequest) {
		FriendResponseData data = socialShareService.addFriend(addFriendRequest);
		FriendResponse response = new FriendResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<InviteFriendByMobileContactsResponse> inviteFriendByMobileContacts(
			@NotNull @Valid String...phoneArray) {
		InviteFriendByMobileContactsResponseData data = socialShareService.inviteFriendByMobileContacts(phoneArray);
		InviteFriendByMobileContactsResponse response = new InviteFriendByMobileContactsResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

}
