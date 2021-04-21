package com.hsb.mvpmsweb.mapper;

import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsb.mvpmsweb.model.domain.UserInfo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

	@Update({"update USER_INFO set LOGED_OFF = true, LAST_UPDATE_DATE_TIME = now(), VERSION = VERSION + 1 where ENTITY_ID = #{entityId}"})
	void updateLoggedOffFlag(Integer userId);
	
}
