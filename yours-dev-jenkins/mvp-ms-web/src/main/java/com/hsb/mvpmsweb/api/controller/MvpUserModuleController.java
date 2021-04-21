package com.hsb.mvpmsweb.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.hsb.mvpmsweb.api.MvpUserModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.model.payload.LoginResponseData;
import com.hsb.mvpmsweb.model.payload.OTPResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.SearchUserResponseData;
import com.hsb.mvpmsweb.model.payload.SendSMSResponseData;
import com.hsb.mvpmsweb.model.payload.SystemSettingResponseData;
import com.hsb.mvpmsweb.model.payload.UserInfoResponseData;
import com.hsb.mvpmsweb.model.payload.ValidateRegisterResponseData;
import com.hsb.mvpmsweb.model.payload.request.CancelUserRequest;
import com.hsb.mvpmsweb.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsweb.model.payload.request.EditUserRequest;
import com.hsb.mvpmsweb.model.payload.request.ForgetPasswordRequest;
import com.hsb.mvpmsweb.model.payload.request.LoginRequest;
import com.hsb.mvpmsweb.model.payload.request.RegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.UserIdRequest;
import com.hsb.mvpmsweb.model.payload.request.ValidateRegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.VerifyOtpRequest;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.response.EditSystemSettingResponse;
import com.hsb.mvpmsweb.model.payload.response.EditUserResponse;
import com.hsb.mvpmsweb.model.payload.response.FindUserResponse;
import com.hsb.mvpmsweb.model.payload.response.LoginResponse;
import com.hsb.mvpmsweb.model.payload.response.OTPResponse;
import com.hsb.mvpmsweb.model.payload.response.RegisterResponse;
import com.hsb.mvpmsweb.model.payload.response.SearchUserResponse;
import com.hsb.mvpmsweb.model.payload.response.SendSMSResponse;
import com.hsb.mvpmsweb.model.payload.response.ValidateRegisterResponse;
import com.hsb.mvpmsweb.service.UserInfoService;

@RestController
@Validated
public class MvpUserModuleController implements MvpUserModuleApi {

	@Autowired
	private UserInfoService userInfoService;

	@Override
	public ResponseEntity<LoginResponse> login(@Valid LoginRequest loginRequest) throws MvpUserException {
		LoginResponseData data = userInfoService.loginUser(loginRequest);
		LoginResponse response = new LoginResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<RegisterResponse> register(@Valid RegisterRequest registerRequest) throws MvpUserException {
		LoginResponseData data = userInfoService.registerUser(registerRequest);
		RegisterResponse response = new RegisterResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EditUserResponse> editUser(@Valid EditUserRequest editUserRequest) throws MvpUserException {
		UserInfoResponseData data = userInfoService.editUser(editUserRequest);
		EditUserResponse response = new EditUserResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FindUserResponse> findUser(String entityId) throws MvpUserException {
		UserInfoResponseData data = userInfoService.findUser(entityId);
		FindUserResponse response = new FindUserResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> cancelUser(@Valid CancelUserRequest cancelUserRequest) throws MvpUserException {
		userInfoService.cancelUser(cancelUserRequest.userId());
		BaseResponse response = new BaseResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> logOutUser(UserIdRequest request) throws MvpUserException {
		userInfoService.logOut(request.userId());
		BaseResponse response = new BaseResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EditSystemSettingResponse> editSystemSetting(
			@Valid EditSystemSettingRequest editSystemSettingRequest) {
		SystemSettingResponseData data = userInfoService.editSystemSetting(editSystemSettingRequest);
		EditSystemSettingResponse response = new EditSystemSettingResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EditSystemSettingResponse> getSystemSetting(Integer userId) throws MvpUserException {
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
	public ResponseEntity<OTPResponse> generateOtp(@Valid String phoneNumber) {
		OTPResponseData data = userInfoService.generateOtp(phoneNumber);
		OTPResponse response = new OTPResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<OTPResponse> verifyOtp(@Valid VerifyOtpRequest verifyOtpRequest) {
		OTPResponseData data = userInfoService.verifyOtp(verifyOtpRequest);
		OTPResponse response = new OTPResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SendSMSResponse> sendSMS(String token, @NotNull String phoneNumber) throws MvpUserException {
		SendSMSResponseData data = userInfoService.sendSMS(token, phoneNumber);
		SendSMSResponse response = new SendSMSResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ValidateRegisterResponse> validateRegister(@Valid ValidateRegisterRequest request) throws MvpUserException {
		ValidateRegisterResponseData data = userInfoService.validateRegister(request);
		ValidateRegisterResponse response = new ValidateRegisterResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> forgetPassword(@Valid ForgetPasswordRequest forgetPassword) throws MvpUserException {
		userInfoService.forgetPassword(forgetPassword);
		BaseResponse response = new BaseResponse();
		response.setResult(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

}
