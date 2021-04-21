package com.hsb.mvpmsweb.service;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsweb.api.exception.MvpMessageException;
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
import com.hsb.mvpmsweb.model.payload.message.CountResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetATResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetCommentsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetLikesResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetNotificaitonsResponseData;
import com.hsb.mvpmsweb.model.payload.message.GetSystemResponseData;
import com.hsb.mvpmsweb.service.impl.MessageServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
@SuppressWarnings("unchecked")
public class MessageServiceTest {
	
	@InjectMocks
	private MessageService messageService = new MessageServiceImpl();
	
	@Mock
	private MessageMapper messageMapper;
	
	@Mock
	private UserInfoMapper userMapper;
	
	@Mock
	private ShareMapper shareMapper;
	
	@Mock
	private PhotoMapper photoMapper;
	
	@Mock
	private VideoMapper videoMapper;
	
	@Mock
	private LikeMapper likeMapper;
	
	@Mock
	private ReplyMapper replyMapper;
	
	@Mock
	private CommentMapper commentMapper;
	
	private Pageable pageable;
	private List<Message> initMessageList;
	private List<UserInfo> initUserList;
	private List<Share> initShareList;
	private List<Photo> initPhotoList;
	private List<Video> initVideoList;
	private List<Like> initLikeList;
	private List<Reply> initReplyList;
	private List<Comment> initCommentList;
	
	@BeforeEach
	public void setUp() {
		initMessageList = new ArrayList<>();
		initUserList = new ArrayList<>();
		initShareList = new ArrayList<>();
		initPhotoList = new ArrayList<>();
		initVideoList = new ArrayList<>();
		initLikeList = new ArrayList<>();
		initReplyList = new ArrayList<>();
		initCommentList = new ArrayList<>();
		
		initUserList.add(new UserInfo(1, 1, "123456789", "86", "sas", "Tom", "sas@.com", "haha", 1, LocalDate.now(),
				"sz", 0, 0, "/image/usr1.jpg", "/image/usr1.jpg", "111", "12345", LocalDateTime.now(), LocalDateTime.now(), 0, false, "S009"));
		initUserList.add(new UserInfo(2, 1, "1234567890", "86", "ddd", "Jerry", "ddd@.com", "hahas", 0, LocalDate.now(),
				"sz", 0, 0, "/image/usr2.jpg", "/image/usr2.jpg", "123", "12345", LocalDateTime.now(), LocalDateTime.now(), 0, false, null));
		
		initShareList.add(new Share(1, 2, "Y", "share1", "photo", "sz", null, 0, 0, 0, 0,
				LocalDateTime.now(), LocalDateTime.now(), null));
		initShareList.add(new Share(2, 1, "Y", "share2", "video", "sz", null, 0, 0, 0, 0,
				LocalDateTime.now(), LocalDateTime.now(), null));
		
		initPhotoList.add(new Photo(1, "photo1", "This is photo one!", "sz", "D:/test/photo/photo1.jpg", 1, LocalDateTime.now()));
		
		initVideoList.add(new Video(1, "video1", "This is video one!", "sz", "D:/test/video/video1.mp4", 11,
				"D:/test/video/thumbail/video1.jpg", 2, LocalDateTime.now()));
		
		initReplyList.add(new Reply(1, 1, 2, 1, "good!", LocalDateTime.now()));
		
		initMessageList.add(new Message(1, "system", "aaa", 0, 1, null, "N", LocalDateTime.now()));
		initMessageList.add(new Message(1, "system", "bbb", 0, 1, null, "N", LocalDateTime.now()));
		initMessageList.add(new Message(1, "chat", "ccc", 0, 1, null, "Y", LocalDateTime.now()));
		initMessageList.add(new Message(1, "chat", "ddd", 0, 1, null, "Y", LocalDateTime.now()));
		
		initLikeList.add(new Like(1, 1, 2, LocalDateTime.now()));
		
		initCommentList.add(new Comment(1, 1, 2, "haha!", LocalDateTime.now()));
		
		pageable = new Pageable() {
			
			@Override
			public Pageable previousOrFirst() {
				return null;
			}
			
			@Override
			public Pageable next() {
				return null;
			}
			
			@Override
			public boolean hasPrevious() {
				return false;
			}
			
			@Override
			public Sort getSort() {
				return null;
			}
			
			@Override
			public int getPageSize() {
				return 10;
			}
			
			@Override
			public int getPageNumber() {
				return 0;
			}
			
			@Override
			public long getOffset() {
				return 0;
			}
			
			@Override
			public Pageable first() {
				return null;
			}
		};
	}
	
