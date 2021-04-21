package com.hsb.mvpmsmessage.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "SOCIAL_LIKE")
@Table(name = "SOCIAL_LIKE")
public class Like implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	@Column(name = "ENTITY_ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
	@JsonIgnore
	private Integer entityId;
	
	@Column(name = "SHARE_ID", length = 11, type = MySqlTypeConstant.INT)
	private Integer shareId;
	
	@Column(name = "FROM_USER_ID", length = 11, type = MySqlTypeConstant.INT)
	private Integer fromUserId;

	@Column(name = "CREATION_DATE_TIME", type = MySqlTypeConstant.DATETIME)
	@JsonIgnore
	private LocalDateTime creationDateTime;
	
}
