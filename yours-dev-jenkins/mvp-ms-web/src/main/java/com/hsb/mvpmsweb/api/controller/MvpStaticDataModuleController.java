package com.hsb.mvpmsweb.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsweb.api.MvpStaticDataModuleApi;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.request.CountriesAndRegionsIdRequest;
import com.hsb.mvpmsweb.model.payload.staticData.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsweb.model.payload.staticData.SystemNotificationResponseData;
import com.hsb.mvpmsweb.model.payload.staticData.response.CountriesAndRegionsAreaCodeListResponse;
import com.hsb.mvpmsweb.model.payload.staticData.response.CountriesAndRegionsResponse;
import com.hsb.mvpmsweb.model.payload.staticData.response.InitDataResponse;
import com.hsb.mvpmsweb.model.payload.staticData.response.SystemNotificationResponse;
import com.hsb.mvpmsweb.model.payload.staticData.response.SystemNotificationListResponse;
import com.hsb.mvpmsweb.service.StaticDataService;

@RequestMapping(value = "staticData")
@RestController
@Validated
public class MvpStaticDataModuleController implements MvpStaticDataModuleApi {

	@Autowired
	private StaticDataService staticDataService;
	
	@Override
	public ResponseEntity<InitDataResponse> initCountriesAndRegionsData() {
		InitDataResponse response = staticDataService.initCountriesAndRegionsData();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CountriesAndRegionsAreaCodeListResponse> getCountriesAndRegionsAreaCodeList(String keyWord) {
		List<CountriesAndRegionsAreaCodeResponseData> data = staticDataService.getCountriesAndRegionsAreaCodeList(keyWord);
		CountriesAndRegionsAreaCodeListResponse response = new CountriesAndRegionsAreaCodeListResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<CountriesAndRegionsResponse> modifyActive(CountriesAndRegionsIdRequest request) {
		CountriesAndRegionsAreaCodeResponseData data = staticDataService.modifyActive(request.id());
		CountriesAndRegionsResponse response = new CountriesAndRegionsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CountriesAndRegionsResponse> modifyProhibit(CountriesAndRegionsIdRequest request) {
		CountriesAndRegionsAreaCodeResponseData data = staticDataService.modifyProhibit(request.id());
		CountriesAndRegionsResponse response = new CountriesAndRegionsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<InitDataResponse> initSystemNotificaitonData() {
		InitDataResponse response = staticDataService.initSystemNotificationData();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SystemNotificationResponse> getSystemNotificaitonById(Integer id) {
		SystemNotificationResponseData data = staticDataService.getSystemNotificaitonById(id);
		SystemNotificationResponse response = new SystemNotificationResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SystemNotificationListResponse> getAllSystemNotificaiton() {
		List<SystemNotificationResponseData> data = staticDataService.getAllSystemNotificaiton();
		SystemNotificationListResponse response = new SystemNotificationListResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
}
