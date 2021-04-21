package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * ReplyResponseData
 */
@Data
@Validated
public class ReplyResponseData {

	@JsonProperty("entityId")
	private Integer entityId = null;

	@JsonProperty("commentId")
	private Integer commentId = null;

	@JsonProperty("toUserId")
	private Integer toUserId = null;

	@JsonProperty("fromUserId")
	private Integer fromUserId = null;

	@JsonProperty("fromUserName")
	private String fromUserName = null;

	@JsonProperty("fromUserImgPath")
	private String fromUserImgPath = null;
  
	@JsonProperty("toUserName")
	private String toUserName = null;

	@JsonProperty("comment")
	private String comment = null;

	@JsonProperty("createTime")
	private String createTime = null;

}

