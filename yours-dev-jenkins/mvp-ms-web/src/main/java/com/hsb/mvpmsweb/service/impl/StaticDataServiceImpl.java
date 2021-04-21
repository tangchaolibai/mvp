package com.hsb.mvpmsweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsb.mvpmsweb.constant.StaticDataConstants;
import com.hsb.mvpmsweb.mapper.CountriesAndRegionsMapper;
import com.hsb.mvpmsweb.mapper.SystemNotificationMapper;
import com.hsb.mvpmsweb.model.domain.CountriesAndRegions;
import com.hsb.mvpmsweb.model.domain.SystemNotification;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.staticData.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsweb.model.payload.staticData.SystemNotificationResponseData;
import com.hsb.mvpmsweb.model.payload.staticData.response.InitDataResponse;
import com.hsb.mvpmsweb.service.StaticDataService;
import com.hsb.mvpmsweb.util.StaticDataUtils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class StaticDataServiceImpl implements StaticDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(StaticDataServiceImpl.class);

	@Autowired
	private CountriesAndRegionsMapper countriesAndRegionsMapper;
	
	@Autowired
	private SystemNotificationMapper systemNotificationMapper;
	
	private SystemNotificationResponseData toSystemNotificationResponseData(SystemNotification systemNotification) {
		SystemNotificationResponseData responseData = new SystemNotificationResponseData();
		if(systemNotification != null) {
			responseData.setId(systemNotification.getEntityId());
			responseData.setNotification(systemNotification.getNotification());
			responseData.setCreationDateTime(systemNotification.getCreateDateTime());
		}
		return responseData;
	}
	
	private CountriesAndRegionsAreaCodeResponseData toCountriesAndRegionsAreaCodeResponseData(CountriesAndRegions countriesAndRegions) {
		CountriesAndRegionsAreaCodeResponseData responseData = new CountriesAndRegionsAreaCodeResponseData();
		if(countriesAndRegions != null) {
			responseData.setId(countriesAndRegions.entityId());
			responseData.setNameEnUs(countriesAndRegions.nameEnUs());
			responseData.setNameZhCn(countriesAndRegions.nameZhCn());
			responseData.setNameZhHk(countriesAndRegions.nameZhHk());
			responseData.setAreaCode(countriesAndRegions.areaCode());
			responseData.setProhibit(countriesAndRegions.prohibit());
			responseData.setActive(countriesAndRegions.active());
		}
		return responseData;
	}
	
	@Override
	public InitDataResponse initCountriesAndRegionsData() {
		InitDataResponse result = new InitDataResponse(ResponseResult.DefaultSuccessResponse);
		
		Integer dataCount = countriesAndRegionsMapper.
				selectCount((new QueryWrapper<CountriesAndRegions>().last("limit 1")));
		if(dataCount > 0) {
			logger.warn("@@@@@@@@@@ Countries&Regions had been initialized!");
			return result;
		}
		
		logger.info("@@@@@@@@@@ Countries&Regions initialization...");
		for(CountriesAndRegions countriesAndRegions : StaticDataUtils.initCountriesAndRegionsList) {
			countriesAndRegionsMapper.insert(countriesAndRegions);
		}
		
		logger.info("@@@@@@@@@@ Countries&Regions initialization done!");
		return result;
	}

	@Override
	public List<CountriesAndRegionsAreaCodeResponseData> getCountriesAndRegionsAreaCodeList(String keyWord) {
		List<CountriesAndRegionsAreaCodeResponseData> result = new ArrayList<>();
		List<CountriesAndRegions> resultList = new ArrayList<>();
		
		keyWord = keyWord.trim();
		if(StrUtil.isEmpty(keyWord)) {
			resultList = countriesAndRegionsMapper.getAreaCodeListByActive();
		}else {
			resultList = 
					countriesAndRegionsMapper.selectList(new QueryWrapper<CountriesAndRegions>().
							like(StaticDataConstants.NAME_EN_US, keyWord).or().like(StaticDataConstants.AREA_CODE, keyWord));
		}
		if(CollectionUtil.isEmpty(resultList)) {
			logger.info("Related data Not found!");
		}
		
		resultList.forEach(r -> result.add(toCountriesAndRegionsAreaCodeResponseData(r)));
		return result;
	}
	
	@Override
	public CountriesAndRegionsAreaCodeResponseData modifyActive(Integer id) {
		CountriesAndRegionsAreaCodeResponseData result = new CountriesAndRegionsAreaCodeResponseData();
		
		CountriesAndRegions countriesAndRegion = countriesAndRegionsMapper.selectById(id);
		if(countriesAndRegion == null) {
			logger.warn("Modify 'active' failed, there is not CountriesAndRegion [{}] data in dataBase!", id);
			return result;
		}
		countriesAndRegionsMapper.update(countriesAndRegion.active(!countriesAndRegion.active()),
				new QueryWrapper<CountriesAndRegions>().eq(StaticDataConstants.ENTITY_ID, countriesAndRegion.entityId()));
		
		result = toCountriesAndRegionsAreaCodeResponseData(countriesAndRegion);
		return result;
	}

	@Override
	public CountriesAndRegionsAreaCodeResponseData modifyProhibit(Integer id) {
		CountriesAndRegionsAreaCodeResponseData result = new CountriesAndRegionsAreaCodeResponseData();
		
		CountriesAndRegions countriesAndRegion = countriesAndRegionsMapper.selectById(id);
		if(countriesAndRegion == null) {
			logger.warn("Modify 'prohibit' failed, there is not CountriesAndRegion [{}] data in dataBase!", id);
			return result;
		}
		countriesAndRegionsMapper.update(countriesAndRegion.prohibit(!countriesAndRegion.prohibit()),
				new QueryWrapper<CountriesAndRegions>().eq(StaticDataConstants.ENTITY_ID, countriesAndRegion.entityId()));
		
		result = toCountriesAndRegionsAreaCodeResponseData(countriesAndRegion);
		return result;
	}

	@Override
	public InitDataResponse initSystemNotificationData() {
		InitDataResponse result = new InitDataResponse(ResponseResult.DefaultSuccessResponse);
		
		Integer dataCount = systemNotificationMapper.
				selectCount((new QueryWrapper<SystemNotification>().last("limit 1")));
		if(dataCount > 0) {
			logger.info("@@@@@@@@@@ System notificaiton had been initialized!");
			return result;
		}
		
		logger.info("@@@@@@@@@@ System notificaiton initialization...");
		for(SystemNotification systemNotification : StaticDataUtils.initSystemNotificaitonList) {
			systemNotificationMapper.insert(systemNotification);
		}
		
		logger.info("@@@@@@@@@@ System notificaiton initialization done!");
		return result;
	}

	@Override
	public SystemNotificationResponseData getSystemNotificaitonById(Integer id) {
		SystemNotificationResponseData result = new SystemNotificationResponseData();
		
		SystemNotification systemNotification = systemNotificationMapper.selectById(id);
		if(systemNotification == null) {
			logger.warn("System notification [{}] not found!", id);
			return result;
		}
		
		result = toSystemNotificationResponseData(systemNotification);
		return result;
	}

	@Override
	public List<SystemNotificationResponseData> getAllSystemNotificaiton() {
		List<SystemNotificationResponseData> result = new ArrayList<>();
		
		List<SystemNotification> resultList = systemNotificationMapper.selectList(new QueryWrapper<SystemNotification>());
		if(CollectionUtil.isEmpty(resultList)) {
			logger.info("There is not [System Notification] data in dataBase!");
		}
		
		resultList.forEach(r -> result.add(toSystemNotificationResponseData(r)));
		return result;
	}
	
}
