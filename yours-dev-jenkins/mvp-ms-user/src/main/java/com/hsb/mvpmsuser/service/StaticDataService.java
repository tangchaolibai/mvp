package com.hsb.mvpmsuser.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsuser.model.domain.CountriesAndRegions;
import com.hsb.mvpmsuser.model.payload.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsuser.model.payload.response.InitDataResponse;

public interface StaticDataService extends IService<CountriesAndRegions> {
	
	public InitDataResponse initCountriesAndRegionsData();
	public List<CountriesAndRegionsAreaCodeResponseData> getCountriesAndRegionsAreaCodeList();
	public CountriesAndRegionsAreaCodeResponseData modifyActive(Integer id);
	public CountriesAndRegionsAreaCodeResponseData modifyProhibit(Integer id);
	
}
