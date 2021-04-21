package com.hsb.mvpmsweb.service;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.util.ReflectionTestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsb.mvpmsweb.api.exception.MvpAssistantException;
import com.hsb.mvpmsweb.mapper.PerformenceMapper;
import com.hsb.mvpmsweb.mapper.PhotoMapper;
import com.hsb.mvpmsweb.mapper.ProductMapper;
import com.hsb.mvpmsweb.mapper.FanMapper;
import com.hsb.mvpmsweb.mapper.ShareMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.mapper.VideoMapper;
import com.hsb.mvpmsweb.model.domain.Performence;
import com.hsb.mvpmsweb.model.domain.Photo;
import com.hsb.mvpmsweb.model.domain.Product;
import com.hsb.mvpmsweb.model.domain.Share;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.domain.Video;
import com.hsb.mvpmsweb.model.payload.assistant.SearchPhotosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchProductsResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchVideosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.request.SearchRequest;
import com.hsb.mvpmsweb.service.impl.AssistantServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
@SuppressWarnings("unchecked")
public class AssistantServiceTest {
	
	@InjectMocks
	private AssistantService assistantService = new AssistantServiceImpl();
	
	@Mock
	private UserInfoMapper userMapper;
	
	@Mock
	private FanMapper fanMapper;
	
	@Mock
	private VideoMapper videoMapper;
	
	@Mock
	private PhotoMapper photoMapper;
	
	@Mock
	private ShareMapper shareMapper;
	
	@Mock
	private ProductMapper productMapper;
	
	@Mock
	private PerformenceMapper performenceMapper;
	
	// TOKEN 会过期,测试相关接口需重新生成
	@SuppressWarnings("unused")
	private static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMTEiLCJzdWIiOiIxMTEiLCJpc3MiOiJNVlAtQkUiLCJpYXQiOjE2MDAyMTgxNjMsImF1ZCI6Ik1WUC1VSSIsImV4cCI6MTYwMDM5MDk2MywibmJmIjoxNjAwMjE4MTYzfQ.fwFtQe1ADlGGk6J1RnVt0M5XLNEy2h1Jv_IkpcM7Blg";
	
	private Pageable pageable;
	private List<UserInfo> initUserList;
	private List<Share> initShareList;
	private List<Photo> initPhotoList;
	private List<Video> initVideoList;
	private List<Product> initProductList;
	private List<Performence> initPerformenceList;
	
