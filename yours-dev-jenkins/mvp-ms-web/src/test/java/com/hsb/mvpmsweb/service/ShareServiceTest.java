package com.hsb.mvpmsweb.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsweb.api.exception.MvpShareException;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.mapper.CommentMapper;
import com.hsb.mvpmsweb.mapper.PhotoMapper;
import com.hsb.mvpmsweb.mapper.ReplyMapper;
import com.hsb.mvpmsweb.mapper.FanMapper;
import com.hsb.mvpmsweb.mapper.LikeMapper;
import com.hsb.mvpmsweb.mapper.MessageMapper;
import com.hsb.mvpmsweb.mapper.ShareMapper;
import com.hsb.mvpmsweb.mapper.SystemSettingMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.mapper.VideoMapper;
import com.hsb.mvpmsweb.model.domain.Comment;
import com.hsb.mvpmsweb.model.domain.Fan;
import com.hsb.mvpmsweb.model.domain.Like;
import com.hsb.mvpmsweb.model.domain.Share;
import com.hsb.mvpmsweb.model.domain.SystemSetting;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.payload.AllFanListResponseData;
import com.hsb.mvpmsweb.model.payload.AllFollowingUserListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentListResponseData;
import com.hsb.mvpmsweb.model.payload.CommentResponseData;
import com.hsb.mvpmsweb.model.payload.DraftListResponseData;
import com.hsb.mvpmsweb.model.payload.FollowingListResponseData;
import com.hsb.mvpmsweb.model.payload.FollowingUserByUserNameResponseData;
import com.hsb.mvpmsweb.model.payload.FriendByMobileContactsResponseData;
import com.hsb.mvpmsweb.model.payload.FriendListResponseData;
import com.hsb.mvpmsweb.model.payload.FriendResponseData;
import com.hsb.mvpmsweb.model.payload.InviteFriendByMobileContactsResponseData;
import com.hsb.mvpmsweb.model.payload.MobileContactsRequestData;
import com.hsb.mvpmsweb.model.payload.ProfileResponseData;
import com.hsb.mvpmsweb.model.payload.RecentlyUpdatedUserResponseData;
import com.hsb.mvpmsweb.model.payload.ReplyResponseData;
import com.hsb.mvpmsweb.model.payload.SendSMSResponseData;
import com.hsb.mvpmsweb.model.payload.ShareListResponseData;
import com.hsb.mvpmsweb.model.payload.ShareRelationCountResponseData;
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
import com.hsb.mvpmsweb.service.impl.ShareServiceImpl;
import com.hsb.mvpmsweb.util.JwtTokenUtil;
import com.hsb.mvpmsweb.util.RedisUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
public class ShareServiceTest {
	
	@InjectMocks
	private ShareService shareService = new ShareServiceImpl();
	
	@Mock
	private UserInfoService userInfoService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Value("${jwt.base64Secret}")
	private String base64Secret;

	@Value("${jwt.expiresSecond}")
	private int expiresSecond;
	
	@Mock
	private RedisUtils redisUtils;
	
	@Mock
	private FanMapper fanMapper;
	
	@Mock
	private ShareMapper shareMapper;
	
	@Mock
	private UserInfoMapper userInfoMapper;
	
	@Mock
	private CommentMapper commentMapper;
	
	@Mock
	private ReplyMapper replyMapper;
	
	@Mock
	private MessageMapper messageMapper;
	
	@Mock
	private LikeMapper likeMapper;

	@Mock
	private PhotoMapper photoMapper;
	
	@Mock
	private VideoMapper videoMapper;
	
	@Mock
	private SystemSettingMapper systemSettingMapper;
	
	/**
	 * only test the failure for non-share
	 * @throws MvpShareException
	 */
	@Test
	public void testAddViewAmount() throws MvpShareException {
		Integer shareId = 1;
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(null);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () -> {
			shareService.addViewAmount(shareId);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-001-01", exception.getCode()));
	}
	
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetFollowingListByCreateTime1() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		Page<Share> page = new Page<>();
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);
		FollowingListResponseData data = shareService.getFollowingListByCreateTime(new FollowingListByCreateTimeRequest(), new PageMock());
		
