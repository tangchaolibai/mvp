package com.hsb.mvpmsweb.model.payload;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentResponseData {

	@JsonProperty("entityId")
	private Integer entityId = null;

	@JsonProperty("fromUserId")
	private Integer fromUserId = null;

	@JsonProperty("fromUserName")
	private String fromUserName = null;

	@JsonProperty("fromUserImgPath")
	private String fromUserImgPath = null;

	@JsonProperty("comment")
	private String comment = null;

	@JsonProperty("createTime")
	private String createTime = null;

	@JsonProperty("replyList")
	@Valid
	private List<ReplyResponseData> replyList = null;
  
	@JsonProperty("replyCount")
	private Integer replyCount = null;

}

