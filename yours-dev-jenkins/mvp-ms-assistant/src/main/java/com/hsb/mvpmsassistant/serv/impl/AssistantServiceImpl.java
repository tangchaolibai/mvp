package com.hsb.mvpmsassistant.serv.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsb.mvpmsassistant.constant.AssistantConstants;
import com.hsb.mvpmsassistant.mapper.CinemaMapper;
import com.hsb.mvpmsassistant.mapper.FilmMapper;
import com.hsb.mvpmsassistant.mapper.FilmScheduleMapper;
import com.hsb.mvpmsassistant.mapper.PhotoMapper;
import com.hsb.mvpmsassistant.mapper.SocialShareMapper;
import com.hsb.mvpmsassistant.mapper.UserMapper;
import com.hsb.mvpmsassistant.mapper.VideoMapper;
import com.hsb.mvpmsassistant.model.entity.Photo;
import com.hsb.mvpmsassistant.model.entity.Video;
import com.hsb.mvpmsassistant.model.payload.GetOneResultResponseData;
import com.hsb.mvpmsassistant.model.payload.PhotoResponseData;
import com.hsb.mvpmsassistant.model.payload.ProductResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchPhotosResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchProductsResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchUsersResponseData;
import com.hsb.mvpmsassistant.model.payload.SearchVideosResponseData;
import com.hsb.mvpmsassistant.model.payload.UserResponseData;
import com.hsb.mvpmsassistant.model.payload.VideoResponseData;
import com.hsb.mvpmsassistant.model.payload.request.SearchRequest;
import com.hsb.mvpmsassistant.model.payload.response.InitDataResponse;
import com.hsb.mvpmsassistant.model.payload.response.ResponseResult;
import com.hsb.mvpmsassistant.serv.AssistantService;
import com.hsb.mvpmsassistant.util.AssistantUtils;
import com.hsb.mvpmsshare.model.domain.SocialShare;
import com.hsb.mvpmsticket.model.entity.Cinema;
import com.hsb.mvpmsticket.model.entity.Film;
import com.hsb.mvpmsticket.model.entity.FilmSchedule;
import com.hsb.mvpmsuser.model.domain.UserInfo;

