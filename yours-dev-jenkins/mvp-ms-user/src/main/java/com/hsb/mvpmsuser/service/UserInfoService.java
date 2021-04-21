package com.hsb.mvpmsuser.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsb.mvpmsuser.exception.MvpMsUserException;
import com.hsb.mvpmsuser.model.domain.UserInfo;
import com.hsb.mvpmsuser.model.payload.LoginResponseData;
import com.hsb.mvpmsuser.model.payload.SearchUserResponseData;
import com.hsb.mvpmsuser.model.payload.SendSMSResponseData;
import com.hsb.mvpmsuser.model.payload.SystemSettingResponseData;
import com.hsb.mvpmsuser.model.payload.UserInfoResponseData;
import com.hsb.mvpmsuser.model.payload.VerificationCodeResponseData;
import com.hsb.mvpmsuser.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsuser.model.payload.request.EditUserRequest;
import com.hsb.mvpmsuser.model.payload.request.LoginRequest;
import com.hsb.mvpmsuser.model.payload.request.RegisterRequest;

public interface UserInfoService extends IService<UserInfo> {
	
	UserInfoResponseData registerUser(RegisterRequest registerRequest) throws MvpMsUserException;
	LoginResponseData loginUser(LoginRequest loginRequest) throws MvpMsUserException;
	UserInfoResponseData editUser(String entityId, @Valid EditUserRequest editUserRequest) throws MvpMsUserException;
	UserInfoResponseData findUser(String entityId) throws MvpMsUserException;
	void closeAccount(String entityId) throws MvpMsUserException;
	void logOut(String entityId) throws MvpMsUserException;
	SystemSettingResponseData editSystemSetting(EditSystemSettingRequest editSystemSettingRequest);
	SystemSettingResponseData getSystemSetting(Integer userId);
	SystemSettingResponseData initSystemSetting(Integer userId);
	List<SearchUserResponseData> getUserList(String assistantMsg);
	VerificationCodeResponseData genVerificationCode(@NotNull @Valid String phoneNumber);
	VerificationCodeResponseData verify(@NotNull @Valid String phoneNumber, @NotNull @Valid String verificationCode);
	SendSMSResponseData sendSMS(String token, String phoneNumber) throws MvpMsUserException;
	
}
