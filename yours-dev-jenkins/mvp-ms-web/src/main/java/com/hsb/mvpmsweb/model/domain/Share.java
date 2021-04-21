package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "SHARE")
@AllArgsConstructor
@NoArgsConstructor
public class Share implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@TableId(value = "ENTITY_ID",type = IdType.AUTO)
	private Integer entityId;

	@TableField(value = "USER_ID")
	private Integer userId;

	@TableField(value = "POST_STATUS")
	private String postStatus;

	@TableField(value = "MESSAGE")
	private String message;

	@TableField(value = "FILETYPE")
	private String fileType;

	@TableField(value = "LOCATION")
	private String location;

	@TableField(value = "TO_USER_ID")
	private String toUserId;

	@TableField(value = "LIKE_COUNT")
	private Integer likeCount;

	@TableField(value = "COMMENT_COUNT")
	private Integer commentCount;

	@TableField(value = "SHARE_COUNT")
	private Integer shareCount;

	@TableField(value = "VIEW_AMOUNT")
	private Integer viewAmount;

	@TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationDateTime;

	@TableField(value = "LAST_UPDATE_DATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdateDateTime;
	
	@TableField(value = "PRODUCT_URL")
    private String productUrl;

}
