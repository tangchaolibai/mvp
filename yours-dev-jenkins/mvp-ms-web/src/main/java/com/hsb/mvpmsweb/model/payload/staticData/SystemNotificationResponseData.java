package com.hsb.mvpmsweb.model.payload.staticData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to System Notification response data.
 */
@Data
@ApiModel(description = "This entity is used to system notification response data.")
@Validated
@ToString
public class SystemNotificationResponseData {
	
	private Integer id = null;

	private String notification = null;
	
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime creationDateTime = null;
	
}
