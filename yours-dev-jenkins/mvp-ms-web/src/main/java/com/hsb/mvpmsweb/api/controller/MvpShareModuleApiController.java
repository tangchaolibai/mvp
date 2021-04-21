package com.hsb.mvpmsweb.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hsb.mvpmsweb.api.MvpShareModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpShareException;
import com.hsb.mvpmsweb.model.payload.AllFanListResponseData;
import com.hsb.mvpmsweb.model.payload.AllFollowingUserListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentResponseData;
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
import com.hsb.mvpmsweb.model.payload.ResponseResult;
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
import com.hsb.mvpmsweb.model.payload.request.ShareListRequest;
import com.hsb.mvpmsweb.model.payload.request.ShareRelationCountRequest;
import com.hsb.mvpmsweb.model.payload.request.SocialLikeRequest;
import com.hsb.mvpmsweb.model.payload.request.SocialMsgRequest;
import com.hsb.mvpmsweb.model.payload.request.SocialShareRequest;
import com.hsb.mvpmsweb.model.payload.response.AllFanListResponse;
import com.hsb.mvpmsweb.model.payload.response.AllFollowingUserListResponse;
import com.hsb.mvpmsweb.model.payload.response.CommentListResponse;
import com.hsb.mvpmsweb.model.payload.response.CommentResponse;
import com.hsb.mvpmsweb.model.payload.response.DeleteCommentResponse;
import com.hsb.mvpmsweb.model.payload.response.DraftListResponse;
import com.hsb.mvpmsweb.model.payload.response.FilesPathListResponse;
import com.hsb.mvpmsweb.model.payload.response.FollowingListResponse;
import com.hsb.mvpmsweb.model.payload.response.FollowingUserByUserNameResponse;
import com.hsb.mvpmsweb.model.payload.response.FriendByMobileContactsResponse;
import com.hsb.mvpmsweb.model.payload.response.FriendListResponse;
import com.hsb.mvpmsweb.model.payload.response.FriendResponse;
import com.hsb.mvpmsweb.model.payload.response.GiveFollowResponse;
import com.hsb.mvpmsweb.model.payload.response.InviteFriendByMobileContactsResponse;
import com.hsb.mvpmsweb.model.payload.response.IsFriendResponse;
import com.hsb.mvpmsweb.model.payload.response.PostContentResponse;
import com.hsb.mvpmsweb.model.payload.response.ProfileResponse;
import com.hsb.mvpmsweb.model.payload.response.RecentlyUpdatedUserResponse;
import com.hsb.mvpmsweb.model.payload.response.ReplyListResponse;
import com.hsb.mvpmsweb.model.payload.response.ReplyResponse;
import com.hsb.mvpmsweb.model.payload.response.ShareListResponse;
import com.hsb.mvpmsweb.model.payload.response.ShareRelationCountResponse;
import com.hsb.mvpmsweb.model.payload.response.ShareResponse;
import com.hsb.mvpmsweb.model.payload.response.SuccessResponse;
import com.hsb.mvpmsweb.service.ShareService;

@RestController
public class MvpShareModuleApiController implements MvpShareModuleApi {

    @Autowired
    private ShareService shareService;
    
//    @Autowired
//	private ProductService productService;
    
    @Override
   	public ResponseEntity<FilesPathListResponse> uploadFiles(@Valid Integer userId,@RequestParam(required=false,value="files")@Valid MultipartFile[] multipartFiles) throws MvpShareException {
       	FilesPathListResponseData  filesPathListResponseData = shareService.uploadFiles(userId,multipartFiles);
       	FilesPathListResponse response = new FilesPathListResponse();
   		response.setData(filesPathListResponseData);
   		response.setResult(ResponseResult.DefaultSuccessResponse);
   		return ResponseEntity.ok(response);
   	}
    
