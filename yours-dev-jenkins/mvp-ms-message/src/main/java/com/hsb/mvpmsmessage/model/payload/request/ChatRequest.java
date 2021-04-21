package com.hsb.mvpmsmessage.model.payload.request;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ChatRequest
 */
@Validated

public class ChatRequest   {
  @JsonProperty("toUserId")
  private Integer toUserId = null;

  @JsonProperty("fromUserId")
  private Integer fromUserId = null;

  @JsonProperty("msgType")
  private String msgType = null;

  @JsonProperty("message")
  private String message = null;

  public ChatRequest toUserId(Integer toUserId) {
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

  public ChatRequest fromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
    return this;
  }

  /**
   * Comment From User Id
   * @return fromUserId
  **/
  @ApiModelProperty(value = "Comment From User Id")


  public Integer getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Integer fromUserId) {
    this.fromUserId = fromUserId;
  }

  public ChatRequest msgType(String msgType) {
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

  public ChatRequest message(String message) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatRequest chatRequest = (ChatRequest) o;
    return Objects.equals(this.toUserId, chatRequest.toUserId) &&
        Objects.equals(this.fromUserId, chatRequest.fromUserId) &&
        Objects.equals(this.msgType, chatRequest.msgType) &&
        Objects.equals(this.message, chatRequest.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toUserId, fromUserId, msgType, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatRequest {\n");
    
    sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    msgType: ").append(toIndentedString(msgType)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

