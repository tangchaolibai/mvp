package com.hsb.mvpmsweb.service;

import java.util.List;

import com.hsb.mvpmsweb.model.payload.staticData.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsweb.model.payload.staticData.SystemNotificationResponseData;
import com.hsb.mvpmsweb.model.payload.staticData.response.InitDataResponse;

public interface StaticDataService {
	
	public InitDataResponse initCountriesAndRegionsData();
	public List<CountriesAndRegionsAreaCodeResponseData> getCountriesAndRegionsAreaCodeList(String keyWord);
	public CountriesAndRegionsAreaCodeResponseData modifyActive(Integer id);
	public CountriesAndRegionsAreaCodeResponseData modifyProhibit(Integer id);
	public InitDataResponse initSystemNotificationData();
	public SystemNotificationResponseData getSystemNotificaitonById(Integer id);
	public List<SystemNotificationResponseData> getAllSystemNotificaiton();
	
}
