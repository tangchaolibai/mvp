package com.hsb.mvpmsuser.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsuser.exception.MvpMsUserException;
import com.hsb.mvpmsuser.exception.MvpMsUserExceptionCode;
import com.hsb.mvpmsuser.mapper.SystemSettingMapper;
import com.hsb.mvpmsuser.mapper.UserInfoMapper;
import com.hsb.mvpmsuser.model.domain.SystemSetting;
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
import com.hsb.mvpmsuser.service.UserInfoService;
import com.hsb.mvpmsuser.util.JwtTokenUtil;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
	private SystemSettingMapper systemSettingMapper;
    
    @Value("${jwt.base64Secret}")
	private String base64Secret;
	
	@Value("${jwt.expiresSecond}")
	private int expiresSecond;
	
	@Value("${jwt.token-prefix}")
    private String tokenPrefix;
	
	@Override
	public UserInfoResponseData registerUser(RegisterRequest registerRequest) throws MvpMsUserException {
		logger.info("Register User Start");
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("MOBILE_PHONE", registerRequest.mobilePhone());
		UserInfo userExisted = getOne(queryWrapper);
		if (userExisted == null) {
			UserInfo user = new UserInfo()
					.mobilePhone(registerRequest.mobilePhone())
					.password(SecureUtil.md5(registerRequest.password()))
					.nickName(genRandomNickName())
					.userRole(0).areaCode(registerRequest.areaCode());
			save(user);
			initSystemSetting(user.entityId());
			logger.info("Register User End");
			return toUserInfoResponseData(user);
		} else {
			logger.info("Register User Exception");
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_002_01);
		}
	}

	@Override
	public LoginResponseData loginUser(LoginRequest loginRequest) throws MvpMsUserException {
		logger.info("Login User Start");
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("MOBILE_PHONE", loginRequest.mobilePhone()).eq("AREA_CODE", loginRequest.areaCode());
		UserInfo user = getOne(queryWrapper);
		if(user != null) {
			if(user.logedOff().booleanValue()) {
				throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_001_03);
			}
			if (user.password().equals(SecureUtil.md5(loginRequest.password()))) {
				String token = generateToken(String.valueOf(user.entityId()));
				return new LoginResponseData().token(token).userInfoResponseData(toUserInfoResponseData(user));
			} else {
				throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_001_02);
			}
		} else {
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_001_01);
		}
	}
	
	@Override
	public UserInfoResponseData editUser(String entityId, @Valid EditUserRequest editUserRequest) throws MvpMsUserException {
		UserInfo userInfo = getById(entityId);
		if(userInfo != null) {
			userInfo.nickName(editUserRequest.nickName())
				.introduction(editUserRequest.introduction())
				.gender(editUserRequest.gender())
				.location(editUserRequest.location())
				.dateOfBirth(editUserRequest.dateOfBirth());
			updateById(userInfo);
			return toUserInfoResponseData(userInfo);
		} else {
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_998_11);
		}
	}

	@Override
	public UserInfoResponseData findUser(String entityId) throws MvpMsUserException {
		UserInfo userInfo = getById(entityId);
		if(userInfo != null) {
			return toUserInfoResponseData(userInfo);
		} else {
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_998_11);
		}
	}

	@Override
	public void closeAccount(String entityId) throws MvpMsUserException {
		UserInfo userInfo = getById(entityId);
		if(userInfo != null) {
			if(userInfo.logedOff().booleanValue()) {
				throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_005_01);
			}
			userInfoMapper.updateLoggedOffFlag(entityId);
		} else {
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_998_11);
		}
	}

	@Override
	public void logOut(String entityId) throws MvpMsUserException {
		UserInfo userInfo = getById(entityId);
		if(userInfo != null) {
			String token = (String)redisTemplate.opsForValue().get(entityId);
			if(!StrUtil.isEmpty(token)) {
				redisTemplate.delete(entityId);
			} else {
				throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_006_01);
			}
		}  else {
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_998_11);
		}
	}

	private String generateToken(String userId) throws MvpMsUserException {
		String token = JwtTokenUtil.createJWT(userId, base64Secret, expiresSecond);
		redisTemplate.opsForValue().set(userId, token);
		return token;
	}

	private UserInfoResponseData toUserInfoResponseData(UserInfo user) {
		UserInfoResponseData data = null;
		if(user != null) {
			data = new UserInfoResponseData().entityId(user.entityId()).userRole(user.userRole()).mobilePhone(user.mobilePhone()).areaCode(user.areaCode())
					.userName(user.userName()).nickName(user.nickName()).email(user.email()).introduction(user.introduction()).gender(user.gender())
					.location(user.location()).followerCount(user.followerCount()).fanCount(user.fanCount()).userImgPath(user.userImgPath())
					.loginId(user.loginId()).password(user.password()).creationDateTime(user.creationDateTime())
					.lastUpdateDateTime(user.lastUpdateDateTime()).dateOfBirth(user.dateOfBirth());
		}
		return data;
	}
	
	private SendSMSResponseData toSendSMSResponse(String token, String phoneNumber) throws MvpMsUserException {
		SendSMSResponseData data = new SendSMSResponseData();
		UserInfo inviter = userInfoMapper.selectById(JwtTokenUtil.getUserId(token, base64Secret));
		if(inviter != null) {
			data.phoneNumber(phoneNumber);
			data.inviter(inviter.nickName());
			data.SMS("[" + inviter.nickName() + "("+ inviter.mobilePhone() +")] 邀请您使用'yours'系统!");
		}else {
			logger.info("Send SMS Failed!");
			throw new MvpMsUserException(MvpMsUserExceptionCode.ERROR_998_04);
		}
		return data;
	}

	private String genRandomNickName() {
		return RandomUtil.randomString(15);
	}
	
	@Override
	public SystemSettingResponseData editSystemSetting(EditSystemSettingRequest editSystemSettingRequest) {
		QueryWrapper<SystemSetting> settingWrapper = new QueryWrapper<>();
		settingWrapper.eq("USER_ID", editSystemSettingRequest.userId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SystemSetting systemSetting = systemSettingMapper.selectOne(settingWrapper);
		systemSetting.userId(editSystemSettingRequest.userId())
						.privateAccount(editSystemSettingRequest.privateAccount()==null||"".equals(editSystemSettingRequest.privateAccount())?systemSetting.privateAccount():editSystemSettingRequest.privateAccount())
						.searchAllow(editSystemSettingRequest.searchAllow()==null||"".equals(editSystemSettingRequest.searchAllow())?systemSetting.searchAllow():editSystemSettingRequest.searchAllow())
						.onlineShow(editSystemSettingRequest.onlineShow()==null||"".equals(editSystemSettingRequest.onlineShow())?systemSetting.onlineShow():editSystemSettingRequest.onlineShow())
						.feedbackHelp(editSystemSettingRequest.feedbackHelp()==null||"".equals(editSystemSettingRequest.feedbackHelp())?systemSetting.feedbackHelp():editSystemSettingRequest.feedbackHelp())
						.language(editSystemSettingRequest.language()==null||"".equals(editSystemSettingRequest.language())?systemSetting.language():editSystemSettingRequest.language())
						.lastUpdateDateTime(df.format(new Date()));
		systemSettingMapper.update(systemSetting,settingWrapper);
		SystemSettingResponseData systemSettingResponseData = new SystemSettingResponseData();
		systemSettingResponseData.userId(systemSetting.userId())
									.privateAccount(systemSetting.privateAccount())
									.searchAllow(systemSetting.searchAllow())
									.onlineShow(systemSetting.onlineShow())
									.feedbackHelp(systemSetting.feedbackHelp())
									.language(systemSetting.language())
									.creationDateTime(systemSetting.creationDateTime())
									.lastUpdateDateTime(systemSetting.lastUpdateDateTime());
		return systemSettingResponseData;
	}
	
	@Override
	public SystemSettingResponseData getSystemSetting(Integer userId) {
		QueryWrapper<SystemSetting> settingWrapper = new QueryWrapper<>();
		settingWrapper.eq("USER_ID", userId);
		SystemSetting systemSetting = systemSettingMapper.selectOne(settingWrapper);
		SystemSettingResponseData systemSettingResponseData = new SystemSettingResponseData();
		systemSettingResponseData.userId(systemSetting.userId())
									.privateAccount(systemSetting.privateAccount())
									.searchAllow(systemSetting.searchAllow())
									.onlineShow(systemSetting.onlineShow())
									.feedbackHelp(systemSetting.feedbackHelp())
									.language(systemSetting.language())
									.creationDateTime(systemSetting.creationDateTime())
									.lastUpdateDateTime(systemSetting.lastUpdateDateTime());
		return systemSettingResponseData;
	}
	
	/**
	 * init users by systemSetting
	 */
	@Override
	public SystemSettingResponseData initSystemSetting(Integer userId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SystemSetting systemSetting = new SystemSetting();
		systemSetting.userId(userId)
						.privateAccount("N")
						.searchAllow("Y")
						.onlineShow("N")
						.feedbackHelp("")
						.language("English")
						.creationDateTime(df.format(new Date()))
						.lastUpdateDateTime(df.format(new Date()));
		systemSettingMapper.insert(systemSetting);
		SystemSettingResponseData systemSettingResponseData = new SystemSettingResponseData();
		systemSettingResponseData.userId(systemSetting.userId())
									.privateAccount(systemSetting.privateAccount())
									.searchAllow(systemSetting.searchAllow())
									.onlineShow(systemSetting.onlineShow())
									.feedbackHelp(systemSetting.feedbackHelp())
									.language(systemSetting.language())
									.creationDateTime(systemSetting.creationDateTime())
									.lastUpdateDateTime(systemSetting.lastUpdateDateTime());
		return systemSettingResponseData;
	}
	
	/**
	 * find users by assistantMsg
	 */
	@Override
	public List<SearchUserResponseData> getUserList(String assistantMsg) {
		logger.info("start search users data by nickname");
		List<UserInfo> list = userInfoMapper.selectList(new QueryWrapper<UserInfo>().like("nick_name", assistantMsg));
		List<SearchUserResponseData> listData = null;
		if(CollectionUtil.isEmpty(list)) {
			//The database did not find a record that meets the conditions
			listData = new ArrayList<>();
		}else {
			listData = this.toSearchUserResponse(list);
		}
		logger.info("search users end" );
		return listData;
	}
	
	/**
	 * package userinfo list to response
	 * @param list
	 * @return response
	 */
	public List<SearchUserResponseData> toSearchUserResponse(List<UserInfo> list) {
		if(CollectionUtil.isEmpty(list)) {
			return null;
		}
		List<SearchUserResponseData> datas = new ArrayList<>();
		for (UserInfo userInfo : list) {
			SearchUserResponseData data = new SearchUserResponseData()
					.userId(userInfo.entityId()).nickName(userInfo.nickName())
					.followerCount(userInfo.followerCount()).userImage(userInfo.userImgPath());
			datas.add(data);
		}
		return datas;
	}

	@Override
	public VerificationCodeResponseData genVerificationCode(@NotNull @Valid String phoneNumber) {
		// TODO generate code logic
		return new VerificationCodeResponseData().phoneNumber(phoneNumber).verificationCode(RandomUtil.randomNumbers(6));
	}

	@Override
	public VerificationCodeResponseData verify(@NotNull @Valid String phoneNumber, @NotNull @Valid String verificationCode) {
		// TODO verify
		return new VerificationCodeResponseData().phoneNumber(phoneNumber).verificationCode(verificationCode).result(true);
	}

	@Override
	public SendSMSResponseData sendSMS(String token, String phoneNumber) throws MvpMsUserException {
		logger.info("Starting to send SMS...");
		SendSMSResponseData result = new SendSMSResponseData();
		token = StringUtils.remove(token, tokenPrefix);
		result = toSendSMSResponse(token, phoneNumber);
		logger.info("Send SMS Done!");
		return result;
	}

}
