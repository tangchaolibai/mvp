package com.hsb.mvpmsweb.model.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * This entity is used to describe an User response data.
 */
@ApiModel(description = "This entity is used to describe an User response data.")
@Data
@Accessors(fluent = true)
@ToString
public class UserInfoResponseData {
	
	@JsonProperty("userId")
	private Integer entityId = null;

	@JsonProperty("userRole")
	private Integer userRole = null;

	@JsonProperty("mobilePhone")
	private String mobilePhone = null;
	
	@JsonProperty("areaCode")
	private String areaCode = null;

	@JsonProperty("userName")
	private String userName = null;

	@JsonProperty("nickName")
	private String nickName = null;

	@JsonProperty("email")
	private String email = null;

	@JsonProperty("introduction")
	private String introduction = null;

	@JsonProperty("gender")
	private Integer gender = null;

	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth = null;

	@JsonProperty("location")
	private String location = null;
	
	@JsonProperty("followerCount")
	private Integer followerCount = null;
	
	@JsonProperty("fanCount")
	private Integer fanCount = null;
	
	@JsonProperty("userImgPath")
	private String userImgPath = null;
	
	@JsonProperty("coverImgPath")
	private String coverImgPath = null;
	
	@JsonProperty("loginId")
	private String loginId = null;
	
	@JsonProperty("creationDateTime")
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime creationDateTime = null;

	@JsonProperty("lastUpdateDateTime")
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime lastUpdateDateTime = null;
	
	@JsonProperty("shopId")
	private String shopId = null;
	
}
