package com.hsb.mvpmsweb.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.api.exception.MvpUserExceptionCode;
import com.hsb.mvpmsweb.mapper.SystemSettingMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.model.domain.SystemSetting;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.payload.SearchUserResponseData;
import com.hsb.mvpmsweb.model.payload.SystemSettingResponseData;
import com.hsb.mvpmsweb.model.payload.request.EditSystemSettingRequest;
import com.hsb.mvpmsweb.model.payload.request.EditUserRequest;
import com.hsb.mvpmsweb.model.payload.request.LoginRequest;
import com.hsb.mvpmsweb.model.payload.request.RegisterRequest;
import com.hsb.mvpmsweb.service.impl.UserInfoServiceImpl;
import com.hsb.mvpmsweb.util.RedisUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(Alphanumeric.class)
@DisplayName("UserInfoServiceTestCase")
@SuppressWarnings("unchecked")
public class UserInfoServiceTest {

	@InjectMocks
	private UserInfoService userInfoService = new UserInfoServiceImpl();

	@Mock
	private UserInfoMapper userInfoMapper;

	@Mock
	private RedisUtils redisUtils;

	@Mock
	private SystemSettingMapper systemSettingMapper;

	/**
	 * normal:No exception
	 * 
	 * @throws MvpUserException
	 */
	@DisplayName("testRegisterUser1")
	@Tag("RegistUser")
	@Test
	public void testRegisterUser1() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.areaCode("86");
		registerRequest.mobilePhone("15111111115");
		registerRequest.password("123456");
		when(userInfoMapper.selectOne(isA(QueryWrapper.class))).thenReturn(null);
		when(systemSettingMapper.insert(isA(SystemSetting.class))).thenReturn(1);
		Assertions.assertDoesNotThrow(() -> {
			userInfoService.registerUser(registerRequest);
			// Assertions.assertEquals(68, data.entityId());
		});
	}

	/**
	 * User already exists
	 */
	@DisplayName("testRegisterUser2")
	@Tag("RegistUser")
	@Test
	public void testRegisterUser2() {
		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.areaCode("86");
		registerRequest.mobilePhone("15111111111");
		registerRequest.password("123456");
		when(userInfoMapper.selectOne(isA(QueryWrapper.class))).thenReturn(new UserInfo());

		MvpUserException exception = Assertions.assertThrows(MvpUserException.class, () -> {
			userInfoService.registerUser(registerRequest);
		});
		assertAll(() -> assertEquals("ERR-01-002-01", exception.getCode()),
				() -> assertEquals("", exception.getMessage()));
		/*
		 * try { userInfoService.registerUser(registerRequest); } catch
		 * (MvpUserException exception) { Assertions.assertEquals("ERR-01-002-01",
		 * exception.getCode()); } Assertions.assertEquals("ERR-01-002-01",
		 * exception.getCode());
		 */
	}

	/**
	 * normal:No exception
	 * 
	 * @throws MvpUserException
	 */
	@DisplayName("testLoginUser1")
	@Tag("LoginUser")
	@Test
	public void testLoginUser1() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.areaCode("86");
		loginRequest.mobilePhone("17620452095");
		loginRequest.password("123456");
		Assertions.assertDoesNotThrow(() -> {
			userInfoService.loginUser(loginRequest);
		});
	}

	/**
	 * no user
	 */
	@DisplayName("testLoginUser2")
	@Tag("LoginUser")
	@Test
	public void testLoginUser2() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.areaCode("86");
		loginRequest.mobilePhone("18333333333");
		loginRequest.password("123");
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class, () -> {
			userInfoService.loginUser(loginRequest);
		});
		assertAll(() -> assertEquals("ERR-01-001-01", exception.getCode()),
				() -> assertEquals("", exception.getMessage()));
	}

	/**
	 * The user has been logged out
	 */
	@DisplayName("testLoginUser3")
	@Tag("LoginUser")
	@Test
	public void testLoginUser3() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.areaCode("86");
		loginRequest.mobilePhone("18312312312");
		loginRequest.password("123");
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class, () -> {
			userInfoService.loginUser(loginRequest);
		});
		assertAll(() -> assertEquals("ERR-01-001-03", exception.getCode()),
				() -> assertEquals("", exception.getMessage()));
	}

	/**
	 * wrong password
	 */
	@DisplayName("testLogin4")
	@Tag("LoginUser")
	@Test
	public void testLoginUser4() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.areaCode("86");
		loginRequest.mobilePhone("17620452095");
		loginRequest.password("123");
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class, () -> {
			userInfoService.loginUser(loginRequest);
		});
		assertAll(() -> assertEquals("ERR-01-001-02", exception.getCode()),
				() -> assertEquals("", exception.getMessage()));
	}

	/**
	 * normal:no exception
	 * 
	 * @throws MvpUserException
	 */
	@DisplayName("testEditUser1")
	@Tag("EditUser")
	@Test
	public void testEditUser1() {
		EditUserRequest editUserRequest = new EditUserRequest();
		editUserRequest.userId(40).nickName("junitTestEdit").introduction("junit test edit user").gender(0)
				.location("HongKong.China").dateOfBirth(LocalDate.now());
		Assertions.assertDoesNotThrow(() -> userInfoService.editUser(editUserRequest));
	}

	/**
	 * no user
	 */
	@DisplayName("testEditUser2")
	@Tag("EditUser")
	@Test
	public void testEditUser2() {
		EditUserRequest editUserRequest = new EditUserRequest();
		editUserRequest.userId(1).nickName("junitTestEdit").introduction("junit test edit user").gender(0)
				.location("HongKong.China").dateOfBirth(LocalDate.now());

		MvpUserException exception = Assertions.assertThrows(MvpUserException.class,
				() -> userInfoService.editUser(editUserRequest));
		assertAll(() -> assertEquals("ERR-01-998-11", exception.getCode()),
				() -> assertEquals("", exception.getMessage()));
	}

	/**
	 * nomal:no exception
	 */
	@DisplayName("testFindUser1")
	@Tag("FindUser")
	@Test
	public void testFindUser1() {
		String entityId = "40";
		assertDoesNotThrow(() -> userInfoService.findUser(entityId));
		// Assertions.assertEquals("17212345678@163.com", response.email());
	}

	/**
	 * no user
	 */
	@DisplayName("testFindUser2")
	@Tag("FindUser")
	@Test
	public void testFindUser2() {
		String entityId = "134564";
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class,
				() -> userInfoService.findUser(entityId));
		assertAll(() -> assertEquals("ERR-01-998-11", exception.getCode()),
				() -> assertEquals("", exception.getLocalizedMessage()));
	}

	@DisplayName("getUserList")
	@Tag("getUserList")
	@Test
	public void testGetUserList() throws MvpUserException {
		// Database has records
		String assistantMsg = "junitTestEdit";
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(new ArrayList<UserInfo>());
		List<SearchUserResponseData> list = userInfoService.getUserList(assistantMsg);
		Assertions.assertEquals(40, list.get(0).userId());
		// No records in the database
		when(userInfoMapper.selectList(isA(QueryWrapper.class))).thenReturn(new ArrayList<UserInfo>());
		String assistantMsg2 = "haha";
		List<SearchUserResponseData> list2 = userInfoService.getUserList(assistantMsg2);
		Assertions.assertEquals(0, list2.size());
	}

	/**
	 * User doesn't exist. User has logged off. Normal case.
	 * 
	 * @throws MvpUserException
	 */
	@Test
	public void testCancelUser() throws MvpUserException {
		// User doesn't exist.
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(null);
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class,
				() -> userInfoService.cancelUser(10000));
		Assertions.assertTrue(exception.getCode().equals(MvpUserExceptionCode.ERROR_998_11));
		// User has logged off.
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(new UserInfo().logedOff(true));
		exception = Assertions.assertThrows(MvpUserException.class, () -> userInfoService.cancelUser(1000));
		Assertions.assertTrue(exception.getCode().equals(MvpUserExceptionCode.ERROR_005_01));
		// Normal case.
		when(userInfoMapper.selectById(isA(Integer.class))).thenReturn(new UserInfo().logedOff(false));
		userInfoService.cancelUser(100);
		verify(userInfoMapper, times(1)).updateLoggedOffFlag(isA(Integer.class));
	}

	@Test
	public void testLogOut() throws MvpUserException {
		// User doesn't exist.
		when(userInfoMapper.selectById(isA(String.class))).thenReturn(null);
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class,
				() -> userInfoService.logOut(10000));
		Assertions.assertTrue(exception.getCode().equals(MvpUserExceptionCode.ERROR_998_11));
		// User isn't online.
		when(userInfoMapper.selectById(isA(String.class))).thenReturn(new UserInfo());
		when(redisUtils.get(isA(String.class))).thenReturn(null);
		exception = Assertions.assertThrows(MvpUserException.class, () -> userInfoService.logOut(10000));
		Assertions.assertTrue(exception.getCode().equals(MvpUserExceptionCode.ERROR_006_01));
		// normal case
		when(redisUtils.get(isA(String.class))).thenReturn("aaa");
		userInfoService.logOut(10000);
		verify(redisUtils, times(1)).del(isA(String.class));
	}

	/**
	 * edit user's system params
	 */
	@DisplayName("testEditSystemSetting")
	@Tag("SystemSetting")
	@Test
	public void testEditSystemSetting() {
		EditSystemSettingRequest editSystemSettingRequest = new EditSystemSettingRequest();
		editSystemSettingRequest.userId(62).language("Japanese").onlineShow(null);
		Assertions.assertDoesNotThrow(() -> {
			SystemSettingResponseData response = userInfoService.editSystemSetting(editSystemSettingRequest);
			assertAll(() -> {
				assertEquals("N", response.onlineShow());
				assertEquals("Japanese", response.language());
			});
		});
	}

	/**
	 * get user's system params
	 */
	@DisplayName("testEditSystemSetting")
	@Tag("SystemSetting")
	@Test
	public void testGetSystemSetting() {
		Integer userId = 62;
		Assertions.assertDoesNotThrow(() -> {
			SystemSettingResponseData response = userInfoService.getSystemSetting(userId);
			assertAll(() -> {
				assertEquals("N", response.onlineShow());
				assertEquals("Japanese", response.language());
			});
		});
		// not found
		Integer userId2 = 10000;
		MvpUserException exception = Assertions.assertThrows(MvpUserException.class, () -> {
			userInfoService.getSystemSetting(userId2);
		});
		assertAll(() -> {
			assertEquals(MvpUserExceptionCode.ERROR_001_01, exception.getCode());
			assertEquals("", exception.getMessage());
		});
	}
}
