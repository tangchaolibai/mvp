package com.hsb.mvpmsshare.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * UserResponseData
 */
@Validated

public class UserDetailResponseData   {
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
  private LocalDateTime dateOfBirth = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("followerCount")
  private Long followerCount = null;

  @JsonProperty("fanCount")
  private Long fanCount = null;

  @JsonProperty("userImgPath")
  private String userImgPath = null;

  @JsonProperty("loginId")
  private String loginId = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("creationDateTime")
  private LocalDateTime creationDateTime = null;

  @JsonProperty("lastUpdateDateTime")
  private LocalDateTime lastUpdateDateTime = null;

  public UserDetailResponseData userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public UserDetailResponseData userRole(String userRole) {
    this.userRole = userRole;
    return this;
  }

  /**
   * Get userRole
   * @return userRole
  **/
  @ApiModelProperty(value = "")


  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }

  public UserDetailResponseData mobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  /**
   * Get mobilePhone
   * @return mobilePhone
  **/
  @ApiModelProperty(value = "")


  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public UserDetailResponseData areaCode(String areaCode) {
    this.areaCode = areaCode;
    return this;
  }

  /**
   * Get areaCode
   * @return areaCode
  **/
  @ApiModelProperty(value = "")


  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public UserDetailResponseData userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserDetailResponseData nickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  /**
   * Get nickName
   * @return nickName
  **/
  @ApiModelProperty(value = "")


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public UserDetailResponseData email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserDetailResponseData introduction(String introduction) {
    this.introduction = introduction;
    return this;
  }

  /**
   * Get introduction
   * @return introduction
  **/
  @ApiModelProperty(value = "")


  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public UserDetailResponseData gender(String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  **/
  @ApiModelProperty(value = "")


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public UserDetailResponseData dateOfBirth(LocalDateTime dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * Get dateOfBirth
   * @return dateOfBirth
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDateTime dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public UserDetailResponseData location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public UserDetailResponseData followerCount(Long followerCount) {
    this.followerCount = followerCount;
    return this;
  }

  /**
   * Get followerCount
   * @return followerCount
  **/
  @ApiModelProperty(value = "")


  public Long getFollowerCount() {
    return followerCount;
  }

  public void setFollowerCount(Long followerCount) {
    this.followerCount = followerCount;
  }

  public UserDetailResponseData fanCount(Long fanCount) {
    this.fanCount = fanCount;
    return this;
  }

  /**
   * Get fanCount
   * @return fanCount
  **/
  @ApiModelProperty(value = "")


  public Long getFanCount() {
    return fanCount;
  }

  public void setFanCount(Long fanCount) {
    this.fanCount = fanCount;
  }

  public UserDetailResponseData userImgPath(String userImgPath) {
    this.userImgPath = userImgPath;
    return this;
  }

  /**
   * Get userImgPath
   * @return userImgPath
  **/
  @ApiModelProperty(value = "")


  public String getUserImgPath() {
    return userImgPath;
  }

  public void setUserImgPath(String userImgPath) {
    this.userImgPath = userImgPath;
  }

  public UserDetailResponseData loginId(String loginId) {
    this.loginId = loginId;
    return this;
  }

  /**
   * Get loginId
   * @return loginId
  **/
  @ApiModelProperty(value = "")


  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public UserDetailResponseData password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserDetailResponseData creationDateTime(LocalDateTime creationDateTime) {
    this.creationDateTime = creationDateTime;
    return this;
  }

  /**
   * Get creationDateTime
   * @return creationDateTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(LocalDateTime creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  public UserDetailResponseData lastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
    this.lastUpdateDateTime = lastUpdateDateTime;
    return this;
  }

  /**
   * Get lastUpdateDateTime
   * @return lastUpdateDateTime
  **/
  @ApiModelProperty(value = "")
  @Valid
  public LocalDateTime getLastUpdateDateTime() {
    return lastUpdateDateTime;
  }

  public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
    this.lastUpdateDateTime = lastUpdateDateTime;
  }
}

