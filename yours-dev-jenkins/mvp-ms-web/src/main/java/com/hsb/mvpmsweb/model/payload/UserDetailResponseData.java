package com.hsb.mvpmsweb.model.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UserResponseData
 */
@ApiModel(description = "This entity is used to describe an user detail response data.")
@Validated
@Data
public class UserDetailResponseData {
	@JsonProperty("userId")
	private Integer userId = null;

	@JsonProperty("userRole")
	private String userRole = null;

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
	private String gender = null;

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

	@JsonProperty("loginId")
	private String loginId = null;

	@JsonProperty("password")
	private String password = null;

	@JsonProperty("creationDateTime")
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime creationDateTime = null;

	@JsonProperty("lastUpdateDateTime")
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime lastUpdateDateTime = null;

	@JsonProperty("isFriend")
	private Boolean isFriend;

}

