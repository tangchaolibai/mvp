package com.hsb.mvpmsweb.model.payload.message;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to message response data.
 */
@Data
@ApiModel(description = "This entity is used to message response data.")
@Validated
@ToString
public class MessageResponseData {
	
	private Integer id = null;

	private String msgType = null;

	private String msg = null;

	private Integer fromUserId = null;

	private String fromUserName = null;

	private String fromUserImgPath = null;

}