import cn.hutool.core.collection.CollectionUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class AssistantServiceImpl implements AssistantService {
	
	private static final Logger logger = LoggerFactory.getLogger(AssistantServiceImpl.class);
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SocialShareMapper shareMapper;
	
	@Autowired
	private FilmMapper filmMapper;
	
	@Autowired
	private CinemaMapper cinemaMapper;
	
	@Autowired
	private FilmScheduleMapper filmScheduleMapper;
	
	private ProductResponseData toProductResponseData(Film film, FilmSchedule filmSchedule) {
		ProductResponseData responseData = new ProductResponseData();
		if(filmSchedule != null) {
			responseData.setId(filmSchedule.entityId());
			responseData.setName(film.chineseName());
			responseData.setPrice(filmSchedule.price() + filmSchedule.currency());
			responseData.setImgUrl(film.image());
			responseData.setCreationDate(filmSchedule.date());
			responseData.setType(AssistantConstants.PRODUCT_TYPE_TICKET);
		}
		return responseData;
	}
	
	private UserResponseData toUserResponseData(UserInfo userInfo) {
		UserResponseData responseData = new UserResponseData();
		if(userInfo != null) {
			responseData.setId(userInfo.entityId());
			responseData.setNickName(userInfo.nickName());
			responseData.setImagePath(userInfo.userImgPath());
			responseData.setFollowers(userInfo.fanCount());
			responseData.setIsFollow(false);
		}
		return responseData;
	}
	
	private VideoResponseData toVideoResponseData(Video video) {
		VideoResponseData responseData = new VideoResponseData();
		if(video != null) {
			responseData.setId(video.entityId());
			responseData.setName(video.videoName());
			responseData.setPath(video.thumbnailUrl());
			responseData.setShareId(video.shareId());
		}
		return responseData;
	}
	
	private PhotoResponseData toPhotoResponseData(Photo photo) {
		PhotoResponseData responseData = new PhotoResponseData();
		if(photo != null) {
			responseData.setId(photo.entityId());
			responseData.setName(photo.photoName());
			responseData.setPath(photo.photoPath());
			responseData.setShareId(photo.shareId());
		}
		return responseData;
	}
	
	@Override
	public SearchVideosResponseData searchVideos(Pageable pageable, SearchRequest searchRequest) {
		SearchVideosResponseData result = new SearchVideosResponseData();
		List<VideoResponseData> videoResultList = new ArrayList<>();
		
		List<UserInfo> userList = userMapper.
				selectList(new QueryWrapper<UserInfo>().like(AssistantConstants.USER_NICKNAME, searchRequest.getKeyWord()));
		if(CollectionUtil.isEmpty(userList)) {
			logger.info("Related [user] not found.");
			result.setVideos(videoResultList);
			return result;
		}
		
		List<Video> videoListFromDB = new ArrayList<>();
		userList.forEach(u -> {
			List<SocialShare> shareList = shareMapper.
					selectList(new QueryWrapper<SocialShare>().eq(AssistantConstants.USER_ID, u.entityId()));
			
			if(CollectionUtil.isEmpty(shareList)) {
				logger.info("Related user [{}] has not post share.", u.nickName());
			}else {
				shareList.forEach(s -> {
					videoListFromDB.addAll(videoMapper.selectList(new QueryWrapper<Video>().eq(AssistantConstants.SHARE_ID, s.getEntityId())));
				});
			}
		});
		
		if(CollectionUtil.isEmpty(videoListFromDB)) {
			logger.info("Searched [user] has not share any [videos].");
		}
		
		List<Video> videoListFromDB_ = videoListFromDB.stream().
				sorted(Comparator.comparing(Video::creationDateTime)).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		videoListFromDB_.forEach(v -> videoResultList.add(toVideoResponseData(v)));
		result.setVideos(videoResultList);
		
		return result;
	}

	@Override
	public SearchPhotosResponseData searchPhotos(Pageable pageable, SearchRequest searchRequest) {
		SearchPhotosResponseData result = new SearchPhotosResponseData();
		List<PhotoResponseData> photoResultList = new ArrayList<>();
		
		List<UserInfo> userList = userMapper.
				selectList(new QueryWrapper<UserInfo>().like(AssistantConstants.USER_NICKNAME, searchRequest.getKeyWord()));
		if(CollectionUtil.isEmpty(userList)) {
			logger.info("Related [user] not found.");
			result.setPhotos(photoResultList);
			return result;
		}
		
		List<Photo> photoListFromDB = new ArrayList<>();
		userList.forEach(u -> {
			List<SocialShare> shareList = shareMapper.
					selectList(new QueryWrapper<SocialShare>().eq(AssistantConstants.USER_ID, u.entityId()));
			
			if(CollectionUtil.isEmpty(shareList)) {
				logger.info("Related user [{}] has not post share.", u.nickName());
			}else {
				shareList.forEach(s -> {
					photoListFromDB.addAll(photoMapper.selectList(new QueryWrapper<Photo>().eq(AssistantConstants.SHARE_ID, s.getEntityId())));
				});
			}
		});
		
		if(CollectionUtil.isEmpty(photoListFromDB)) {
			logger.info("Searched [user] has not share any [photos].");
		}
		
		List<Photo> photoListFromDB_ = photoListFromDB.stream().
				sorted(Comparator.comparing(Photo::creationDateTime)).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		photoListFromDB_.forEach(p -> photoResultList.add(toPhotoResponseData(p)));
		result.setPhotos(photoResultList);
		
		return result;
	}

	@Override
	public SearchUsersResponseData searchUsers(Pageable pageable, SearchRequest searchRequest) {
		SearchUsersResponseData result = new SearchUsersResponseData();
		
		List<UserResponseData> userResultList = new ArrayList<>();
		IPage<UserInfo> userPage = userMapper.
				selectPage(new Page<>(pageable.getPageNumber(), pageable.getPageSize(), false),
						new QueryWrapper<UserInfo>().like(AssistantConstants.USER_NICKNAME, searchRequest.getKeyWord()));
		
		if(userPage.getTotal() <= 0L) {
			logger.info("Related [user] not found.");
		}
		
		userPage.getRecords().forEach(u -> userResultList.add(toUserResponseData(u)));
		result.setUsers(userResultList);
		
		return result;
	}

	@Override
	public SearchProductsResponseData searchProducts(Pageable pageable, SearchRequest searchRequest) {
		SearchProductsResponseData result = new SearchProductsResponseData();
		List<ProductResponseData> productResultList = new ArrayList<>();
		// WFT:威富通
		List<ProductResponseData> productListFromWFT = new ArrayList<>();
		ProductResponseData pData = new ProductResponseData();
		pData.setId(120);
		pData.setName("WFT商品");
		pData.setPrice(BigDecimal.valueOf(1200) + "HKD");
		pData.setImgUrl("null");
		pData.setType(AssistantConstants.PRODUCT_TYPE_OTHER);
		pData.setCreationDate(LocalDate.now());
		
		productListFromWFT.add(pData);
		// TODO
		
		List<Cinema> cinemaList = cinemaMapper.
				selectList(new QueryWrapper<Cinema>().
						like(AssistantConstants.CINEMA_NAME, searchRequest.getKeyWord()));
		
		if(CollectionUtil.isEmpty(cinemaList)) {
			logger.info("Related [cinema] not found.");
		}
		
		cinemaList.forEach(c -> {
			List<FilmSchedule> filmScheduleList = filmScheduleMapper.
					selectList(new QueryWrapper<FilmSchedule>().eq(AssistantConstants.CINEMA_ID, c.entityId()));
			
			if(CollectionUtil.isEmpty(filmScheduleList)) {
				logger.info("Related cinema [{}] has not film schedule.", c.name());
			}else {
				filmScheduleList.forEach(fs -> productResultList.add(toProductResponseData(filmMapper.selectById(fs.filmId()), fs)));
			}
		});
		
		productListFromWFT.addAll(productResultList);
		productListFromWFT = productListFromWFT.stream().
				sorted(Comparator.comparing(ProductResponseData::getCreationDate).reversed()).
				sorted(Comparator.comparing(ProductResponseData::getType).reversed()).
				skip(pageable.getPageNumber() * pageable.getPageSize()).
				limit(pageable.getPageSize()).
				collect(Collectors.toList());
		result.setProducts(productListFromWFT);
		
		return result;
	}

	@Override
	public InitDataResponse initData() {
		InitDataResponse result = new InitDataResponse(ResponseResult.DefaultSuccessResponse);
		logger.info("@@@@@@@@@@ Data Initialization...");
		
		Integer dataCount = videoMapper.
				selectCount((new QueryWrapper<Video>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Videos had been initialized!");
		}else {
			for(Video video : AssistantUtils.initVideoList) {
				videoMapper.insert(video);
			}
		}
		
		dataCount = photoMapper.
				selectCount((new QueryWrapper<Photo>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Photos had been initialized!");
		}else {
			for(Photo photo : AssistantUtils.initPhotoList) {
				photoMapper.insert(photo);
			}
		}
		
		logger.info("@@@@@@@@@@ Data Initialization Done!");
		return result;
	}

	@Override
	public GetOneResultResponseData getOneResult(Pageable pageable, SearchRequest searchRequest) {
		GetOneResultResponseData result = new GetOneResultResponseData();
		Object searchResult = null;
		
		searchResult = getSearchResult(searchVideos(pageable, searchRequest).getVideos(), searchPhotos(pageable, searchRequest).getPhotos(),
				searchUsers(pageable, searchRequest).getUsers(), searchProducts(pageable, searchRequest).getProducts());
		
		result.setResult(searchResult);
		return result;
	}
	
	private Object getSearchResult(List<VideoResponseData> videos, List<PhotoResponseData> photos, 
			List<UserResponseData> users, List<ProductResponseData> products) {
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
	
}
