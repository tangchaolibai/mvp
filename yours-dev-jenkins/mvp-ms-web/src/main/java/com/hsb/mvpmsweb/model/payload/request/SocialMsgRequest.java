package com.hsb.mvpmsweb.model.payload.request;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe like of message Request.
 */
@ApiModel(description = "This entity is used to describe like of message Request.")
@Validated

public class SocialMsgRequest   {
  @JsonProperty("shareId")
  private Integer shareId = null;

  @JsonProperty("fromUserId")
  private Integer fromUserId = null;

  @JsonProperty("toUserId")
  private List<Integer> toUserId = null;

  @JsonProperty("msgType")
  private String msgType = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("isRead")
  private String isRead = null;

  public SocialMsgRequest shareId(Integer shareId) {
    this.shareId = shareId;
    return this;
  }

  /**
   * ShareId of Content
   * @return shareId
  **/
  @ApiModelProperty(value = "ShareId of Content")


  public Integer getShareId() {
    return shareId;
  }

  public void setShareId(Integer shareId) {
    this.shareId = shareId;
  }

  public SocialMsgRequest fromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
    return this;
  }

  /**
   * userId of who send the message
   * @return fromUserId
  **/
  @ApiModelProperty(value = "userId of who send the message")


  public Integer getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
  }

  public SocialMsgRequest toUserId(List<Integer> toUserId) {
    this.toUserId = toUserId;
    return this;
  }

  /**
   * userId of who receive the message
   * @return toUserId
  **/
  @ApiModelProperty(value = "userId of who receive the message")


  public List<Integer> getToUserId() {
    return toUserId;
  }

  public void setToUserId(List<Integer> toUserId) {
    this.toUserId = toUserId;
  }

  public SocialMsgRequest msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  /**
   * msgType of message
   * @return msgType
  **/
  @ApiModelProperty(value = "msgType of message")


  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public SocialMsgRequest message(String message) {
    this.message = message;
    return this;
  }

  /**
   * text of message
   * @return message
  **/
  @ApiModelProperty(value = "text of message")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public SocialMsgRequest isRead(String isRead) {
    this.isRead = isRead;
    return this;
  }

  /**
   * text of message
   * @return isRead
  **/
  @ApiModelProperty(value = "text of message")


  public String getIsRead() {
    return isRead;
  }

  public void setIsRead(String isRead) {
    this.isRead = isRead;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SocialMsgRequest socialMsgRequest = (SocialMsgRequest) o;
    return Objects.equals(this.shareId, socialMsgRequest.shareId) &&
        Objects.equals(this.fromUserId, socialMsgRequest.fromUserId) &&
        Objects.equals(this.toUserId, socialMsgRequest.toUserId) &&
        Objects.equals(this.msgType, socialMsgRequest.msgType) &&
        Objects.equals(this.message, socialMsgRequest.message) &&
        Objects.equals(this.isRead, socialMsgRequest.isRead);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shareId, fromUserId, toUserId, msgType, message, isRead);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SocialMsgRequest {\n");
    
    sb.append("    shareId: ").append(toIndentedString(shareId)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
    sb.append("    msgType: ").append(toIndentedString(msgType)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    isRead: ").append(toIndentedString(isRead)).append("\n");
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