		Assertions.assertEquals(data.getRecentShareCount(), null);
		Assertions.assertEquals(data.getFollowingListData().size(), 0);
	}
	/**
	 * Success for no-follow-other
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetFollowingListByCreateTime2() throws MvpShareException {
		Page<Share> page = new Page<>();
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(null);
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);
		FollowingListResponseData data = shareService.getFollowingListByCreateTime(new FollowingListByCreateTimeRequest(), new PageMock());
		
		Assertions.assertEquals(data.getRecentShareCount(), null);
		Assertions.assertEquals(data.getFollowingListData().size(), 0);
	}
	/**
	 * Failure for non-video
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetFollowingListByCreateTime3() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		Page<Share> page = new Page<>();
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setFileType("video");
		shareList.add(share);
		page.setRecords(shareList);
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);

		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () ->{
			shareService.getFollowingListByCreateTime(new FollowingListByCreateTimeRequest(), new PageMock());
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-003-01", exception.getCode()));
		
	}
	/**
	 * Failure for non-photo
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetFollowingListByCreateTime4() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		Page<Share> page = new Page<>();
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setFileType("photo");
		shareList.add(share);
		page.setRecords(shareList);
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);

		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () ->{
			shareService.getFollowingListByCreateTime(new FollowingListByCreateTimeRequest(), new PageMock());
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-002-01", exception.getCode()));
		
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetShareListByCreateTime1() throws MvpShareException {
		Integer userId = 54; 
		Page<Share> page = new Page<>();
		Pageable pageable = null;
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);
		ShareListResponseData data = shareService.getShareListByCreateTime(userId, pageable);
		Assertions.assertEquals(data.getShareList().size(), 0);
	}
	/**
	 * Failure for non-video
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetShareListByCreateTime2() throws MvpShareException {
		Integer userId = 54; 
		Page<Share> page = new Page<>();
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setFileType("video");
		shareList.add(share);
		page.setRecords(shareList);
		Pageable pageable = new PageMock();
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.getShareListByCreateTime(userId, pageable);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-003-01", exception.getCode()));
	}
	/**
	 * Failure for non-photo
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetShareListByCreateTime3() throws MvpShareException {
		Integer userId = 54; 
		Page<Share> page = new Page<>();
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setFileType("photo");
		shareList.add(share);
		page.setRecords(shareList);
		Pageable pageable = new PageMock();
		when(shareMapper.selectPage(isA(Page.class), isA(Wrapper.class))).thenReturn(page);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.getShareListByCreateTime(userId, pageable);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-002-01", exception.getCode()));
	}
	
	@Test
	public void testGetShareRelationCount() {
		Share share1 = new Share();
		share1.setShareCount(1);
		share1.setCommentCount(2);
		share1.setLikeCount(3);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share1);
		ShareRelationCountRequest request = new ShareRelationCountRequest();
		request.setShareId(1);
		ShareRelationCountResponseData socialShare = shareService.getShareRelationCount(request);
		Assertions.assertEquals(socialShare.getCommentCount(), 2);
		Assertions.assertEquals(socialShare.getLikeCount(), 3);
		Assertions.assertEquals(socialShare.getShareCount(), 1);
		
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testFindFollowingByUserName1() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		List<UserInfo> user = new ArrayList<>();
		user.add(new UserInfo().userName("aaa").entityId(1));
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(user);
		FollowingUserByUserNameResponseData data = shareService.findFollowingBySearchContent(new FollowingUserByUserNameRequest());
		Assertions.assertEquals(data.getUserList().get(0).getUserName(), "aaa");
	}
	/**
	 * Failure for non-user
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testFindFollowingByUserName2() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		List<UserInfo> user = new ArrayList<>();
		user.add(new UserInfo().userName("aaa").entityId(1));
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(user);
		FollowingUserByUserNameResponseData data = shareService.findFollowingBySearchContent(new FollowingUserByUserNameRequest());
		Assertions.assertEquals(data.getUserList().get(0).getUserName(), "aaa");
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllFanList1() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		List<UserInfo> user = new ArrayList<>();
		user.add(new UserInfo().userName("aaa").entityId(1));
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(user);
		AllFanListResponseData data = shareService.getAllFanList(new AllFanListRequest());
		Assertions.assertEquals(data.getUserList().get(0).getUserName(), "aaa");
	}
	/**
	 * Failure for non-user
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllFanList2() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		List<UserInfo> user = new ArrayList<>();
		user.add(null);
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(user);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () -> {
			shareService.getAllFanList(new AllFanListRequest());
		});
		assertAll(() -> assertEquals("ERR-02-004-01", exception.getCode()));
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllFollowingUserList1() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		List<UserInfo> user = new ArrayList<>();
		user.add(new UserInfo().userName("aaa").entityId(1));
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(user);
		AllFollowingUserListResponseData data = shareService.getAllFollowingUserList(new FollowingUserListRequest());
		Assertions.assertEquals(data.getUserList().get(0).getUserName(), "aaa");
	}
	/**
	 * Failure for non-user
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllFollowingUserList2() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		follow.add(new Fan());
		List<UserInfo> user = new ArrayList<>();
		user.add(null);
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(user);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () -> {
			shareService.getAllFollowingUserList(new FollowingUserListRequest());
		});
		assertAll(() -> assertEquals("ERR-02-004-01", exception.getCode()));
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetRecentlyUpdatedUser() throws MvpShareException {
		List<Fan> follow = new ArrayList<>();
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setUserId(1);
		shareList.add(share);
		follow.add(new Fan());
		UserInfo user = new UserInfo();
		user.userName("aaa").entityId(1);
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(shareMapper.selectList(isA(QueryWrapper.class))).thenReturn(shareList);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(user);
		RecentlyUpdatedUserResponseData data = shareService.getRecentlyUpdatedUser(new RecentlyUpdatedUserRequest());
		Assertions.assertEquals(data.getUserList().get(0).getUserName(), "aaa");
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCommentList1() throws MvpShareException {
		Integer shareId = 100;
		List<Comment> commentList = new ArrayList<>();
		Comment comment = new Comment();
		comment.setFromUserId(1);
		commentList.add(comment);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(new UserInfo());
		when(commentMapper.selectList(isA(QueryWrapper.class))).thenReturn(commentList);
		CommentListResponseData data = shareService.getCommentList(shareId);
		Assertions.assertEquals(data.getCommentCount(),1);
		Assertions.assertEquals(data.getCommentList().size(), 1);
		Assertions.assertEquals(data.getShareId(), 100);
	}
	/**
	 * Failure for non-user
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetCommentList2() throws MvpShareException {
		Integer shareId = 100;
		List<Comment> commentList = new ArrayList<>();
		Comment comment = new Comment();
		comment.setFromUserId(1);
		commentList.add(comment);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(null);
		when(commentMapper.selectList(isA(QueryWrapper.class))).thenReturn(commentList);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () -> {
			shareService.getCommentList(shareId);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-004-01", exception.getCode()));
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@Test
	public void testAddComment1() throws MvpShareException {
		Share share = new Share();
		share.setCommentCount(2);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(new UserInfo());
		CommentRequest request = new CommentRequest();
		request.setShareId(1);
		request.setFromUserId(1);
		CommentResponseData data = shareService.addComment(request);
		Assertions.assertEquals(data.getReplyList(), null);
		Assertions.assertEquals(data.getFromUserId(), 1);
	}
	/**
	 * Failure for non-share
	 * @throws MvpShareException
	 */
	@Test
	public void testAddComment2() throws MvpShareException {
		CommentRequest request = new CommentRequest();
		request.setShareId(1);
		request.setFromUserId(1);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.addComment(request);
		});
		assertAll(() -> assertEquals("ERR-02-001-01", exception.getCode()));
	}
	/**
	 * Failure One for non-share
	 * @throws MvpShareException
	 */
	@Test
	public void testDeleteComment1() throws MvpShareException {
		Integer shareId = 1;
		Integer entityId = 1;
		String flag = "COMMENT";
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.deleteCommentList(shareId, entityId, flag);
		});
		assertAll(() -> assertEquals("ERR-02-001-01", exception.getCode()));
	}
	/**
	 * Failure Two for non-comment
	 * @throws MvpShareException
	 */
	@Test
	public void testDeleteComment2() throws MvpShareException {
		Integer shareId = 1;
		Integer entityId = 1;
		String flag = "COMMENT";
		Share share = new Share();
		share.setCommentCount(0);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.deleteCommentList(shareId, entityId, flag);
		});
		assertAll(() -> assertEquals("ERR-02-005-01", exception.getCode()));
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@Test
	public void testAddReply1() throws MvpShareException {
		Share share = new Share();
		share.setCommentCount(2);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(new UserInfo());
		ReplyRequest request = new ReplyRequest();
		request.setShareId(1);
		request.setFromUserId(1);
		ReplyResponseData data = shareService.addReply(request);
		Assertions.assertEquals(data.getFromUserId(), 1);
	}
	/**
	 * Failure for non-share
	 * @throws MvpShareException
	 */
	@Test
	public void testAddReply2() throws MvpShareException {
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(null);
		ReplyRequest request = new ReplyRequest();
		request.setShareId(1);
		request.setFromUserId(1);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.addReply(request);
		});
		assertAll(() -> assertEquals("ERR-02-001-01", exception.getCode()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindFriend() throws MvpShareException {
		Integer userId = null;
		String searchContent = "a";
		List<UserInfo> userList = new ArrayList<>();
		UserInfo user = new UserInfo();
		user.entityId(10);
		user.mobilePhone("123");
		user.nickName("aaa");
		userList.add(user);
		List<Fan> fanList = new ArrayList<>();
		Fan socialFan = new Fan();
		socialFan.setUserId(1);
		socialFan.setFanId(1);
		fanList.add(socialFan);
		SystemSetting systemSetting = new SystemSetting();
		systemSetting.searchAllow("Y");
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(userList);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(user);
		when(systemSettingMapper.selectOne(isA(QueryWrapper.class))).thenReturn(systemSetting);
		//when(userInfoMapper.selectList(isA(null))).thenReturn(userList);
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(fanList);
		FriendListResponseData data = shareService.findFriend(userId, searchContent);
		Assertions.assertEquals(data.getFansList().get(0).getFanId(), 10);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testFindFriendByMobileContacts() {
		FriendByMobileContactsRequest request = new FriendByMobileContactsRequest();
		List<MobileContactsRequestData> list = new ArrayList<>();
		String[] phoneArray = {"51234567","18111111111","13852225222"};
		for (int i = 0; i < phoneArray.length; i++) {
			MobileContactsRequestData datas = new MobileContactsRequestData();
			datas.setMobilePhone(phoneArray[i]);
			datas.setUserName("a");
			list.add(datas);
		}
		request.mobileContactsList(list);
		List<UserInfo> userList = new ArrayList<>();
		UserInfo user = new UserInfo();
		user.entityId(10);
		user.mobilePhone("18111111111");
		userList.add(user);
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(userList);
		FriendByMobileContactsResponseData data = shareService.findFriendByMobileContacts(request);
		Assertions.assertEquals(data.getMobileContacts().get(0).getMobilePhone(), "51234567");
		Assertions.assertEquals(data.getMobileContacts().get(1).getMobilePhone(), "13852225222");
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetFriendList() {
		Integer userId = 55;
		List<Fan> follow = new ArrayList<>();
		Fan fan = new Fan();
		//fan.setEntityId(55);
		fan.setFanId(55);
		fan.setUserId(1);
		follow.add(fan);
		UserInfo user = new UserInfo();
		user.entityId(1);
		user.nickName("aaa");
		when(fanMapper.selectList(isA(QueryWrapper.class))).thenReturn(follow);
		when(userInfoMapper.selectOne(isA(QueryWrapper.class))).thenReturn(user);
		FriendListResponseData data = shareService.getFriendList(userId,null);
		//Assertions.assertEquals(data.getFansList().get(0).getUserId(), 55);
		Assertions.assertEquals(data.getFansList().get(0).getFanId(), 1);
		Assertions.assertEquals(data.getFansList().get(0).getFanName(), "aaa");
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@Test
	public void testAddFriend1() throws MvpShareException {
		AddFriendRequest request = new AddFriendRequest();
		request.setUserId(1);
		request.setFanId(2);
		UserInfo user = new UserInfo();
		user.entityId(1);
		//when(shareMapper.selectById(isA(Integer.class))).thenReturn(new Share());
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(user);
		FriendResponseData data = shareService.addFriend(request);
		Assertions.assertEquals(data.getFanId(), 1);
		Assertions.assertEquals(data.getUserId(), 1);
		
	}
	/**
	 * Failure for non_user
	 * @throws MvpShareException
	 */
	@Test
	public void testAddFriend2() throws MvpShareException {
		AddFriendRequest request = new AddFriendRequest();
		request.setUserId(1);
		request.setFanId(2);
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(null);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class,() ->{
			shareService.addFriend(request);
		});
		assertAll(() -> assertEquals("ERR-02-004-01", exception.getCode()));
		
	}
	/**
	 * there is no logic for inviteFriendByMobileContacts,no test now
	 * @throws MvpShareException 
	 * @throws MvpWebException 
	 */
	@Test
	public void testInviteFriendByMobileContacts() throws MvpShareException, MvpUserException {
		InviteFriendByMobileContactsRequest request = new InviteFriendByMobileContactsRequest();
		request.setMobilePhone("111");
		String token = "111";
		SendSMSResponseData smsData = new SendSMSResponseData();
		smsData.SMS("success");
		when(userInfoService.sendSMS(isA(String.class), isA(String.class))).thenReturn(smsData);
		InviteFriendByMobileContactsResponseData data = shareService.inviteFriendByMobileContacts(request,token);
		Assertions.assertEquals(data.getMessage(), "success");
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@Test
	public void testGetPostList1() throws MvpShareException {
		Integer currentUserId = null;
		Integer scanUserId = null;
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(new Share());
		ProfileResponseData data = shareService.getPostList(currentUserId, scanUserId);
		Assertions.assertEquals(data.getFollowerCount(), 0);
		Assertions.assertEquals(data.getShareList().size(), 0);
	}
	/**
	 * Failure for non_video
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetPostList2() throws MvpShareException {
		Integer currentUserId = null;
		Integer scanUserId = null;
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setFileType("video");
		shareList.add(share);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(new Share());
		when(shareMapper.selectList(isA(Wrapper.class))).thenReturn(shareList);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () -> {
			shareService.getPostList(currentUserId, scanUserId);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-003-01", exception.getCode()));
	}
	/**
	 * Failure for non-photo
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetPostList3() throws MvpShareException {
		Integer currentUserId = null;
		Integer scanUserId = null;
		List<Share> shareList = new ArrayList<>();
		Share share = new Share();
		share.setFileType("photo");
		shareList.add(share);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(new Share());
		when(shareMapper.selectList(isA(Wrapper.class))).thenReturn(shareList);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () -> {
			shareService.getPostList(currentUserId, scanUserId);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-002-01", exception.getCode()));
	}
	/**
	 * Success
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetLikeList1() throws MvpShareException {
		Integer currentUserId = null;
		Integer scanUserId = null;
		List<Like> likeList = new ArrayList<>();
		Like like = new Like();
		like.setShareId(1);
		likeList.add(like);
		when(likeMapper.selectList(isA(QueryWrapper.class))).thenReturn(likeList);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(new Share());
		ProfileResponseData data = shareService.getLikeList(currentUserId, scanUserId);
		Assertions.assertEquals(data.getFollowerCount(), 0);
		Assertions.assertEquals(data.getShareList().size(), 1);
	}
	/**
	 * Failure for non-video
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetLikeList2() throws MvpShareException {
		Integer currentUserId = null;
		Integer scanUserId = null;
		List<Like> likeList = new ArrayList<>();
		Like like = new Like();
		like.setShareId(1);
		likeList.add(like);
		Share share = new Share();
		share.setFileType("video");
		when(likeMapper.selectList(isA(QueryWrapper.class))).thenReturn(likeList);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () ->{
			shareService.getLikeList(currentUserId, scanUserId);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-003-01", exception.getCode()));
	}
	/**
	 * Failure for non-photo
	 * @throws MvpShareException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetLikeList3() throws MvpShareException {
		Integer currentUserId = null;
		Integer scanUserId = null;
		List<Like> likeList = new ArrayList<>();
		Like like = new Like();
		like.setShareId(1);
		likeList.add(like);
		Share share = new Share();
		share.setFileType("photo");
		when(likeMapper.selectList(isA(QueryWrapper.class))).thenReturn(likeList);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () ->{
			shareService.getLikeList(currentUserId, scanUserId);
		});
		assertAll(() -> Assertions.assertEquals("ERR-02-002-01", exception.getCode()));
	}
	/**
	 * only test the failure for non-params
	 * @throws MvpShareException
	 */
	@Test
	public void testGiveFollow() throws MvpShareException {
		GiveFollowRequest request = new GiveFollowRequest();
		request.setFollowingId(1);
		request.setUserId(2);
		MvpShareException exception = Assertions.assertThrows(MvpShareException.class, () ->{
			shareService.giveFollow(request);
		});
		assertAll(() -> assertEquals("ERR-02-009-01", exception.getCode()));
		
	}
	
	/**
	 * only test the failure for non-files
	 * @throws MvpShareException,MvpUserException
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testUploadFiles1() throws MvpShareException, MvpUserException {
		File file1 = new File("C:\\Users\\Administrator\\Desktop\\imageName1.png");
		File file2 = new File("C:\\Users\\Administrator\\Desktop\\imageName2.png");
		File file3 = new File("C:\\Users\\Administrator\\Desktop\\imageName3.png");
		try {
			MockMultipartFile firstFile = new MockMultipartFile("file", "imageName1.png", MediaType.TEXT_PLAIN_VALUE,
					new FileInputStream(file1));
			MockMultipartFile secondFile = new MockMultipartFile("file", "imageName2.png", MediaType.TEXT_PLAIN_VALUE,
					new FileInputStream(file2));
			MockMultipartFile thridFile = new MockMultipartFile("file", "imageName2.png", MediaType.TEXT_PLAIN_VALUE,
					new FileInputStream(file3));
			String token = JwtTokenUtil.createJWT("65", base64Secret, expiresSecond);
			redisUtils.set("65", token);
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
			mockMvc.perform(MockMvcRequestBuilders.fileUpload("/share/uploadFiles").file(firstFile).file(secondFile)
					.file(thridFile).header("Authorization", "Bearer " + token).param("userId", "65"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * only test the failure for non-files
	 * @throws MvpShareException,MvpUserException
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testUploadFiles2() throws MvpShareException, MvpUserException {
		try {
			String token = JwtTokenUtil.createJWT("65", base64Secret, expiresSecond);
			redisUtils.set("65", token);
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
			String result = mockMvc
					.perform(MockMvcRequestBuilders.fileUpload("/share/uploadFiles")
							.header("Authorization", "Bearer " + token).param("userId", "65"))

					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			JSONObject parseObject = JSONArray.parseObject(result);
			JSONObject obj = parseObject.getJSONObject("result");
			Assertions.assertEquals("ERR-02-006-01", obj.get("code").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Success for have draft
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetDraftContent1() {
		Integer userId = 59;
		Share socialShare = new Share();
		socialShare.setPostStatus("N");
		when(shareMapper.selectOne(isA(Wrapper.class))).thenReturn(socialShare);
		DraftListResponseData data = shareService.getDraftContent(userId);
		Assertions.assertEquals("N",data.getPostStatus());
	}
	
	/**
	 * Success for non draft
	 */
	@Test
	public void testGetDraftContent2() {
		Integer userId = 108;
		DraftListResponseData data = shareService.getDraftContent(userId);
		Assertions.assertEquals(null,data.getPostStatus());
	}
	
	/**
	 * Success for post photos
	 * @throws MvpShareException 
	 */
	@Test
	public void testPostContent1() throws MvpShareException {
		SocialShareRequest socialShareRequest = new SocialShareRequest();
		socialShareRequest.setUserId(60);
		socialShareRequest.setPostStatus("Y");
		socialShareRequest.setMessage("add by user60");
		socialShareRequest.setFileType("photo");
		socialShareRequest.setLocation("shenzhen");
		socialShareRequest.setUserId(55);
		List<ContentRequestData> contentDatas = new ArrayList<ContentRequestData>();
		ContentRequestData contentData = new ContentRequestData();
		contentData.setContentName("520200812094740imageName2.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName2.png");
		contentDatas.add(contentData);
		contentData.setContentName("520200812094740imageName3.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName3.png");
		contentDatas.add(contentData);
		contentData.setContentName("520200812094740imageName4.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName4.png");
		contentDatas.add(contentData);
		socialShareRequest.setContentRequestData(contentDatas);
		shareService.postContent(socialShareRequest);
	}
	
	/**
	 * Success for post video
	 * @throws MvpShareException 
	 */
	@Test
	public void testPostContent2() throws MvpShareException {
		SocialShareRequest socialShareRequest = new SocialShareRequest();
		socialShareRequest.setUserId(60);
		socialShareRequest.setPostStatus("Y");
		socialShareRequest.setMessage("add by user60");
		socialShareRequest.setFileType("vidoe");
		socialShareRequest.setLocation("shenzhen");
		socialShareRequest.setUserId(55);
		List<ContentRequestData> contentDatas = new ArrayList<ContentRequestData>();
		ContentRequestData contentData = new ContentRequestData();
		contentData.setContentName("520200812094740video2.mp4");
		contentData.setContentPath("/useruploadFile/5/videos/520200812094740video2.mp4");
		contentData.setContentSeconds(19);
		contentData.setContentThumbnailUrl("/useruploadFile/5/photos/520200812094740video2.png");
		contentDatas.add(contentData);
		socialShareRequest.setContentRequestData(contentDatas);
		shareService.postContent(socialShareRequest);
	}
	
	/**
	 * Success for post photos as draft and dtaft exist
	 * @throws MvpShareException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testPostContent3() throws MvpShareException {
		SocialShareRequest socialShareRequest = new SocialShareRequest();
		socialShareRequest.setUserId(59);
		socialShareRequest.setPostStatus("N");
		socialShareRequest.setMessage("add by user60");
		socialShareRequest.setFileType("photo");
		socialShareRequest.setLocation("shenzhen");
		socialShareRequest.setUserId(55);
		List<ContentRequestData> contentDatas = new ArrayList<ContentRequestData>();
		ContentRequestData contentData = new ContentRequestData();
		contentData.setContentName("520200812094740imageName2.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName2.png");
		contentDatas.add(contentData);
		contentData.setContentName("520200812094740imageName3.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName3.png");
		contentDatas.add(contentData);
		contentData.setContentName("520200812094740imageName4.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName4.png");
		contentDatas.add(contentData);
		socialShareRequest.setContentRequestData(contentDatas);
		
		Share socialShare = new Share();
		socialShare.setEntityId(61);
		when(shareMapper.selectOne(isA(Wrapper.class))).thenReturn(socialShare);
		
		shareService.postContent(socialShareRequest);
	}
	
	/**
	 * Success for post video as draft anf draft non exist
	 * @throws MvpShareException 
	 */
	@Test
	public void testPostContent4() throws MvpShareException {
		SocialShareRequest socialShareRequest = new SocialShareRequest();
		socialShareRequest.setUserId(59);
		socialShareRequest.setPostStatus("N");
		socialShareRequest.setMessage("add by user60");
		socialShareRequest.setFileType("photo");
		socialShareRequest.setLocation("shenzhen");
		socialShareRequest.setUserId(55);
		List<ContentRequestData> contentDatas = new ArrayList<ContentRequestData>();
		ContentRequestData contentData = new ContentRequestData();
		contentData.setContentName("520200812094740imageName2.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName2.png");
		contentDatas.add(contentData);
		contentData.setContentName("520200812094740imageName3.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName3.png");
		contentDatas.add(contentData);
		contentData.setContentName("520200812094740imageName4.png");
		contentData.setContentPath("/useruploadFile/5/photos/520200812094740imageName4.png");
		contentDatas.add(contentData);
		socialShareRequest.setContentRequestData(contentDatas);
		
		shareService.postContent(socialShareRequest);
	}
	
	/**
	 * Success for like content
	 */
	@Test
	public void testGiveLike1() {
		SocialLikeRequest socialLikeRequest = new SocialLikeRequest();
		socialLikeRequest.setFromUserId(60);
		socialLikeRequest.setLikeStatus("Y");
		socialLikeRequest.setShareId(110);
		Share share1 = new Share();
		share1.setLikeCount(3);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share1);
		try {
			shareService.giveLike(socialLikeRequest);
		} catch (MvpShareException e) {
			Assertions.assertEquals("ERR-02-001-01", e.getCode());
		}
	}
	
	/**
	 * Success for like content and the share non exist
	 * @throws MvpShareException
	 */
	@Test
	public void testGiveLike2() {
		SocialLikeRequest socialLikeRequest = new SocialLikeRequest();
		socialLikeRequest.setFromUserId(60);
		socialLikeRequest.setLikeStatus("Y");
		socialLikeRequest.setShareId(1);
		try {
			shareService.giveLike(socialLikeRequest);
		} catch (MvpShareException e) {
			Assertions.assertEquals("ERR-02-001-01", e.getCode());
		}
	}

	@Test
	public void testDeleteContent() {
		Integer shareId = 110;
		shareService.deleteContent(shareId);
	}

	/**
	 * Success for share content
	 */
	@Test
	public void testShareContent1() {
		SocialMsgRequest socialMsgRequest = new  SocialMsgRequest();
		socialMsgRequest.setFromUserId(60);
		socialMsgRequest.setIsRead("N");
		socialMsgRequest.setMessage("");
		socialMsgRequest.setMsgType("photo");
		socialMsgRequest.setShareId(55);
		List<Integer> toUserIds=new ArrayList<Integer>();
		toUserIds.add(64);
		socialMsgRequest.setToUserId(toUserIds);
		Share share1 = new Share();
		share1.setShareCount(3);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share1);
		try {
			shareService.shareContent(socialMsgRequest);
		} catch (MvpShareException e) {
			Assertions.assertEquals("AERR-02-007-01", e.getCode());
		}
	}
	
	/**
	 * Failure for non user to share
	 * @throws MvpShareException
	 */
	@Test
	public void testShareContent2() {
		SocialMsgRequest socialMsgRequest = new  SocialMsgRequest();
		socialMsgRequest.setFromUserId(60);
		socialMsgRequest.setIsRead("N");
		socialMsgRequest.setMessage("");
		socialMsgRequest.setMsgType("photo");
		socialMsgRequest.setShareId(55);
		List<Integer> toUserIds=null;
		socialMsgRequest.setToUserId(toUserIds);
		Share share1 = new Share();
		share1.setShareCount(3);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(share1);
		try {
			shareService.shareContent(socialMsgRequest);
		} catch (MvpShareException e) {
			Assertions.assertEquals("ERR-02-007-01", e.getCode());
		}
	}
	
	class PageMock implements Pageable {

		@Override
		public int getPageNumber() {
			return 0;
		}

		@Override
		public int getPageSize() {
			return 0;
		}

		@Override
		public long getOffset() {
			return 0;
		}

		@Override
		public Sort getSort() {
			return null;
		}

		@Override
		public Pageable next() {
			return null;
		}

		@Override
		public Pageable previousOrFirst() {
			return null;
		}

		@Override
		public Pageable first() {
			return null;
		}

		@Override
		public boolean hasPrevious() {
			return false;
		}
		
	}

}
