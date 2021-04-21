package com.hsb.mvpmsshare.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("USER_INFO")
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Integer entityId;
	
	@TableField(value = "USER_ROLE")
	private String userRole;
	
	@TableField(value = "MOBILE_PHONE")
	private String mobilePhone;
	
	@TableField(value = "AREA_CODE")
	private String areaCode;
	
	@TableField(value = "USER_NAME")
	private String userName;
	
	@TableField(value = "NICK_NAME")
	private String nickName;
	
	@TableField(value = "EMAIL")
	private String email;
	
	@TableField(value = "INTRODUCTION")
	private String introduction;
	
	@TableField(value = "GENDER")
	private String gender;
	
	@TableField(value = "DATE_OF_BIRTH")
	private LocalDateTime dateOfBirth;
	
	@TableField(value = "LOCATION")
	private String location;
	
	@TableField(value = "FOLLOWER_COUNT")
	private Integer followerCount;
	
	@TableField(value = "FAN_COUNT")
	private Integer fanCount;
	
	@TableField(value = "USER_IMG_PATH")
	private String userImgPath;
	
	@TableField(value = "LOGIN_ID")
	private String loginId;
	
	@TableField(value = "PASSWORD")
	private String password;
	
	@TableField(value = "CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;
	
	@TableField(value = "LAST_UPDATE_DATE_TIME")
	private LocalDateTime lastUpdateDateTime;

}
