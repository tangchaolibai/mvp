package com.hsb.mvpmsweb.model.payload.message;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * This entity is used to describe an chat response data.
 */
@Data
@ApiModel(description = "This entity is used to describe an chat response data.")
@Validated
@ToString
public class ChatResponseData {
	
	@JsonProperty("toUserId")
	private Integer toUserId = null;

	@JsonProperty("toUserName")
	private String toUserName = null;

	@JsonProperty("toUserImgPath")
	private String toUserImgPath = null;

	@JsonProperty("fromUserId")
	private Integer fromUserId = null;

	@JsonProperty("fromUserName")
	private String fromUserName = null;

	@JsonProperty("fromUserImgPath")
	private String fromUserImgPath = null;

	@JsonProperty("msgType")
	private String msgType = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("createTime")
	private LocalDateTime createTime = null;

}
