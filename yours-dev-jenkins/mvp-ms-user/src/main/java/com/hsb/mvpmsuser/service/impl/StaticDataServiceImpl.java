package com.hsb.mvpmsuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsuser.mapper.CountriesAndRegionsMapper;
import com.hsb.mvpmsuser.model.domain.CountriesAndRegions;
import com.hsb.mvpmsuser.model.payload.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsuser.model.payload.ResponseResult;
import com.hsb.mvpmsuser.model.payload.response.InitDataResponse;
import com.hsb.mvpmsuser.service.StaticDataService;
import com.hsb.mvpmsuser.util.StaticDataUtils;

import cn.hutool.core.collection.CollectionUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class StaticDataServiceImpl extends ServiceImpl<CountriesAndRegionsMapper, CountriesAndRegions> implements StaticDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(StaticDataServiceImpl.class);

	@Autowired
	private CountriesAndRegionsMapper countriesAndRegionsMapper;
	
	private CountriesAndRegionsAreaCodeResponseData toCountriesAndRegionsAreaCodeResponseData(CountriesAndRegions countriesAndRegions) {
		CountriesAndRegionsAreaCodeResponseData responseData = new CountriesAndRegionsAreaCodeResponseData();
		if(countriesAndRegions != null) {
			responseData.setId(countriesAndRegions.entityId());
			responseData.setNameEnUs(countriesAndRegions.nameEnUs());
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
	public List<CountriesAndRegionsAreaCodeResponseData> getCountriesAndRegionsAreaCodeList() {
		List<CountriesAndRegionsAreaCodeResponseData> result = new ArrayList<>();
		
		List<CountriesAndRegions> resultList = countriesAndRegionsMapper.getAreaCodeListByActive();
		if(CollectionUtil.isEmpty(resultList)) {
			logger.warn("There is not [Area Code] data in dataBase!");
		}
		
		resultList.forEach(r -> result.add(toCountriesAndRegionsAreaCodeResponseData(r)));
		return result;
	}

	@Override
	public CountriesAndRegionsAreaCodeResponseData modifyActive(Integer id) {
		CountriesAndRegionsAreaCodeResponseData result = new CountriesAndRegionsAreaCodeResponseData();
		
		CountriesAndRegions countriesAndRegion = countriesAndRegionsMapper.selectById(id);
		if(countriesAndRegion == null) {
			logger.warn("There is not [CountriesAndRegion] data in dataBase!");
		}
		countriesAndRegionsMapper.update(countriesAndRegion.active(!countriesAndRegion.active()),
				new QueryWrapper<CountriesAndRegions>().eq("ENTITY_ID", countriesAndRegion.entityId()));
		
		result = toCountriesAndRegionsAreaCodeResponseData(countriesAndRegion);
		return result;
	}

	@Override
	public CountriesAndRegionsAreaCodeResponseData modifyProhibit(Integer id) {
		CountriesAndRegionsAreaCodeResponseData result = new CountriesAndRegionsAreaCodeResponseData();
		
		CountriesAndRegions countriesAndRegion = countriesAndRegionsMapper.selectById(id);
		if(countriesAndRegion == null) {
			logger.warn("There is not [CountriesAndRegion] data in dataBase!");
		}
		countriesAndRegionsMapper.update(countriesAndRegion.prohibit(!countriesAndRegion.prohibit()),
				new QueryWrapper<CountriesAndRegions>().eq("ENTITY_ID", countriesAndRegion.entityId()));
		
		result = toCountriesAndRegionsAreaCodeResponseData(countriesAndRegion);
		return result;
	}
	
}
