package com.hsb.mvpmsweb.model.payload.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to system messages response data.
 */
@Data
@ApiModel(description = "This entity is used to system messages response data.")
@Validated
@ToString
public class SystemResponseData {
	
	private Integer id = null;

	private String msg = null;
	
	private Integer fromUserId = null;
	
	private String fromUserName = null;
	
	private String fromUserImgPath = null;	
	
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime creationDateTime = null;
	
	private String isRead = null;

}
