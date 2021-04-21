package com.hsb.mvpmsshare.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "SOCIAL_MSG")
public class SocialMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "ENTITY_ID",type = IdType.AUTO)
	private Integer entityId;

	@TableField(value = "SHARE_ID")
	private Integer shareId;
	
	@TableField(value = "MSG")
	private String msg;

	@TableField(value = "FROM_USER_ID")
	private Integer fromUserId;

	@TableField(value = "TO_USER_ID")
	private Integer toUserId;

	@TableField(value = "IS_READ")
	private String isRead;

	@TableField(value = "CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;

}
