package com.hsb.mvpmsshare.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@TableName(value = "VIDEOS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "ENTITY_ID", type = IdType.AUTO)
	private Integer entityId;
	
	@TableField("VIDEO_NAME")
	private String videoName;
	
	@TableField("VIDEO_DES")
	private String description;
	
	@TableField("VIDEO_LOC")
	private String location;
	
	@TableField("VIDEO_PATH")
	private String videoPath;
	
	@TableField("VIDEO_SECONDS")
	private int seconds;
	
	@TableField("THUMBNAIL_URL")
	private String thumbnailPath;
	
	@TableField("SHARE_ID")
	private Integer shareId;
	
	@TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
	private LocalDateTime creationDateTime;
	
}