	@BeforeEach
	public void setUp() {
		initUserList = new ArrayList<>();
		initShareList = new ArrayList<>();
		initPhotoList = new ArrayList<>();
		initVideoList = new ArrayList<>();
		initProductList = new ArrayList<>();
		initPerformenceList = new ArrayList<>();
		
		initUserList.add(new UserInfo(1, 1, "123456789", "86", "sas", "Tom", "sas@.com", "haha", 1, LocalDate.now(),
				"sz", 0, 0, "/image/usr1.jpg", "/image/usr1.jpg", "111", "12345", LocalDateTime.now(), LocalDateTime.now(), 0, false, "S009"));
		
		initShareList.add(new Share(1, 1, "Y", "share1", "", "SZ", null, 0, 0, 0, 0, LocalDateTime.now(), LocalDateTime.now(), "1"));
		
		initPhotoList.add(new Photo(1, "photo1", "This is photo 1.", "SZ", "/img/image1.jpg", 1, LocalDateTime.now()));
		initPhotoList.add(new Photo(2, "photo2", "This is photo 2.", "SZ", "/img/image2.jpg", 1, LocalDateTime.now()));
		initPhotoList.add(new Photo(3, "photo3", "This is photo 3.", "SZ", "/img/image3.jpg", 1, LocalDateTime.now()));
		
		initVideoList.add(new Video(1, "video1", "This is video 1", "SZ", "/video/video1.mp4", 12, "/video/video1.jpg", 1, LocalDateTime.now()));
		initVideoList.add(new Video(2, "video2", "This is video 2", "SZ", "/video/video2.mp4", 15, "/video/video2.jpg", 2, LocalDateTime.now()));
		initVideoList.add(new Video(3, "video3", "This is video 3", "SZ", "/video/video3.mp4", 34, "/video/video3.jpg", 3, LocalDateTime.now()));
		
		initProductList.add(new Product(1, "product1", "HKD", "D:/test/product/product1.jpg", 1, 1, LocalDateTime.now()));
		
		initPerformenceList.add(new Performence(1, "performence1", LocalDate.now(), LocalTime.now(), LocalTime.now(),
				"sz", BigDecimal.valueOf(120L), "Jay Zhow", 100));
		
		ReflectionTestUtils.setField(assistantService, "base64Secret", "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
		ReflectionTestUtils.setField(assistantService, "tokenPrefix", "Bearer ");
		ReflectionTestUtils.setField(assistantService, "httpUrl", "http://api.errand.online");
		ReflectionTestUtils.setField(assistantService, "photoUrl", "http://shop.errand.online/media");
		ReflectionTestUtils.setField(assistantService, "contentUrl", "http://shop.errand.online/#/shop/goodsdetail");
		ReflectionTestUtils.setField(assistantService, "findAllUrl", "/api/goods/list/all");
		ReflectionTestUtils.setField(assistantService, "findByNameUrl", "/api/goods/query/byname");
		
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
	public void TestSearchPhotos() {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setKeyWord("卿立");
		
		when(userMapper.selectList(isA(QueryWrapper.class))).thenReturn(initUserList);
		when(shareMapper.selectList(isA(QueryWrapper.class))).thenReturn(initShareList);
		when(photoMapper.selectList(isA(QueryWrapper.class))).thenReturn(initPhotoList);
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(initShareList.get(0));
		SearchPhotosResponseData result = assistantService.searchPhotos(pageable, searchRequest);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("photo1", result.getPhotos().get(0).getName());
		Assertions.assertEquals(1, result.getPhotos().size());
	}
	
	@Test
	public void TestSearchVideos() {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setKeyWord("卿立");
		
		when(userMapper.selectList(isA(QueryWrapper.class))).thenReturn(initUserList);
		when(shareMapper.selectList(isA(QueryWrapper.class))).thenReturn(initShareList);
		when(videoMapper.selectOne(isA(QueryWrapper.class))).thenReturn(initVideoList.get(0));
		when(shareMapper.selectById(isA(Integer.class))).thenReturn(initShareList.get(0));
		SearchVideosResponseData result = assistantService.searchVideos(pageable, searchRequest);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals("video1", result.getVideos().get(0).getName());
		Assertions.assertEquals(1, result.getVideos().size());
	}
	
//	@Test
//	public void TestSearchUsers() throws MvpAssistantException {
//		SearchRequest searchRequest = new SearchRequest();
//		searchRequest.setKeyWord("卿立");
//		
//		IPage<UserInfo> userPage = new Page<>();
//		userPage.setTotal(1);
//		userPage.setSize(10);
//		userPage.setCurrent(1);
//		userPage.setRecords(initUserList);
//		
//		String token = "Bearer " + TOKEN;
//		when(userMapper.selectPage(isA(Page.class), isA(QueryWrapper.class))).thenReturn(userPage);
//		when(fanMapper.selectCount(isA(QueryWrapper.class))).thenReturn(0);
//		SearchUsersResponseData result = assistantService.searchUsers(pageable, token, searchRequest);
//		
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals("Tom", result.getUsers().get(0).getNickName());
//		Assertions.assertEquals(1, result.getUsers().size());
//	}
	
	@Test
	public void TestSearchProducts() throws MvpAssistantException {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setKeyWord("卿立");
		
		when(userMapper.selectList(isA(QueryWrapper.class))).thenReturn(initUserList);
		SearchProductsResponseData result = assistantService.searchProducts(pageable, searchRequest);
		
		Assertions.assertNotNull(result);
//      *实际测试结果会有变化
//		Assertions.assertEquals("Fave Tie Mono/BK Collar", result.getProducts().get(0).getName());
//		Assertions.assertEquals("Handmade Chinese Knots Earrings",
//				result.getProducts().get(1).getName());
//		Assertions.assertEquals(6, result.getProducts().size());
	}
	
//	@Test
//	public void TestGetOneResult() throws MvpAssistantException, MvpWebException {
//		SearchRequest searchRequest = new SearchRequest();
//		searchRequest.setKeyWord("test");
//		String token = "Bearer " + TOKEN;
//		
//		IPage<UserInfo> userPage = new Page<>();
//		userPage.setTotal(1);
//		userPage.setSize(10);
//		userPage.setCurrent(1);
//		userPage.setRecords(initUserList);
//		
//		when(userMapper.selectPage(isA(Page.class), isA(QueryWrapper.class))).thenReturn(userPage);
//		when(userMapper.selectList(isA(QueryWrapper.class))).thenReturn(initUserList);
//		when(shareMapper.selectList(isA(QueryWrapper.class))).thenReturn(initShareList);
//		when(photoMapper.selectList(isA(QueryWrapper.class))).thenReturn(initPhotoList);
//		when(videoMapper.selectList(isA(QueryWrapper.class))).thenReturn(initVideoList);
//		when(productMapper.selectList(isA(QueryWrapper.class))).thenReturn(initProductList);
//		when(performenceMapper.selectList(isA(QueryWrapper.class))).thenReturn(initPerformenceList);
//		GetOneResultResponseData result = assistantService.getOneResult(pageable, token, searchRequest);
//		
//		Assertions.assertEquals("video1", ((VideoResponseData)(result.getResult())).getName());
//		Assertions.assertEquals("/video/video1.jpg", ((VideoResponseData)(result.getResult())).getThumbnailUrl());
//	}
	
}
