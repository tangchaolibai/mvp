package com.hsb.mvpmsweb.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import com.hsb.mvpmsweb.api.MvpShareModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpShareException;
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
import com.hsb.mvpmsweb.model.payload.response.PostContentResponse;
import com.hsb.mvpmsweb.model.payload.response.ProfileResponse;
import com.hsb.mvpmsweb.model.payload.response.RecentlyUpdatedUserResponse;
import com.hsb.mvpmsweb.model.payload.response.ReplyResponse;
import com.hsb.mvpmsweb.model.payload.response.ShareListResponse;
import com.hsb.mvpmsweb.model.payload.response.ShareRelationCountResponse;
import com.hsb.mvpmsweb.model.payload.response.SuccessResponse;
import com.hsb.mvpmsweb.service.ShareService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
public class MvpShareModuleApiControllerTest {
	
	@InjectMocks
	private MvpShareModuleApi api = new MvpShareModuleApiController();

	@Mock
	private ShareService shareService;
	
	@Test
	public void testUploadFiles() throws MvpShareException {
		@Valid
		Integer userId = null;
		@Valid
		MultipartFile[] multipartFiles = null;
		ResponseEntity<FilesPathListResponse> response = api.uploadFiles(userId, multipartFiles);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGiveFollow() throws MvpShareException {
		@Valid
		GiveFollowRequest giveFollowRequest = null;
		ResponseEntity<GiveFollowResponse> response = api.giveFollow(giveFollowRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetDraftContent() throws MvpShareException {
		@Valid
		Integer userId = null;
		ResponseEntity<DraftListResponse> response = api.getDraftContent(userId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testShareContent() throws MvpShareException {
		@Valid
		SocialMsgRequest socialMsg = null;
		ResponseEntity<SuccessResponse> response = api.shareContent(socialMsg);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testDeleteContent() throws MvpShareException {
		@Valid
		Integer shareId = null;
		ResponseEntity<SuccessResponse> response = api.deleteContent(shareId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testPostContent() throws MvpShareException {
		@Valid
		SocialShareRequest socialShare = null;
		ResponseEntity<PostContentResponse> response = api.postContent(socialShare);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGiveLike() throws MvpShareException {
		@Valid
		SocialLikeRequest socialLike = null;
		ResponseEntity<SuccessResponse> response = api.giveLike(socialLike);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetFollowingListByCreateTime() throws MvpShareException {
		Pageable pageable = null;
		@Valid
		FollowingListByCreateTimeRequest followingListByCreateTimeRequest = null;
		ResponseEntity<FollowingListResponse> response = api
				.getFollowingListByCreateTime(followingListByCreateTimeRequest, pageable);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetShareListByCreateTime() throws MvpShareException {
		Pageable pageable = null;
		@Valid
		ShareListRequest shareListRequest = new ShareListRequest();
		ResponseEntity<ShareListResponse> response = api.getShareListByCreateTime(shareListRequest, pageable);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetShareRelationCount() throws MvpShareException {
		@Valid
		ShareRelationCountRequest shareRelationCountRequest = null;
		ResponseEntity<ShareRelationCountResponse> response = api.getShareRelationCount(shareRelationCountRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testFindFollowingByUserName() throws MvpShareException {
		@Valid
		FollowingUserByUserNameRequest followingUserByUserNameRequest = null;
		ResponseEntity<FollowingUserByUserNameResponse> response = api
				.findFollowingBySearchContent(followingUserByUserNameRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetAllFanList() throws MvpShareException {
		@Valid
		AllFanListRequest allFanListRequest = null;
		ResponseEntity<AllFanListResponse> response = api.getAllFanList(allFanListRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetAllFollowingUserList() throws MvpShareException {
		@Valid
		FollowingUserListRequest followingUserListRequest = null;
		ResponseEntity<AllFollowingUserListResponse> response = api.getAllFollowingUserList(followingUserListRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetRecentlyUpdatedUser() throws MvpShareException {
		@Valid
		RecentlyUpdatedUserRequest recentlyUpdatedUserRequest = null;
		ResponseEntity<RecentlyUpdatedUserResponse> response = api.getRecentlyUpdatedUser(recentlyUpdatedUserRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testInsertData() throws MvpShareException {
		ResponseEntity<Void> response = api.insertData();
		// Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals(null, response);
	}

	@Test
	public void testAddViewAmount() throws MvpShareException {
		@Valid
		Integer shareId = null;
		ResponseEntity<SuccessResponse> response = api.addViewAmount(shareId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetCommentList() throws MvpShareException {
		@Valid
		Integer shareId = null;
		ResponseEntity<CommentListResponse> response = api.getCommentList(shareId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testAddComment() throws MvpShareException {
		@Valid
		CommentRequest commentRequest = null;
		ResponseEntity<CommentResponse> response = api.addComment(commentRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testAddReply() throws MvpShareException {
		@Valid
		ReplyRequest replyRequest = null;
		ResponseEntity<ReplyResponse> response = api.addReply(replyRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testDeleteCommentList() throws MvpShareException {
		@NotNull
		@Valid
		Integer entityId = null;
		@NotNull
		@Valid
		Integer shareId = null;
		@NotNull
		@Valid
		String flag = null;
		ResponseEntity<DeleteCommentResponse> response = api.deleteCommentList(shareId, entityId, flag);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testFindFriend() throws MvpShareException {
		@NotNull
		@Valid
		String phoneArray = null;
		@Valid
		Integer userId = null;
		ResponseEntity<FriendListResponse> response = api.findFriend(userId, phoneArray);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testFindFriendByMobileContacts() throws MvpShareException {
		@NotNull
		@Valid
		@SuppressWarnings("unused")
		String phoneArray = null;
		ResponseEntity<FriendByMobileContactsResponse> response = api.findFriendByMobileContacts(new FriendByMobileContactsRequest());
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetFriendList() throws MvpShareException {
		@NotNull
		@Valid
		Integer userId = null;
		ResponseEntity<FriendListResponse> response = api.getFriendList(userId,null);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testAddFriend() throws MvpShareException {
		@Valid
		AddFriendRequest addFriendRequest = null;
		ResponseEntity<FriendResponse> response = api.addFriend(addFriendRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testInviteFriendByMobileContacts() throws MvpShareException {
		@NotNull
		@Valid
		InviteFriendByMobileContactsRequest request = new InviteFriendByMobileContactsRequest();
		String token = null;
		ResponseEntity<InviteFriendByMobileContactsResponse> response = api.inviteFriendByMobileContacts(request,token);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetPostList() throws MvpShareException {
		@Valid
		Integer scanUserId = null;
		@Valid
		Integer currentUserId = null;
		ResponseEntity<ProfileResponse> response = api.getPostList(currentUserId, scanUserId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testGetLikeList() throws MvpShareException {
		@Valid
		Integer currentUserId = null;
		@Valid
		Integer scanUserId = null;
		ResponseEntity<ProfileResponse> response = api.getLikeList(currentUserId, scanUserId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}
	
}
