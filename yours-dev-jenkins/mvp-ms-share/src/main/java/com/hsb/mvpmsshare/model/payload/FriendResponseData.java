package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * FriendResponseData
 */
@Validated

public class FriendResponseData   {
  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("userImgPath")
  private String userImgPath = null;

  @JsonProperty("fanId")
  private Integer fanId = null;

  @JsonProperty("fanName")
  private String fanName = null;

  @JsonProperty("fanImgPath")
  private String fanImgPath = null;

  @JsonProperty("registerFlag")
  private Boolean registerFlag = null;

  @JsonProperty("createTime")
  private LocalDateTime createTime = null;

  public FriendResponseData userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * To User Id
   * @return userId
  **/
  @ApiModelProperty(value = "To User Id")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public FriendResponseData userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * User Name
   * @return userName
  **/
  @ApiModelProperty(value = "User Name")


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public FriendResponseData userImgPath(String userImgPath) {
    this.userImgPath = userImgPath;
    return this;
  }

  /**
   * User Image Path
   * @return userImgPath
  **/
  @ApiModelProperty(value = "User Image Path")


  public String getUserImgPath() {
    return userImgPath;
  }

  public void setUserImgPath(String userImgPath) {
    this.userImgPath = userImgPath;
  }

  public FriendResponseData fanId(Integer fanId) {
    this.fanId = fanId;
    return this;
  }

  /**
   * To Fan Id
   * @return fanId
  **/
  @ApiModelProperty(value = "To Fan Id")


  public Integer getFanId() {
    return fanId;
  }

  public void setFanId(Integer fanId) {
    this.fanId = fanId;
  }

  public FriendResponseData fanName(String fanName) {
    this.fanName = fanName;
    return this;
  }

  /**
   * Fan Name
   * @return fanName
  **/
  @ApiModelProperty(value = "Fan Name")


  public String getFanName() {
    return fanName;
  }

  public void setFanName(String fanName) {
    this.fanName = fanName;
  }

  public FriendResponseData fanImgPath(String fanImgPath) {
    this.fanImgPath = fanImgPath;
    return this;
  }

  /**
   * Fan Image Path
   * @return fanImgPath
  **/
  @ApiModelProperty(value = "Fan Image Path")


  public String getFanImgPath() {
    return fanImgPath;
  }

  public void setFanImgPath(String fanImgPath) {
    this.fanImgPath = fanImgPath;
  }

  public FriendResponseData registerFlag(Boolean registerFlag) {
    this.registerFlag = registerFlag;
    return this;
  }

  /**
   * Mobile Phone Number is or not Register
   * @return registerFlag
  **/
  @ApiModelProperty(value = "Mobile Phone Number is or not Register")


  public Boolean isRegisterFlag() {
    return registerFlag;
  }

  public void setRegisterFlag(Boolean registerFlag) {
    this.registerFlag = registerFlag;
  }

  public FriendResponseData createTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Commnet Create Time
   * @return createTime
  **/
  @ApiModelProperty(value = "Commnet Create Time")

  @Valid

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendResponseData friendResponseData = (FriendResponseData) o;
    return Objects.equals(this.userId, friendResponseData.userId) &&
        Objects.equals(this.userName, friendResponseData.userName) &&
        Objects.equals(this.userImgPath, friendResponseData.userImgPath) &&
        Objects.equals(this.fanId, friendResponseData.fanId) &&
        Objects.equals(this.fanName, friendResponseData.fanName) &&
        Objects.equals(this.fanImgPath, friendResponseData.fanImgPath) &&
        Objects.equals(this.registerFlag, friendResponseData.registerFlag) &&
        Objects.equals(this.createTime, friendResponseData.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userName, userImgPath, fanId, fanName, fanImgPath, registerFlag, createTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendResponseData {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    userImgPath: ").append(toIndentedString(userImgPath)).append("\n");
    sb.append("    fanId: ").append(toIndentedString(fanId)).append("\n");
    sb.append("    fanName: ").append(toIndentedString(fanName)).append("\n");
    sb.append("    fanImgPath: ").append(toIndentedString(fanImgPath)).append("\n");
    sb.append("    registerFlag: ").append(toIndentedString(registerFlag)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

