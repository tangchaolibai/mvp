package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("COMMENT")
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@TableId(value = "ENTITY_ID",type = IdType.AUTO)
	private Integer entityId;
	
	@TableField(value = "SHARE_ID")
	private Integer shareId;
	
	@TableField(value = "FROM_USER_ID")
	private Integer fromUserId;
	
	@TableField(value = "MESSAGE")
	private String message;
	
	@TableField(value = "CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;
	
}
