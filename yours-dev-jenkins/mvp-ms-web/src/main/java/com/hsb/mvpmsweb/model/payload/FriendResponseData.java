package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;

/**
 * FriendResponseData
 */
@Validated
@Data
@ToString
public class FriendResponseData {

	@JsonProperty("userId")
	private Integer userId = null;

	@JsonProperty("userName")
	private String userName = null;

	@JsonProperty("userImgPath")
	private String userImgPath = null;

	@JsonProperty("fanId")
	private Integer fanId = null;
  
	@JsonProperty("fanMobilePhone")
	private String fanMobilePhone = null;
  
	@JsonProperty("type")
	private Integer type = null;
  
	@JsonProperty("fanName")
	private String fanName = null;

	@JsonProperty("initials")
	private String initials = null;

	@JsonProperty("fanImgPath")
	private String fanImgPath = null;

	@JsonProperty("registerFlag")
	private Boolean registerFlag = null;

	@JsonProperty("createTime")
	private LocalDateTime createTime = null;

}

