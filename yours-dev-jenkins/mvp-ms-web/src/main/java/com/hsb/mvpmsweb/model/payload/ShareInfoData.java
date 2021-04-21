package com.hsb.mvpmsweb.model.payload;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@Validated
@ToString
public class ShareInfoData {
  
	@JsonProperty("shareId")
	private Integer shareId;

	@JsonProperty("userId")
	private Integer userId;
	   
	@JsonProperty("userImgPath")
	private String userImgPath;
	  
	@JsonProperty("userName")
	private String userName;

	@JsonProperty("postStatus")
	private String postStatus;

	@JsonProperty("message")
	private String message;

	@JsonProperty("fileType")
	private String fileType;

	@JsonProperty("location")
	private String location;

	@JsonProperty("toUserId")
	private String toUserId;

	@JsonProperty("shareCount")
	private Integer shareCount;

	@JsonProperty("likeCount")
	private Integer likeCount;

	@JsonProperty("commentCount")
	private Integer commentCount;

	@JsonProperty("viewAmount")
	private Integer viewAmount;
	
	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;
	
	@JsonProperty("isLike")
	private Boolean isLike;
	
	@JsonProperty("isFollowing")
	private Boolean isFollowing;
	
	@JsonProperty("createTime")
	@JsonFormat(pattern = "dd.MMM",locale = "ENGLISH")
	private LocalDateTime createTime;

	@JsonProperty("lastUpdateTime")
	private LocalDateTime lastUpdateTime;
	
	@JsonProperty("productUrl")
	private String productUrl;

	@JsonProperty("contentResponseData")
	private List<ContentResponseData> contentResponseData;

}

