package com.hsb.mvpmsuser.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsuser.api.MvpStaticDataModuleApi;
import com.hsb.mvpmsuser.model.payload.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsuser.model.payload.ResponseResult;
import com.hsb.mvpmsuser.model.payload.response.CountriesAndRegionsAreaCodeListResponse;
import com.hsb.mvpmsuser.model.payload.response.CountriesAndRegionsResponse;
import com.hsb.mvpmsuser.model.payload.response.InitDataResponse;
import com.hsb.mvpmsuser.service.StaticDataService;

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
	public ResponseEntity<CountriesAndRegionsAreaCodeListResponse> getCountriesAndRegionsAreaCodeList() {
		List<CountriesAndRegionsAreaCodeResponseData> data = staticDataService.getCountriesAndRegionsAreaCodeList();
		CountriesAndRegionsAreaCodeListResponse response = new CountriesAndRegionsAreaCodeListResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CountriesAndRegionsResponse> modifyActive(Integer id) {
		CountriesAndRegionsAreaCodeResponseData data = staticDataService.modifyActive(id);
		CountriesAndRegionsResponse response = new CountriesAndRegionsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CountriesAndRegionsResponse> modifyProhibit(Integer id) {
		CountriesAndRegionsAreaCodeResponseData data = staticDataService.modifyProhibit(id);
		CountriesAndRegionsResponse response = new CountriesAndRegionsResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
}
