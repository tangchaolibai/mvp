package com.hsb.mvpmsmessage.model.payload;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe an chat response data.
 */
@ApiModel(description = "This entity is used to describe an chat response data.")
@Validated

public class ChatResponseData   {
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

  public ChatResponseData toUserId(Integer toUserId) {
    this.toUserId = toUserId;
    return this;
  }

  /**
   * To User Entity Id
   * @return toUserId
  **/
  @ApiModelProperty(value = "To User Entity Id")


  public Integer getToUserId() {
    return toUserId;
  }

  public void setToUserId(Integer toUserId) {
    this.toUserId = toUserId;
  }

  public ChatResponseData toUserName(String toUserName) {
    this.toUserName = toUserName;
    return this;
  }

  /**
   * To User Name
   * @return toUserName
  **/
  @ApiModelProperty(value = "To User Name")


  public String getToUserName() {
    return toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public ChatResponseData toUserImgPath(String toUserImgPath) {
    this.toUserImgPath = toUserImgPath;
    return this;
  }

  /**
   * To User Image Path
   * @return toUserImgPath
  **/
  @ApiModelProperty(value = "To User Image Path")


  public String getToUserImgPath() {
    return toUserImgPath;
  }

  public void setToUserImgPath(String toUserImgPath) {
    this.toUserImgPath = toUserImgPath;
  }

  public ChatResponseData fromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
    return this;
  }

  /**
   * From User Id
   * @return fromUserId
  **/
  @ApiModelProperty(value = "From User Id")


  public Integer getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
  }

  public ChatResponseData fromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
    return this;
  }

  /**
   * From User Name
   * @return fromUserName
  **/
  @ApiModelProperty(value = "From User Name")


  public String getFromUserName() {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public ChatResponseData fromUserImgPath(String fromUserImgPath) {
    this.fromUserImgPath = fromUserImgPath;
    return this;
  }

  /**
   * User Image Path
   * @return fromUserImgPath
  **/
  @ApiModelProperty(value = "User Image Path")


  public String getFromUserImgPath() {
    return fromUserImgPath;
  }

  public void setFromUserImgPath(String fromUserImgPath) {
    this.fromUserImgPath = fromUserImgPath;
  }

  public ChatResponseData msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  /**
   * Message Type
   * @return msgType
  **/
  @ApiModelProperty(value = "Message Type")


  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public ChatResponseData message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Chat Message
   * @return message
  **/
  @ApiModelProperty(value = "Chat Message")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ChatResponseData createTime(LocalDateTime createTime) {
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
    ChatResponseData chatResponseData = (ChatResponseData) o;
    return Objects.equals(this.toUserId, chatResponseData.toUserId) &&
        Objects.equals(this.toUserName, chatResponseData.toUserName) &&
        Objects.equals(this.toUserImgPath, chatResponseData.toUserImgPath) &&
        Objects.equals(this.fromUserId, chatResponseData.fromUserId) &&
        Objects.equals(this.fromUserName, chatResponseData.fromUserName) &&
        Objects.equals(this.fromUserImgPath, chatResponseData.fromUserImgPath) &&
        Objects.equals(this.msgType, chatResponseData.msgType) &&
        Objects.equals(this.message, chatResponseData.message) &&
        Objects.equals(this.createTime, chatResponseData.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toUserId, toUserName, toUserImgPath, fromUserId, fromUserName, fromUserImgPath, msgType, message, createTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatResponseData {\n");
    
    sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
    sb.append("    toUserName: ").append(toIndentedString(toUserName)).append("\n");
    sb.append("    toUserImgPath: ").append(toIndentedString(toUserImgPath)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    fromUserName: ").append(toIndentedString(fromUserName)).append("\n");
    sb.append("    fromUserImgPath: ").append(toIndentedString(fromUserImgPath)).append("\n");
    sb.append("    msgType: ").append(toIndentedString(msgType)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

