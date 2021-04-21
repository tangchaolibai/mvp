package com.hsb.mvpmsshare.model.domain;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("REPLY")
@Data
@Accessors(chain = true)
public class Reply {

	@TableId(value = "ENTITY_ID",type = IdType.AUTO)
	private Integer entityId;
	
	@TableField(value = "COMMENT_ID")
	private Integer commentId;
	
	@TableField(value = "FROM_USER_ID")
	private Integer fromUserId;
	
	@TableField(value = "TO_USER_ID")
	private Integer toUserId;
	
	@TableField(value = "REPLY")
	private String reply;
	
	@TableField(value = "CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;
	
}