	@Test
	public void TestGetUnreadNotificationsCount() {
		when(messageMapper.selectCount(isA(QueryWrapper.class))).thenReturn(2);
		CountResponseData result = messageService.getUnreadNotificationsCount(initUserList.get(0).entityId());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2, result.getCount());
	}
	
	@Test
	public void TestGetNotifications() {
		IPage<Message> socialPage = new Page<>();
		socialPage.setRecords(initMessageList);
		socialPage.setSize(10);
		socialPage.setTotal(1);
		
		when(messageMapper.selectPage(isA(Page.class), isA(QueryWrapper.class))).thenReturn(socialPage);
		when(userMapper.selectById(isA(Integer.class))).thenReturn(initUserList.get(0));
		GetNotificaitonsResponseData result = messageService.getNotifications(pageable, initUserList.get(0).entityId());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("aaa", result.getNotificaiton().get(0).getMsg());
		Assertions.assertEquals("bbb", result.getNotificaiton().get(1).getMsg());
		Assertions.assertEquals("ccc", result.getNotificaiton().get(2).getMsg());
		Assertions.assertEquals("ddd", result.getNotificaiton().get(3).getMsg());
		Assertions.assertEquals(4, result.getNotificaiton().size());
	}
	
	@Test
	public void TestDeleteNotificaiton() throws MvpMessageException {
		Message delNotification = initMessageList.get(2);
		when(messageMapper.selectById(isA(Integer.class))).thenReturn(delNotification);
		when(messageMapper.deleteById(isA(Integer.class))).thenReturn(1);
		messageService.deleteNotificaiton(1);
		
		Assertions.assertNotNull(delNotification);
		Assertions.assertEquals("ccc", delNotification.getMsg());
	}
	
	@Test
	public void TestGetSystemMessage() {
		List<Message> systemMessage = initMessageList;
		systemMessage = systemMessage.stream().filter(s -> MessageConstants.MESSAGE_TYPE_SYSTEM.equals(s.getMsgType())).collect(Collectors.toList());
		IPage<Message> SystemMessagePage = new Page<>();
		SystemMessagePage.setRecords(systemMessage);
		SystemMessagePage.setSize(10);
		SystemMessagePage.setTotal(1);
		
		when(messageMapper.selectPage(isA(Page.class), isA(QueryWrapper.class))).thenReturn(SystemMessagePage);
		GetSystemResponseData result = messageService.getSystemMessage(pageable, initUserList.get(0).entityId());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("aaa", result.getMessages().get(0).getMsg());
		Assertions.assertEquals("bbb", result.getMessages().get(1).getMsg());
		Assertions.assertEquals(2, result.getMessages().size());
	}
	
	@Test
	public void TestGetLikes() {
		when(shareMapper.selectList(isA(QueryWrapper.class))).thenReturn(initShareList);
		when(likeMapper.selectList(isA(QueryWrapper.class))).thenReturn(initLikeList);
		when(userMapper.selectById(isA(Integer.class))).thenReturn(initUserList.get(0));
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(initShareList.get(0));
		when(photoMapper.selectList(isA(QueryWrapper.class))).thenReturn(initPhotoList);
		GetLikesResponseData result = messageService.getLikes(pageable, initUserList.get(0).entityId());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(2, result.getLikes().get(0).getFromUserId());
		Assertions.assertEquals(2, result.getLikes().size());
	}
	
	@Test
	public void TestGetATMe() {
		when(replyMapper.selectList(isA(QueryWrapper.class))).thenReturn(initReplyList);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(initShareList.get(0));
		when(userMapper.selectById(isA(Integer.class))).thenReturn(initUserList.get(0));
		when(commentMapper.selectById(isA(Integer.class))).thenReturn(initCommentList.get(0));
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(initShareList.get(0));
		when(photoMapper.selectList(isA(QueryWrapper.class))).thenReturn(initPhotoList);
		GetATResponseData result = messageService.getATMe(pageable, initUserList.get(0).entityId());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("good!", result.getReplys().get(0).getReply());
		Assertions.assertEquals(1, result.getReplys().size());
	}
	
	@Test
	public void TestComments() {
		when(shareMapper.selectList(isA(QueryWrapper.class))).thenReturn(initShareList);
		when(commentMapper.selectList(isA(QueryWrapper.class))).thenReturn(initCommentList);
		when(userMapper.selectById(isA(Integer.class))).thenReturn(initUserList.get(0));
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(initShareList.get(0));
		when(photoMapper.selectList(isA(QueryWrapper.class))).thenReturn(initPhotoList);
		GetCommentsResponseData result = messageService.getComments(pageable, initUserList.get(0).entityId());
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("haha!", result.getComments().get(0).getComment());
		Assertions.assertEquals(2, result.getComments().size());
	}
	
}
