package com.hsb.mvpmsassistant.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author
 * An abstract entity for all entities in the system
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
public abstract class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@TableId
	@Column(name = "ENTITY_ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
	@JsonIgnore
	private Integer entityId;

	@Column(name = "CREATION_DATE_TIME", type = MySqlTypeConstant.DATETIME)
	@JsonIgnore
	private LocalDateTime creationDateTime;
	
}
