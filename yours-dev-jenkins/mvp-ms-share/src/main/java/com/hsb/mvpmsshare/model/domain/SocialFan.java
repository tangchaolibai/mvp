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
@TableName("SOCIAL_FAN")
public class SocialFan implements Serializable{

	private static final long serialVersionUID = 9060753270070195175L;

	@TableId(value = "ENTITY_ID", type = IdType.AUTO)
	private Integer entityId;
	
	@TableField(value = "USER_ID")
	private Integer userId;
	
	@TableField(value = "FAN_ID")
	private Integer fanId;
	
	@TableField(value = "IS_BLACK")
	private String isBlack;
	
	@TableField(value = "CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;
	
}
