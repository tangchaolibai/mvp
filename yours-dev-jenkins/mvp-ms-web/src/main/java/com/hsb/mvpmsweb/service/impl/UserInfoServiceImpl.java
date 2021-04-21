package com.hsb.mvpmsweb.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsb.mvpmsweb.api.exception.MvpUserException;
import com.hsb.mvpmsweb.api.exception.MvpUserExceptionCode;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.api.exception.MvpWebExceptionCode;
import com.hsb.mvpmsweb.mapper.SystemSettingMapper;
import com.hsb.mvpmsweb.mapper.UserInfoMapper;
import com.hsb.mvpmsweb.model.domain.SystemSetting;
import com.hsb.mvpmsweb.model.domain.UserInfo;
import com.hsb.mvpmsweb.model.payload.*;
import com.hsb.mvpmsweb.model.payload.request.*;
import com.hsb.mvpmsweb.service.UserInfoService;
import com.hsb.mvpmsweb.util.JwtTokenUtil;
import com.hsb.mvpmsweb.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SystemSettingMapper systemSettingMapper;

	@Value("${jwt.base64Secret}")
	private String base64Secret;

	@Value("${jwt.expiresSecond}")
	private int expiresSecond;

	@Value("${aes.password.secretKey}")
	private String pwdSecretKey;

	@Value("${jwt.token-prefix}")
    private String tokenPrefix;

	@Override
	public LoginResponseData registerUser(RegisterRequest registerRequest) throws MvpUserException {
		log.info("Register User Start...");
		//String password = AesUtil.aesDecryptString(registerRequest.password(),pwdSecretKey);
		String password = registerRequest.password();
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("MOBILE_PHONE", registerRequest.mobilePhone());
		UserInfo userExisted = userInfoMapper.selectOne(queryWrapper);
		if (userExisted == null) {
			UserInfo user = new UserInfo().mobilePhone(registerRequest.mobilePhone()).password(SecureUtil.md5(password))
					.nickName(genRandomNickName()).userRole(0).areaCode(registerRequest.areaCode());
			userInfoMapper.insert(user);
			initSystemSetting(user.entityId());
			log.info("Register User End.");
			LoginRequest loginRequest = new LoginRequest().areaCode(registerRequest.areaCode())
					.mobilePhone(registerRequest.mobilePhone()).password(registerRequest.password());
			return loginUser(loginRequest);
		} else {
			log.info("Register User Exception.");
			throw new MvpUserException(MvpUserExceptionCode.ERROR_002_01, new String[] {registerRequest.mobilePhone()});
		}
	}

	@Override
	public LoginResponseData loginUser(LoginRequest loginRequest) throws MvpUserException {
		log.info("Login User Start...");
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("MOBILE_PHONE", loginRequest.mobilePhone());
		//.eq("AREA_CODE", loginRequest.areaCode());
		UserInfo user = userInfoMapper.selectOne(queryWrapper);
		if (user != null) {
			//String loginPwd = AesUtil.aesDecryptString(loginRequest.password(), pwdSecretKey);
			String loginPwd = loginRequest.password();
			if (user.logedOff().booleanValue()) {
				throw new MvpUserException(MvpUserExceptionCode.ERROR_001_03);
			}
			if (user.password().equals(SecureUtil.md5(loginPwd))) {
				String token = generateToken(String.valueOf(user.entityId()));
				return new LoginResponseData().token(token).entityId(user.entityId()).userRole(user.userRole())
						.mobilePhone(user.mobilePhone()).areaCode(user.areaCode()).userName(user.userName())
						.nickName(user.nickName()).email(user.email()).introduction(user.introduction())
						.gender(user.gender()).location(user.location()).followerCount(user.followerCount())
						.fanCount(user.fanCount()).userImgPath(user.userImgPath()).loginId(user.loginId())
						.creationDateTime(user.creationDateTime())
						.lastUpdateDateTime(user.lastUpdateDateTime()).dateOfBirth(user.dateOfBirth()).shopId(user.shopId())
						.coverImgPath(user.coverImgPath());
			} else {
				throw new MvpUserException(MvpUserExceptionCode.ERROR_001_02);
			}
		} else {
			throw new MvpUserException(MvpUserExceptionCode.ERROR_001_01);
		}
	}

	@Override
	public UserInfoResponseData editUser(@Valid EditUserRequest editUserRequest) throws MvpUserException {
		UserInfo userInfo = userInfoMapper.selectById(editUserRequest.userId());
		if (userInfo == null)
			throw new MvpUserException(MvpWebExceptionCode.ERROR_998_11);
		if(StrUtil.isBlank(editUserRequest.nickName()))
			throw new MvpUserException(MvpUserExceptionCode.ERROR_003_01);
		
		userInfo.nickName(editUserRequest.nickName()).introduction(editUserRequest.introduction())
			.gender(editUserRequest.gender()).location(editUserRequest.location())
			.dateOfBirth(editUserRequest.dateOfBirth()).userImgPath(editUserRequest.userImgPath())
			.coverImgPath(editUserRequest.coverImgPath());
		userInfoMapper.updateById(userInfo);
		
		return toUserInfoResponseData(userInfo);
	}

	@Override
	public UserInfoResponseData findUser(String entityId) throws MvpUserException {
		UserInfo userInfo = userInfoMapper.selectById(entityId);
		if (userInfo != null) {
			return toUserInfoResponseData(userInfo);
		} else {
			throw new MvpUserException(MvpWebExceptionCode.ERROR_998_11);
		}
	}

	@Override
	public void cancelUser(Integer userId) throws MvpUserException {
		UserInfo userInfo = userInfoMapper.selectById(userId);
		if (userInfo != null) {
			if (userInfo.logedOff().booleanValue()) {
				throw new MvpUserException(MvpUserExceptionCode.ERROR_005_01);
			}
			userInfoMapper.updateLoggedOffFlag(userId);
		} else {
			throw new MvpUserException(MvpWebExceptionCode.ERROR_998_11);
		}
	}

	@Override
	public void logOut(Integer entityId) throws MvpUserException {
		UserInfo userInfo = userInfoMapper.selectById(entityId);
		if (userInfo != null) {
			String token = (String) redisUtils.get(String.valueOf(entityId));
			if (!StrUtil.isEmpty(token)) {
				redisUtils.del(String.valueOf(entityId));
			} else {
				throw new MvpUserException(MvpUserExceptionCode.ERROR_006_01);
			}
		} else {
			throw new MvpUserException(MvpWebExceptionCode.ERROR_998_11);
		}
	}

	private String generateToken(String userId) throws MvpUserException {
		String token = null;
		try {
			token = JwtTokenUtil.createJWT(userId, base64Secret, expiresSecond);
		} catch (MvpWebException e) {
			throw new MvpUserException(e.getCode());
		}
		redisUtils.set(userId, token);
		return token;
	}

	private UserInfoResponseData toUserInfoResponseData(UserInfo user) {
		UserInfoResponseData data = null;
		if (user != null) {
			data = new UserInfoResponseData().entityId(user.entityId()).userRole(user.userRole())
					.mobilePhone(user.mobilePhone()).areaCode(user.areaCode()).userName(user.userName())
					.nickName(user.nickName()).email(user.email()).introduction(user.introduction())
					.gender(user.gender()).location(user.location()).followerCount(user.followerCount())
					.fanCount(user.fanCount()).userImgPath(user.userImgPath()).loginId(user.loginId())
					.creationDateTime(user.creationDateTime())
					.lastUpdateDateTime(user.lastUpdateDateTime()).dateOfBirth(user.dateOfBirth()).shopId(user.shopId())
					.coverImgPath(user.coverImgPath());
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
		SystemSetting systemSetting = systemSettingMapper.selectOne(settingWrapper);
		systemSetting.userId(editSystemSettingRequest.userId())
				.privateAccount(editSystemSettingRequest.privateAccount() == null
						|| "".equals(editSystemSettingRequest.privateAccount()) ? systemSetting.privateAccount()
								: editSystemSettingRequest.privateAccount())
				.searchAllow(editSystemSettingRequest.searchAllow() == null
						|| "".equals(editSystemSettingRequest.searchAllow()) ? systemSetting.searchAllow()
								: editSystemSettingRequest.searchAllow())
				.onlineShow(editSystemSettingRequest.onlineShow() == null
						|| "".equals(editSystemSettingRequest.onlineShow()) ? systemSetting.onlineShow()
								: editSystemSettingRequest.onlineShow())
				.feedbackHelp(editSystemSettingRequest.feedbackHelp() == null
						|| "".equals(editSystemSettingRequest.feedbackHelp()) ? systemSetting.feedbackHelp()
								: editSystemSettingRequest.feedbackHelp())
				.language(editSystemSettingRequest.language() == null || "".equals(editSystemSettingRequest.language())
						? systemSetting.language()
						: editSystemSettingRequest.language());
		systemSettingMapper.update(systemSetting, settingWrapper);
		SystemSettingResponseData systemSettingResponseData = new SystemSettingResponseData();
		systemSettingResponseData.userId(systemSetting.userId()).privateAccount(systemSetting.privateAccount())
				.searchAllow(systemSetting.searchAllow()).onlineShow(systemSetting.onlineShow())
				.feedbackHelp(systemSetting.feedbackHelp()).language(systemSetting.language())
				.creationDateTime(systemSetting.creationDateTime())
				.lastUpdateDateTime(systemSetting.lastUpdateDateTime());
		return systemSettingResponseData;
	}

	@Override
	public SystemSettingResponseData getSystemSetting(Integer userId) throws MvpUserException {
		QueryWrapper<SystemSetting> settingWrapper = new QueryWrapper<>();
		settingWrapper.eq("USER_ID", userId);
		SystemSetting systemSetting = systemSettingMapper.selectOne(settingWrapper);
		if(systemSetting != null) {
			SystemSettingResponseData systemSettingResponseData = new SystemSettingResponseData();
			systemSettingResponseData.entityId(systemSetting.entityId()).userId(systemSetting.userId()).privateAccount(systemSetting.privateAccount())
					.searchAllow(systemSetting.searchAllow()).onlineShow(systemSetting.onlineShow())
					.feedbackHelp(systemSetting.feedbackHelp()).language(systemSetting.language())
					.creationDateTime(systemSetting.creationDateTime())
					.lastUpdateDateTime(systemSetting.lastUpdateDateTime());
			return systemSettingResponseData;
		}else {
			throw new MvpUserException(MvpUserExceptionCode.ERROR_001_01);
		}
	}

	/**
	 * init users by systemSetting
	 */
	private void initSystemSetting(Integer userId) {
		SystemSetting systemSetting = new SystemSetting();
		systemSetting.userId(userId).privateAccount("N").searchAllow("Y").onlineShow("N").feedbackHelp("")
				.language("English");
		systemSettingMapper.insert(systemSetting);
		SystemSettingResponseData systemSettingResponseData = new SystemSettingResponseData();
		systemSettingResponseData.userId(systemSetting.userId()).privateAccount(systemSetting.privateAccount())
				.searchAllow(systemSetting.searchAllow()).onlineShow(systemSetting.onlineShow())
				.feedbackHelp(systemSetting.feedbackHelp()).language(systemSetting.language())
				.creationDateTime(systemSetting.creationDateTime())
				.lastUpdateDateTime(systemSetting.lastUpdateDateTime());
	}

	/**
	 * find users by assistantMsg
	 */
	@Override
	public List<SearchUserResponseData> getUserList(String assistantMsg) {
		log.info("Start search users data by nickname...");
		List<UserInfo> list = userInfoMapper.selectList(new QueryWrapper<UserInfo>().like("nick_name", assistantMsg));
		List<SearchUserResponseData> listData = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(list)){
            for (UserInfo userInfo : list) {
                SearchUserResponseData data = new SearchUserResponseData().userId(userInfo.entityId())
                        .nickName(userInfo.nickName()).followerCount(userInfo.followerCount())
                        .userImage(userInfo.userImgPath());
                listData.add(data);
            }
        }
		log.info("Search users end.");
		return listData;
	}

	private SendSMSResponseData toSendSMSResponse(String token, String phoneNumber) throws MvpUserException {
		SendSMSResponseData data = new SendSMSResponseData();
		UserInfo inviter = null;
		try {
			inviter = userInfoMapper.selectById(JwtTokenUtil.getUserId(token, base64Secret));
		} catch (MvpWebException e) {
			log.error("Send SMS failed: [{}]", e.getMessage());
			throw new MvpUserException(e.getCode());
		}

		if(inviter != null) {
			data.phoneNumber(phoneNumber);
			data.inviter(inviter.nickName());
			data.SMS("[" + inviter.nickName() + "(" + inviter.mobilePhone() + ")] 邀请您使用'yours'!");
		}
		return data;
	}

	@Override
	public OTPResponseData generateOtp(@Valid String phoneNumber) {
		OTPResponseData data = new OTPResponseData();
		data.setPhoneNumber(phoneNumber);
		//TODO generate code
		data.setVerificationCode("123456");
		return data;
	}

	@Override
	public OTPResponseData verifyOtp(@Valid VerifyOtpRequest verifyOtpRequest) {
		// TODO verify
		OTPResponseData data = new OTPResponseData();
		data.setPhoneNumber(verifyOtpRequest.phoneNumber());
		data.setResult("Success");
		return data;
	}

	@Override
	public SendSMSResponseData sendSMS(String token, String phoneNumber) throws MvpUserException {
		log.info("Starting to send SMS...");
		SendSMSResponseData result = new SendSMSResponseData();
		token = StringUtils.remove(token, tokenPrefix);
		result = toSendSMSResponse(token, phoneNumber);
		log.info("Send SMS Done!");
		return result;
	}

	@Override
	public ValidateRegisterResponseData validateRegister(ValidateRegisterRequest request) throws MvpUserException {
		ValidateRegisterResponseData result = new ValidateRegisterResponseData();
		Integer userNum = userInfoMapper.selectCount(new QueryWrapper<UserInfo>().eq("MOBILE_PHONE", request.getMobilePhone()));
		if(userNum > 0) {
			log.info("Register fail, phone [{}] has been registered!", request.getMobilePhone());
			throw new MvpUserException(MvpUserExceptionCode.ERROR_002_01, new String[] { request.getMobilePhone() });
		}
		result.setIsRegister(userNum > 0);
		return result;
	}

	@Override
	public void forgetPassword(@Valid ForgetPasswordRequest forgetPassword) throws MvpUserException {
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("MOBILE_PHONE", forgetPassword.getMobilePhone());
		UserInfo user = userInfoMapper.selectOne(wrapper);
		if(user == null) {
			log.error("The account does not exist.");
			throw new MvpUserException(MvpWebExceptionCode.ERROR_998_11);
		}
		
		if(user.password().equals(SecureUtil.md5(forgetPassword.getPassword()))) {
			log.error("The new password cannot be the same as the old one");
			throw new MvpUserException(MvpUserExceptionCode.ERROR_007_01);
		}
		user.password(SecureUtil.md5(forgetPassword.getPassword()));
		userInfoMapper.updateById(user);
	}

}
