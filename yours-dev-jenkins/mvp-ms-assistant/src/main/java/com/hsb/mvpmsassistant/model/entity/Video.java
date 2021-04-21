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
@TableName(value = "VIDEOS")
@Table(name = "VIDEOS")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	@Column(name = "ENTITY_ID", type = MySqlTypeConstant.INT, length = 11, isKey = true, isAutoIncrement = true)
	@JsonIgnore
	private Integer entityId;
	
	@Column(name = "VIDEO_NAME", length = 50, type = MySqlTypeConstant.VARCHAR)
	private String videoName;
	
	@Column(name = "VIDEO_DES", length = 500, type = MySqlTypeConstant.VARCHAR)
	private String videoDes;
	
	@Column(name = "VIDEO_LOC", length = 100, type = MySqlTypeConstant.VARCHAR)
	private String videoLoc;
	
	@Column(name = "VIDEO_PATH", length = 100, type = MySqlTypeConstant.VARCHAR)
	private String videoPath;
	
	@Column(name = "VIDEO_SECONDS", length = 11, type = MySqlTypeConstant.INT)
	private Integer videoSeconds;
	
	@Column(name = "THUMBNAIL_URL", length = 100, type = MySqlTypeConstant.VARCHAR)
	private String thumbnailUrl;
	
	@Column(name = "SHARE_ID", length = 11, type = MySqlTypeConstant.INT)
	private Integer shareId;
	
	@Column(name = "CREATION_DATE_TIME", type = MySqlTypeConstant.DATETIME)
	@JsonIgnore
	private LocalDateTime creationDateTime;
	
}
