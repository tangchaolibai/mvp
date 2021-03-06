/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.hsb.mvpmsuser.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsb.mvpmsuser.annotation.JwtIgnore;
import com.hsb.mvpmsuser.model.payload.ExceptionResponse;
import com.hsb.mvpmsuser.model.payload.response.CountriesAndRegionsAreaCodeListResponse;
import com.hsb.mvpmsuser.model.payload.response.CountriesAndRegionsResponse;
import com.hsb.mvpmsuser.model.payload.response.InitDataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {"MVP STATIC DATA Module"})
public interface MvpStaticDataModuleApi {

	@JwtIgnore
    @ApiOperation(value = "Init Countries And Regions Data", nickname = "initCountriesAndRegionsData", notes = "Init Countries And Regions Data", response = InitDataResponse.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = InitDataResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
    @GetMapping(value = "/initData/countriesAndRegions", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InitDataResponse> initCountriesAndRegionsData();

    
	@JwtIgnore
    @ApiOperation(value = "Get Active Countries And Regions Area Code List", nickname = "getCountriesAndRegionsAreaCodeList", notes = "Get Active Countries And Regions Area Code List", response = CountriesAndRegionsAreaCodeListResponse.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CountriesAndRegionsAreaCodeListResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
    @GetMapping(value = "/areaCode/countriesAndRegions/active", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CountriesAndRegionsAreaCodeListResponse> getCountriesAndRegionsAreaCodeList();
	
	
	@JwtIgnore
    @ApiOperation(value = "modify Countries And Regions Active Field", nickname = "modifyActive", notes = "modify Countries And Regions Active Field", response = CountriesAndRegionsResponse.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CountriesAndRegionsResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
    @PostMapping(value = "/areaCode/countriesAndRegions/modifyActive", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CountriesAndRegionsResponse> modifyActive(@ApiParam(value = "countriesAndRegions id", required = true) @RequestParam(value = "id", required = true) Integer id);
	
	
	@JwtIgnore
    @ApiOperation(value = "modify Countries And Regions Prohibit Field", nickname = "modifyProhibit", notes = "modify Countries And Regions Prohibit Field", response = CountriesAndRegionsResponse.class)
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CountriesAndRegionsResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ExceptionResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionResponse.class)})
    @PostMapping(value = "/areaCode/countriesAndRegions/modifyProhibit", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CountriesAndRegionsResponse> modifyProhibit(@ApiParam(value = "countriesAndRegions id", required = true) @RequestParam(value = "id", required = true) Integer id);
	
}
