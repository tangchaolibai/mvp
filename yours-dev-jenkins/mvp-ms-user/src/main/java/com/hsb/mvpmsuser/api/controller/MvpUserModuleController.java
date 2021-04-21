package com.hsb.mvpmsuser.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsuser.annotation.JwtIgnore;
import com.hsb.mvpmsuser.api.MvpUserModuleApi;
import com.hsb.mvpmsuser.exception.MvpMsUserException;
import com.hsb.mvpmsuser.model.payload.LoginResponseData;
import com.hsb.mvpmsuser.model.payload.ResponseResult;
import com.hsb.mvpmsuser.model.payload.SearchUserResponseData;
import com.hsb.mvpmsuser.model.payload.SendSMSResponseData;
import com.hsb.mvpmsuser.model.payload.SystemSettingResponseData;
import com.hsb.mvpmsuser.model.payload.UserInfoResponseData;
import com.hsb.mvpmsuser.model.payload.VerificationCodeResponseData;
import com.hsb.mvpmsuser.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsuser.model.payload.request.EditUserRequest;
import com.hsb.mvpmsuser.model.payload.request.LoginRequest;
import com.hsb.mvpmsuser.model.payload.request.RegisterRequest;
import com.hsb.mvpmsuser.model.payload.response.BaseResponse;
import com.hsb.mvpmsuser.model.payload.response.EditSystemSettingResponse;
import com.hsb.mvpmsuser.model.payload.response.EditUserResponse;
import com.hsb.mvpmsuser.model.payload.response.FindUserResponse;
import com.hsb.mvpmsuser.model.payload.response.GenVerificationCodeResponse;
import com.hsb.mvpmsuser.model.payload.response.LoginResponse;
import com.hsb.mvpmsuser.model.payload.response.RegisterResponse;
import com.hsb.mvpmsuser.model.payload.response.SearchUserResponse;
import com.hsb.mvpmsuser.model.payload.response.SendSMSResponse;
import com.hsb.mvpmsuser.model.payload.response.VerificationResultResponse;
import com.hsb.mvpmsuser.service.UserInfoService;

@RestController
@Validated
public class MvpUserModuleController implements MvpUserModuleApi {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	@JwtIgnore
	public ResponseEntity<LoginResponse> login(@Valid LoginRequest loginRequest) throws MvpMsUserException {
		LoginResponseData data = userInfoService.loginUser(loginRequest);
		LoginResponse response = new LoginResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	@JwtIgnore
	public ResponseEntity<RegisterResponse> register(@Valid RegisterRequest registerRequest) throws MvpMsUserException {
		UserInfoResponseData data = userInfoService.registerUser(registerRequest);
		RegisterResponse response = new RegisterResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EditUserResponse> editUser(String entityId, @Valid EditUserRequest editUserRequest) throws MvpMsUserException {
		UserInfoResponseData data = userInfoService.editUser(entityId, editUserRequest);
		EditUserResponse response = new EditUserResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FindUserResponse> findUser(String entityId) throws MvpMsUserException {
		UserInfoResponseData data = userInfoService.findUser(entityId);
		FindUserResponse response = new FindUserResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> closeAccount(String entityId) throws MvpMsUserException {
		userInfoService.closeAccount(entityId);
		BaseResponse response = new BaseResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> logOutUser(String entityId) throws MvpMsUserException {
		userInfoService.logOut(entityId);
		BaseResponse response = new BaseResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EditSystemSettingResponse> editSystemSetting( @Valid EditSystemSettingRequest editSystemSettingRequest) {
		SystemSettingResponseData data = userInfoService.editSystemSetting(editSystemSettingRequest);
		EditSystemSettingResponse response = new EditSystemSettingResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EditSystemSettingResponse> getSystemSetting(Integer userId) {
		SystemSettingResponseData data = userInfoService.getSystemSetting(userId);
		EditSystemSettingResponse response = new EditSystemSettingResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<SearchUserResponse> getUserList(String assistantMsg) {
		List<SearchUserResponseData> data = userInfoService.getUserList(assistantMsg);
		SearchUserResponse response = new SearchUserResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<GenVerificationCodeResponse> getVerificationCode(@NotNull @Valid String phoneNumber) {
		VerificationCodeResponseData data = userInfoService.genVerificationCode(phoneNumber);
		GenVerificationCodeResponse response = new GenVerificationCodeResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<VerificationResultResponse> getVerificationResult(@NotNull @Valid String phoneNumber,
			@NotNull @Valid String verificationCode) {
		VerificationCodeResponseData data = userInfoService.verify(phoneNumber, verificationCode);
		VerificationResultResponse response = new VerificationResultResponse();
		response.setData(data);
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SendSMSResponse> sendSMS(String token, @NotNull String phoneNumber) throws MvpMsUserException {
		SendSMSResponseData data = userInfoService.sendSMS(token, phoneNumber);
		SendSMSResponse response = new SendSMSResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}
	
}
