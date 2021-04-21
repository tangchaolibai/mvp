package com.hsb.mvpmsweb.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsweb.api.exception.MvpAssistantException;
import com.hsb.mvpmsweb.api.exception.MvpAssistantExceptionCode;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.constant.AssistantConstants;
import com.hsb.mvpmsweb.constant.BaseConstants;
import com.hsb.mvpmsweb.constant.SwiftPassConstants;
import com.hsb.mvpmsweb.mapper.PhotoMapper;
import com.hsb.mvpmsweb.mapper.FanMapper;
import com.hsb.mvpmsweb.mapper.ShareMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.mapper.VideoMapper;
import com.hsb.mvpmsweb.model.domain.Photo;
import com.hsb.mvpmsweb.model.domain.Fan;
import com.hsb.mvpmsweb.model.domain.Share;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.domain.Video;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.assistant.AssistantProductResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.GetOneResultResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.PhotoResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchPhotosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchProductsResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchUsersResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.SearchVideosResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.UserResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.VideoResponseData;
import com.hsb.mvpmsweb.model.payload.assistant.request.SearchRequest;
import com.hsb.mvpmsweb.model.payload.assistant.response.InitDataResponse;
import com.hsb.mvpmsweb.model.payload.assistant.swiftpass.SPFindProductsRequest;
import com.hsb.mvpmsweb.model.payload.assistant.swiftpass.SPProductResponseData;
import com.hsb.mvpmsweb.service.AssistantService;
import com.hsb.mvpmsweb.util.AssistantUtils;
import com.hsb.mvpmsweb.util.JwtTokenUtil;
import com.hsb.mvpmsweb.util.WebUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class AssistantServiceImpl implements AssistantService {
	
	private static final Logger logger = LoggerFactory.getLogger(AssistantServiceImpl.class);
	
	@Value("${jwt.base64Secret}")
	private String base64Secret;
	
	@Value("${jwt.token-prefix}")
    private String tokenPrefix;
	
	@Value("${product.url}")
    private String httpUrl;
	
	@Value("${product.photoUrl}")
    private String photoUrl;
	
	@Value("${product.contentUrl}")
    private String contentUrl;
	
	@Value("${product.findByName.url}")
    private String findByNameUrl;
	
	@Value("${product.findAll.url}")
    private String findAllUrl;
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Autowired
	private UserInfoMapper userMapper;
	
	@Autowired
	private ShareMapper shareMapper;
	
	@Autowired
	private FanMapper fanMapper;
	
	private SPFindProductsRequest toSPFindProductsRequest(SearchRequest request, String shopId) {
		SPFindProductsRequest result = new SPFindProductsRequest();
		if(request != null) {
			result.setAction("");
			result.setAppkey("Portal");
			result.setCheck("");
			result.setTimestamp("888888");
			Map<String, String> valuepairs = new HashMap<>();
			valuepairs.put(SwiftPassConstants.PAGE, "1");
			valuepairs.put(SwiftPassConstants.ENTRIES, "10000");
			valuepairs.put(SwiftPassConstants.SHOP_ID, shopId);
			valuepairs.put(SwiftPassConstants.IS_ASCENDING, AssistantConstants.FALSE);
			valuepairs.put(SwiftPassConstants.GOODS_ORDER_BY, SwiftPassConstants.GOODS_ORDER_BY_CREATION_DATE_TIME);
			//valuepairs.put(SwiftPassConstants.PRODUCT_NAME, request.getKeyWord().trim());
			result.setValuepairs(valuepairs);
		}
		return result;
	}
	
	private AssistantProductResponseData toProductResponseData(SPProductResponseData response) {
		AssistantProductResponseData responseData = new AssistantProductResponseData();
		if(response != null) {
			responseData.setId(response.getId());
			responseData.setName(response.getName());
			responseData.setCreationDateTime(LocalDateTime.parse(response.getCreateDateTime()));
			responseData.setType(AssistantConstants.PRODUCT_TYPE_OTHER);
			if(CollectionUtil.isNotEmpty(response.getGoodsOnSalePrices()) && response.getGoodsOnSalePrices().get(0) != null) 
				responseData.setPrice(response.getGoodsOnSalePrices().get(0).getPrice() + AssistantConstants.CURRENCY_HKD);
			if(CollectionUtil.isNotEmpty(response.getPhotoGallery()) && response.getPhotoGallery().get(0).getName() != null) 
				responseData.setImgUrl(photoUrl + "/" + response.getShopId() + "/" + response.getPhotoGallery().get(0).getName());
			responseData.setContentUrl(contentUrl + "/" + response.getId());
		}
		return responseData;
	}
	
	private UserResponseData toUserResponseData(UserInfo userInfo, String userId) {
		UserResponseData responseData = new UserResponseData();
		if(userInfo != null) {
			responseData.setId(userInfo.entityId());
			responseData.setNickName(userInfo.nickName());
			responseData.setImagePath(userInfo.userImgPath());
			responseData.setFollowers(userInfo.fanCount() == null ? 0 : userInfo.fanCount());
			boolean isFollow = fanMapper.selectCount(new QueryWrapper<Fan>().
							eq(AssistantConstants.USER_ID, userInfo.entityId()).
							eq(AssistantConstants.FAN_ID, Integer.parseInt(userId))) > 0;
			responseData.setIsFollow(isFollow);
		}
		return responseData;
	}
	
	private VideoResponseData toVideoResponseData(Video video) {
		VideoResponseData responseData = new VideoResponseData();
		if(video != null) {
			responseData.setId(video.entityId());
			responseData.setName(video.videoName());
			responseData.setThumbnailUrl(video.thumbnailPath());
			responseData.setShareId(video.shareId());
			responseData.setVideoPath(video.videoPath());
			
			Share share = shareMapper.selectById(video.shareId());
			if(share != null) {
				responseData.setProductUrl(share.getProductUrl());
				responseData.setViews(share.getViewAmount());
			}
		}
		return responseData;
	}
	
	private PhotoResponseData toPhotoResponseData(Photo photo) {
		PhotoResponseData responseData = new PhotoResponseData();
		if(photo != null) {
			responseData.setId(photo.entityId());
			responseData.setName(photo.photoName());
			responseData.setThumbnailUrl(photo.photoPath());
			responseData.setShareId(photo.shareId());
			
			Share share = shareMapper.selectById(photo.shareId());
			if(share != null) {
				responseData.setProductUrl(share.getProductUrl());
				responseData.setViews(share.getViewAmount());
			}
		}
		return responseData;
	}
	
	@Override
	public SearchVideosResponseData searchVideos(Pageable pageable, SearchRequest searchRequest) {
		SearchVideosResponseData result = new SearchVideosResponseData();
		List<VideoResponseData> videoResultList = new ArrayList<>();
		String keyWord = searchRequest.getKeyWord().trim();
		
		List<UserInfo> userList = userMapper.
				selectList(new QueryWrapper<UserInfo>().
						like(AssistantConstants.USER_NICKNAME, keyWord));
		if(CollectionUtil.isEmpty(userList)) {
			logger.info("Related user [{}] not found!", keyWord);
			result.setVideos(videoResultList);
			return result;
		}
		
		List<Video> videoListFromDB = new ArrayList<>();
		Video video = null;
		for(UserInfo user : userList) {
			List<Share> shareList = shareMapper.
					selectList(new QueryWrapper<Share>().eq(AssistantConstants.USER_ID, user.entityId()));
			if(CollectionUtil.isEmpty(shareList)) {
				logger.info("Related user [{}] has not post share.", user.nickName());
				continue;
			}
			
			for(Share share : shareList) {
				video = videoMapper.selectOne(new QueryWrapper<Video>().eq(AssistantConstants.SHARE_ID, share.getEntityId()));
				if(video != null)
					videoListFromDB.add(video);
			}
		}
		if(CollectionUtil.isEmpty(videoListFromDB)) {
			logger.info("Related user has not share any videos.");
		}
		
		result.setTotalCount(Integer.valueOf(videoListFromDB.size()).longValue());
		videoListFromDB = videoListFromDB.stream().
				sorted(Comparator.comparing(Video::creationDateTime)).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		videoListFromDB.forEach(v -> videoResultList.add(toVideoResponseData(v)));
		result.setVideos(videoResultList);
		result.setCurrentPage(pageable.getPageNumber());
		return result;
	}

	@Override
	public SearchPhotosResponseData searchPhotos(Pageable pageable, SearchRequest searchRequest) {
		SearchPhotosResponseData result = new SearchPhotosResponseData();
		List<PhotoResponseData> photoResultList = new ArrayList<>();
		String keyWord = searchRequest.getKeyWord().trim();
		
		List<UserInfo> userList = userMapper.
				selectList(new QueryWrapper<UserInfo>().
						like(AssistantConstants.USER_NICKNAME, keyWord));
		if(CollectionUtil.isEmpty(userList)) {
			logger.info("Related user [{}] not found!", keyWord);
			result.setPhotos(photoResultList);
			return result;
		}
		
		List<Photo> photoListFromDB = new ArrayList<>();
		Photo firstPhoto = null;
		for(UserInfo user : userList) {
			List<Share> shareList = shareMapper.
					selectList(new QueryWrapper<Share>().eq(AssistantConstants.USER_ID, user.entityId()));
			if(CollectionUtil.isEmpty(shareList)) {
				logger.info("Related user [{}] has not post share.", user.nickName());
				continue;
			}
			
			for(Share share : shareList) {
				firstPhoto = photoMapper.selectList(new QueryWrapper<Photo>().eq(AssistantConstants.SHARE_ID, share.getEntityId())).
						stream().findFirst().orElse(null);
				if(firstPhoto != null) 
					photoListFromDB.add(firstPhoto);
			}
		}
		if(CollectionUtil.isEmpty(photoListFromDB)) {
			logger.info("Related user has not share any photos.");
		}
		
		result.setTotalCount(Integer.valueOf(photoListFromDB.size()).longValue());
		photoListFromDB = photoListFromDB.stream().distinct().
				sorted(Comparator.comparing(Photo::creationDateTime)).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		photoListFromDB.forEach(p -> photoResultList.add(toPhotoResponseData(p)));
		result.setPhotos(photoResultList);
		result.setCurrentPage(pageable.getPageNumber());
		return result;
	}

	@Override
	public SearchUsersResponseData searchUsers(Pageable pageable, String token, SearchRequest searchRequest) throws MvpAssistantException {
		SearchUsersResponseData result = new SearchUsersResponseData();
		List<UserResponseData> userResultList = new ArrayList<>();
		String keyWord = searchRequest.getKeyWord().trim();
		
		IPage<UserInfo> userPage = userMapper.
				selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize()),
						new QueryWrapper<UserInfo>().like(AssistantConstants.USER_NICKNAME, keyWord).
						ne(AssistantConstants.LOGED_OFF, true));
		if(userPage.getTotal() <= 0L) {
			logger.info("Related user [{}] not found!", keyWord);
		}
		
		token = StringUtils.remove(token, tokenPrefix);
		String userId = null;
		try {
			userId = JwtTokenUtil.getUserId(token, base64Secret);
		} catch (MvpWebException e) {
			throw new MvpAssistantException(e.getCode());
		}
		String userId_ = userId;
		userPage.getRecords().forEach(u -> userResultList.add(toUserResponseData(u, userId_)));
		result.setUsers(userResultList);
		result.setCurrentPage(pageable.getPageNumber());
		result.setTotalCount(userPage.getTotal());
		return result;
	}

	@Override
	public SearchProductsResponseData searchProducts(Pageable pageable, SearchRequest searchRequest) throws MvpAssistantException {
		SearchProductsResponseData result = new SearchProductsResponseData();
		List<AssistantProductResponseData> productResultList = new ArrayList<>();
		SPFindProductsRequest productRequest = new SPFindProductsRequest();
		List<AssistantProductResponseData> spProductList = new ArrayList<>();
		String keyWord = searchRequest.getKeyWord().trim();
		String productKeyFromSwiftPass = SwiftPassConstants.PRODUCT_CONTENT;
		
		List<UserInfo> userProductList = userMapper.selectList(new QueryWrapper<UserInfo>().
				like(AssistantConstants.USER_NICKNAME, keyWord));
		userProductList = userProductList.stream().filter(u -> StrUtil.isNotBlank(u.shopId())).collect(Collectors.toList());
		if(CollectionUtil.isEmpty(userProductList)) {
			logger.info("Related user [{}] not found!", keyWord);
		}else {
			StringBuffer buffer = null;
			HttpURLConnection con = null;
			try {
				URL url = new URL(httpUrl + findAllUrl);
				
				OutputStreamWriter out = null;
				InputStreamReader in = null;
				BufferedReader br = null;
				String line = "";
				List<SPProductResponseData> productList = new ArrayList<>();
				JSONObject productObject = null;
				Object spProductObjectList = null;
				for(UserInfo user : userProductList) {
					if(user != null) {
						buffer = new StringBuffer();
						con = initHttpUrlConnection(url, BaseConstants.REQUEST_METHOD_POST, MediaType.APPLICATION_JSON_VALUE);
						out = new OutputStreamWriter(con.getOutputStream(), BaseConstants.ENCODING_UTF8);
						productRequest = toSPFindProductsRequest(searchRequest, user.shopId());
						out.write(WebUtils.beanToJson(productRequest));
						out.flush();
						out.close();
						
						in = new InputStreamReader(con.getInputStream(), BaseConstants.ENCODING_UTF8);
						br = new BufferedReader(in);
						for (line = br.readLine(); line != null; line = br.readLine()) {
							buffer.append(line);
						}
						in.close();
						br.close();
						con.disconnect();
						
						productObject = JSONObject.parseObject(buffer.toString());
						spProductObjectList = productObject.get(productKeyFromSwiftPass);
						if(spProductObjectList == null) {
							throw new MvpAssistantException(MvpAssistantExceptionCode.ERROR_001_01, new String[] { productKeyFromSwiftPass });
						}
						productList = JSONArray.parseArray(spProductObjectList.toString(), SPProductResponseData.class);
						if(CollectionUtil.isEmpty(productList)) {
							logger.info("Related User's [{}] shop [{}] product not found or not on-sale.", user.nickName(), user.shopId());
							continue;
						}
						
						for(SPProductResponseData product : productList) {
							if(product != null && product.getIsOnSale())
								spProductList.add(toProductResponseData(product));
						}
					}
				}
			} catch (IOException e) {
				throw new MvpAssistantException(MvpAssistantExceptionCode.ERROR_001_02, new String[] { e.getMessage() });
			}
		}
		
		spProductList.addAll(productResultList);
		result.setTotalCount(Integer.valueOf(spProductList.size()).longValue());
		spProductList = spProductList.stream().distinct().
				sorted(Comparator.comparing(AssistantProductResponseData::getCreationDateTime).reversed()).
				sorted(Comparator.comparing(AssistantProductResponseData::getType).reversed()).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		result.setProducts(spProductList);
		result.setCurrentPage(pageable.getPageNumber());
		return result;
	}

	@Override
	public InitDataResponse initData() {
		InitDataResponse result = new InitDataResponse(ResponseResult.DefaultSuccessResponse);
		logger.info("@@@@@@@@@@ Data Initialization...");
		
		Integer dataCount = videoMapper.
				selectCount((new QueryWrapper<Video>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ Videos had been initialized!");
		}else {
			for(Video video : AssistantUtils.initVideoList) {
				videoMapper.insert(video);
			}
		}
		
		dataCount = photoMapper.
				selectCount((new QueryWrapper<Photo>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ Photos had been initialized!");
		}else {
			for(Photo photo : AssistantUtils.initPhotoList) {
				photoMapper.insert(photo);
			}
		}
		
		logger.info("@@@@@@@@@@ Data Initialization Done!");
		return result;
	}
	
	@Override
	public GetOneResultResponseData getOneResult(Pageable pageable, String token, SearchRequest searchRequest) throws MvpAssistantException {
		GetOneResultResponseData result = new GetOneResultResponseData();
		Object searchResult = null;
		
		token = StringUtils.remove(token, tokenPrefix);
		searchResult = getSearchResult(searchVideos(pageable, searchRequest).getVideos(), searchPhotos(pageable, searchRequest).getPhotos(),
				searchUsers(pageable, token, searchRequest).getUsers(), searchProducts(pageable, searchRequest).getProducts());
		if(searchResult == null)
			logger.info("Related data [{}] not found!", searchRequest.getKeyWord());
		
		result.setResult(searchResult);
		return result;
	}
	
	private Object getSearchResult(List<VideoResponseData> videos, List<PhotoResponseData> photos, 
			List<UserResponseData> users, List<AssistantProductResponseData> products) {
		if(CollectionUtil.isNotEmpty(videos)) {
			return videos.get(0);
		}else {
			if(CollectionUtil.isNotEmpty(photos)) {
				return photos.get(0);
			}else {
				if(CollectionUtil.isNotEmpty(users)) {
					return users.get(0);
				}else {
					if(CollectionUtil.isNotEmpty(products)) {
						return products.get(0);
					}else {
						return null;
					}
				}
			}
		}
	}
	
	private HttpURLConnection initHttpUrlConnection(URL url, String requestMethod, String requestProperty) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod(requestMethod);
		con.setRequestProperty(AssistantConstants.REQUEST_PROPERTY_CONTENT_TYPE, requestProperty);
		return con;
	}
	
}