	@Override
	public ResponseEntity<GiveFollowResponse> giveFollow(@Valid GiveFollowRequest giveFollowRequest) throws MvpShareException {
		shareService.giveFollow(giveFollowRequest);
		GiveFollowResponse response = new GiveFollowResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<DraftListResponse> getDraftContent(@Valid Integer userId) {
		DraftListResponseData data = shareService.getDraftContent(userId);
		DraftListResponse response = new DraftListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SuccessResponse> shareContent(@Valid SocialMsgRequest socialMsgRequest) throws MvpShareException{
		shareService.shareContent(socialMsgRequest);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SuccessResponse> deleteContent(@Valid Integer shareId) {
		shareService.deleteContent(shareId);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<PostContentResponse> postContent(@Valid SocialShareRequest socialShareRequest) throws MvpShareException {
		PostContentResponse response = new PostContentResponse();
		PostContentResponseData data = shareService.postContent(socialShareRequest);
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

//	@Override
//	public ResponseEntity<BaseResponse> postContentWithProduct(@Valid ShareWithProductRequest shareWithProductRequest) throws MvpTicketException {
//		PutOnSaleResponseData data = productService.putOnSale(shareWithProductRequest.getPutOnSaleRequest());
//		SocialShareRequest socialShareRequest = shareWithProductRequest.getSocialShareRequest();
//		socialShareRequest.setProductId(data.getEntityId());
//		shareService.postContent(socialShareRequest);
//		BaseResponse response = new BaseResponse(ResponseResult.DefaultSuccessResponse);
//		return ResponseEntity.ok(response);
//	}

	@Override
	public ResponseEntity<SuccessResponse> giveLike(@Valid SocialLikeRequest socialLike)  throws MvpShareException{
		shareService.giveLike(socialLike);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FollowingListResponse> getFollowingListByCreateTime(
			@Valid FollowingListByCreateTimeRequest followingListByCreateTimeRequest,Pageable pageable) throws MvpShareException {
		FollowingListResponseData data = shareService.getFollowingListByCreateTime(followingListByCreateTimeRequest,pageable);
		FollowingListResponse response = new FollowingListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ShareListResponse> getShareListByCreateTime(ShareListRequest shareListRequest,Pageable pageable) throws MvpShareException {
		ShareListResponseData data = shareService.getShareListByCreateTime(shareListRequest.getUserId(),pageable);
		ShareListResponse response = new ShareListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<ShareResponse> getShareById(String token, Integer shareId) throws MvpShareException {
		ShareInfoData data = shareService.getShareById(token, shareId);
		ShareResponse response = new ShareResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<ShareRelationCountResponse> getShareRelationCount(
			@Valid ShareRelationCountRequest shareRelationCountRequest) {
		ShareRelationCountResponseData data = shareService.getShareRelationCount(shareRelationCountRequest);
		ShareRelationCountResponse response = new ShareRelationCountResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<FollowingUserByUserNameResponse> findFollowingBySearchContent(
			@Valid FollowingUserByUserNameRequest followingUserByUserNameRequest) throws MvpShareException {
		FollowingUserByUserNameResponseData data = shareService.findFollowingBySearchContent(followingUserByUserNameRequest);
		FollowingUserByUserNameResponse response = new FollowingUserByUserNameResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<AllFanListResponse> getAllFanList(@Valid AllFanListRequest allFanListRequest) throws MvpShareException {
		AllFanListResponseData data = shareService.getAllFanList(allFanListRequest);
		AllFanListResponse response = new AllFanListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<AllFollowingUserListResponse> getAllFollowingUserList(
			@Valid FollowingUserListRequest followingUserListRequest) throws MvpShareException {
		AllFollowingUserListResponseData data = shareService.getAllFollowingUserList(followingUserListRequest);
		AllFollowingUserListResponse response = new AllFollowingUserListResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<RecentlyUpdatedUserResponse> getRecentlyUpdatedUser(
			@Valid RecentlyUpdatedUserRequest recentlyUpdatedUserRequest) throws MvpShareException {
		RecentlyUpdatedUserResponseData data = shareService.getRecentlyUpdatedUser(recentlyUpdatedUserRequest);
		RecentlyUpdatedUserResponse response = new RecentlyUpdatedUserResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Void> insertData() {
		shareService.insertData();
		return null;
	}

	@Override
	public ResponseEntity<SuccessResponse> addViewAmount(Integer shareId) throws MvpShareException {
		shareService.addViewAmount(shareId);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<CommentListResponse> getCommentList(@NotNull @Valid Integer shareId) throws MvpShareException {
		CommentListResponseData data = shareService.getCommentList(shareId);
		CommentListResponse response = new CommentListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<ReplyListResponse> getReplyList(@NotNull @Valid Integer commentId)
			throws MvpShareException {
		List<ReplyResponseData> data = shareService.getReplyList(commentId);
		ReplyListResponse response = new ReplyListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<CommentResponse> addComment(@Valid CommentRequest commentRequest) throws MvpShareException {
		CommentResponseData data = shareService.addComment(commentRequest);
		CommentResponse response = new CommentResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<ReplyResponse> addReply(@Valid ReplyRequest replyRequest) throws MvpShareException {
		ReplyResponseData data = shareService.addReply(replyRequest);
		ReplyResponse response = new ReplyResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<DeleteCommentResponse> deleteCommentList(@NotNull @Valid Integer shareId,
			@NotNull @Valid Integer entityId,@NotNull @Valid String flag) throws MvpShareException {
		shareService.deleteCommentList(shareId,entityId,flag);
		DeleteCommentResponse response = new DeleteCommentResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<SuccessResponse> deleteFan(@NotNull @Valid Integer userId, @NotNull @Valid Integer fanId)
			throws MvpShareException {
		shareService.deleteFan(userId,fanId);
		SuccessResponse response = new SuccessResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendListResponse> findFriend(@Valid Integer userId,
			@NotNull @Valid String searchContent) throws MvpShareException {
		FriendListResponseData data = shareService.findFriend(userId,searchContent);
		FriendListResponse response = new FriendListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendByMobileContactsResponse> findFriendByMobileContacts(
			@NotNull @Valid FriendByMobileContactsRequest findFriendByMobileContactsRequest) {
		FriendByMobileContactsResponseData data = shareService.findFriendByMobileContacts(findFriendByMobileContactsRequest);
		FriendByMobileContactsResponse response = new FriendByMobileContactsResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FriendListResponse> getFriendList(@NotNull @Valid Integer userId,String searchContent) {
		FriendListResponseData data = shareService.getFriendList(userId,searchContent);
		FriendListResponse response = new FriendListResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<FriendResponse> addFriend(@Valid AddFriendRequest addFriendRequest) throws MvpShareException {
		FriendResponseData data = shareService.addFriend(addFriendRequest);
		FriendResponse response = new FriendResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<IsFriendResponse> isFriend(@NotNull @Valid Integer userId, @NotNull @Valid Integer fanId) {
		IsFriendResponse response = new IsFriendResponse();
		IsFriendResponseData data = shareService.isFriend(userId,fanId);;
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<InviteFriendByMobileContactsResponse> inviteFriendByMobileContacts(
			InviteFriendByMobileContactsRequest request,String token) throws MvpShareException {
		InviteFriendByMobileContactsResponseData data = shareService.inviteFriendByMobileContacts(request,token);
		InviteFriendByMobileContactsResponse response = new InviteFriendByMobileContactsResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ProfileResponse> getPostList(@Valid Integer currentUserId,@Valid Integer scanUserId) throws MvpShareException {
		ProfileResponseData data = shareService.getPostList(currentUserId,scanUserId);
		ProfileResponse response = new ProfileResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<ProfileResponse> getLikeList(@Valid Integer currentUserId,@Valid Integer scanUserId) throws MvpShareException {
		ProfileResponseData data = shareService.getLikeList(currentUserId,scanUserId);
		ProfileResponse response = new ProfileResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	

}
