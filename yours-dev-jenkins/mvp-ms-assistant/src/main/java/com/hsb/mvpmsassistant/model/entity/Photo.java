package com.hsb.mvpmsassistant.model.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "PHOTOS")
@Table(name = "PHOTOS")
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	@Column(name = "ENTITY_ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
	@JsonIgnore
	private Integer entityId;
	
	@Column(name = "PHOTO_NAME", length = 50, type = MySqlTypeConstant.VARCHAR)
	private String photoName;
	
	@Column(name = "PHOTO_DES", length = 500, type = MySqlTypeConstant.VARCHAR)
	private String photoDes;
	
	@Column(name = "PHOTO_LOC", length = 100, type = MySqlTypeConstant.VARCHAR)
	private String photoLoc;
	
	@Column(name = "PHOTO_PATH", length = 100, type = MySqlTypeConstant.VARCHAR)
	private String photoPath;
	
	@Column(name = "SHARE_ID", length = 11, type = MySqlTypeConstant.INT)
	private Integer shareId;
	
	@Column(name = "CREATION_DATE_TIME", type = MySqlTypeConstant.DATETIME)
	@JsonIgnore
	private LocalDateTime creationDateTime;
	
}
