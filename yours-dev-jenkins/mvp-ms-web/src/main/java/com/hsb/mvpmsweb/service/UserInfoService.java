package com.hsb.mvpmsweb.service;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.payload.LoginResponseData;
import com.hsb.mvpmsweb.model.payload.OTPResponseData;
import com.hsb.mvpmsweb.model.payload.SearchUserResponseData;
import com.hsb.mvpmsweb.model.payload.SendSMSResponseData;
import com.hsb.mvpmsweb.model.payload.SystemSettingResponseData;
import com.hsb.mvpmsweb.model.payload.UserInfoResponseData;
import com.hsb.mvpmsweb.model.payload.ValidateRegisterResponseData;
import com.hsb.mvpmsweb.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsweb.model.payload.request.EditUserRequest;
import com.hsb.mvpmsweb.model.payload.request.ForgetPasswordRequest;
import com.hsb.mvpmsweb.model.payload.request.LoginRequest;
import com.hsb.mvpmsweb.model.payload.request.RegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.ValidateRegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.VerifyOtpRequest;

public interface UserInfoService extends IService<UserInfo> {
	
	LoginResponseData registerUser(RegisterRequest registerRequest) throws MvpUserException;
	LoginResponseData loginUser(LoginRequest loginRequest) throws MvpUserException;
	UserInfoResponseData editUser(@Valid EditUserRequest editUserRequest) throws MvpUserException;
	UserInfoResponseData findUser(String entityId) throws MvpUserException;
	void cancelUser(Integer userId) throws MvpUserException;
	void logOut(Integer entityId) throws MvpUserException;
	SystemSettingResponseData editSystemSetting(EditSystemSettingRequest editSystemSettingRequest);
	SystemSettingResponseData getSystemSetting(Integer userId) throws MvpUserException;
	List<SearchUserResponseData> getUserList(String assistantMsg);
	OTPResponseData generateOtp(@Valid String phoneNumber);
	OTPResponseData verifyOtp(@Valid VerifyOtpRequest verifyOtpRequest);
	SendSMSResponseData sendSMS(String token, String phoneNumber) throws MvpUserException;
	ValidateRegisterResponseData validateRegister(ValidateRegisterRequest request) throws MvpUserException ;
	void forgetPassword(@Valid ForgetPasswordRequest forgetPassword) throws MvpUserException;
	
}
