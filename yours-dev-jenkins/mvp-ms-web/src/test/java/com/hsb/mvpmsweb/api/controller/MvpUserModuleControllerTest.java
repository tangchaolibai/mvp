package com.hsb.mvpmsweb.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsb.mvpmsweb.api.MvpUserModuleApi;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.model.payload.request.CancelUserRequest;
import com.hsb.mvpmsweb.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsweb.model.payload.request.EditUserRequest;
import com.hsb.mvpmsweb.model.payload.request.LoginRequest;
import com.hsb.mvpmsweb.model.payload.request.RegisterRequest;
import com.hsb.mvpmsweb.model.payload.request.UserIdRequest;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.response.EditSystemSettingResponse;
import com.hsb.mvpmsweb.model.payload.response.EditUserResponse;
import com.hsb.mvpmsweb.model.payload.response.FindUserResponse;
import com.hsb.mvpmsweb.model.payload.response.LoginResponse;
import com.hsb.mvpmsweb.model.payload.response.RegisterResponse;
import com.hsb.mvpmsweb.model.payload.response.SearchUserResponse;
import com.hsb.mvpmsweb.service.UserInfoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
public class MvpUserModuleControllerTest {
	
	@InjectMocks
	private MvpUserModuleApi api = new MvpUserModuleController();

	@Mock
	UserInfoService userInfoService;

	@Test
	public void testLogin() throws MvpUserException, MvpWebException {
		@Valid
		LoginRequest loginRequest = null;
		ResponseEntity<LoginResponse> response = api.login(loginRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testRegister() throws MvpUserException, MvpWebException {
		@Valid
		RegisterRequest registerRequest = null;
		// registerRequest.areaCode("+86").mobilePhone("1381234123").password("123");
		ResponseEntity<RegisterResponse> response = api.register(registerRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testEditUser() throws MvpUserException {
		@Valid
		EditUserRequest editUserRequest = null;
		ResponseEntity<EditUserResponse> response = api.editUser(editUserRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testFindUser() throws MvpUserException {
		String userId = null;
		ResponseEntity<FindUserResponse> response = api.findUser(userId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}

	@Test
	public void testCancelUser() throws MvpUserException {
		@Valid
		CancelUserRequest cancelUserRequest = new CancelUserRequest();
		ResponseEntity<BaseResponse> response = api.cancelUser(cancelUserRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testLogOutUser() throws MvpUserException {
		UserIdRequest userId = null;
		ResponseEntity<BaseResponse> response = api.logOutUser(userId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testEditSystemSetting() throws MvpUserException {
		@Valid
		EditSystemSettingRequest editSystemSettingRequest = null;
		ResponseEntity<EditSystemSettingResponse> response = api.editSystemSetting(editSystemSettingRequest);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testGetSystemSetting() throws MvpUserException {
		@Valid
		Integer userId = null;
		ResponseEntity<EditSystemSettingResponse> response = api.getSystemSetting(userId);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}
	
	@Test
	public void testGetUserList() throws MvpUserException {
		@NotNull
		@Valid
		String assistantMsg = null;
		ResponseEntity<SearchUserResponse> response = api.getUserList(assistantMsg);
		Assertions.assertEquals("SUCCESS", response.getBody().getResult().getCode());
		Assertions.assertEquals("Request processed successfully.", response.getBody().getResult().getMessage());
	}
}
