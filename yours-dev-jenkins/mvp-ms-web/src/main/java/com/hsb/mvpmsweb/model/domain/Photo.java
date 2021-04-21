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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@TableName(value = "PHOTOS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "ENTITY_ID", type = IdType.AUTO)
	private Integer entityId;
	
	@TableField("PHOTO_NAME")
	private String photoName;

	@TableField("PHOTO_DES")
	private String description;

	@TableField("PHOTO_LOC")
	private String location;

	@TableField("PHOTO_PATH")
	private String photoPath;

	@TableField("SHARE_ID")
	private Integer shareId;
	
	@TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
	private LocalDateTime creationDateTime;
	
}
