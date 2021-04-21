package com.hsb.mvpmsweb.model.payload.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to like response data.
 */
@Data
@ApiModel(description = "This entity is used to like response data.")
@Validated
@ToString
public class LikeResponseData {
	
	private Integer id = null;

	private Integer shareId = null;

	private Integer fromUserId = null;

	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime creationDateTime = null;
	
	@ApiModelProperty(example = "3 days ago")
	private String dateTimeAgo = null;
	
	private String nickName = null;
	
	private String fileType = null;
	
	private String userImgPath = null;
	
	private String thumbnailUrl = null;
	
	private String videoPath = null;
	
}
