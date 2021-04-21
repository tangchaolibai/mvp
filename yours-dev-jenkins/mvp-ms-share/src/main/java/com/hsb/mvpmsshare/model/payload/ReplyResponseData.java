package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * ReplyResponseData
 */
@Validated

public class ReplyResponseData   {
  @JsonProperty("entityId")
  private Integer entityId = null;

  @JsonProperty("commentId")
  private Integer commentId = null;

  @JsonProperty("toUserId")
  private Integer toUserId = null;

  @JsonProperty("fromUserId")
  private Integer fromUserId = null;

  @JsonProperty("fromUserName")
  private String fromUserName = null;

  @JsonProperty("fromUserImgPath")
  private String fromUserImgPath = null;

  @JsonProperty("reply")
  private String reply = null;

  @JsonProperty("createTime")
  private LocalDateTime createTime = null;

  public ReplyResponseData entityId(Integer entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Reply Entity Id
   * @return entityId
  **/
  @ApiModelProperty(value = "Reply Entity Id")


  public Integer getEntityId() {
    return entityId;
  }

  public void setEntityId(Integer entityId) {
    this.entityId = entityId;
  }

  public ReplyResponseData commentId(Integer commentId) {
    this.commentId = commentId;
    return this;
  }

  /**
   * Comment Entity Id
   * @return commentId
  **/
  @ApiModelProperty(value = "Comment Entity Id")


  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public ReplyResponseData toUserId(Integer toUserId) {
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

  public ReplyResponseData fromUserId(Integer fromUserId) {
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

  public ReplyResponseData fromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
    return this;
  }

  /**
   * Comment From User Name
   * @return fromUserName
  **/
  @ApiModelProperty(value = "Comment From User Name")


  public String getFromUserName() {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public ReplyResponseData fromUserImgPath(String fromUserImgPath) {
    this.fromUserImgPath = fromUserImgPath;
    return this;
  }

  /**
   * Comment From User Image Path
   * @return fromUserImgPath
  **/
  @ApiModelProperty(value = "Comment From User Image Path")


  public String getFromUserImgPath() {
    return fromUserImgPath;
  }

  public void setFromUserImgPath(String fromUserImgPath) {
    this.fromUserImgPath = fromUserImgPath;
  }

  public ReplyResponseData reply(String reply) {
    this.reply = reply;
    return this;
  }

  /**
   * Comment From User Reply
   * @return reply
  **/
  @ApiModelProperty(value = "Comment From User Reply")


  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public ReplyResponseData createTime(LocalDateTime createTime) {
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
    ReplyResponseData replyResponseData = (ReplyResponseData) o;
    return Objects.equals(this.entityId, replyResponseData.entityId) &&
        Objects.equals(this.commentId, replyResponseData.commentId) &&
        Objects.equals(this.toUserId, replyResponseData.toUserId) &&
        Objects.equals(this.fromUserId, replyResponseData.fromUserId) &&
        Objects.equals(this.fromUserName, replyResponseData.fromUserName) &&
        Objects.equals(this.fromUserImgPath, replyResponseData.fromUserImgPath) &&
        Objects.equals(this.reply, replyResponseData.reply) &&
        Objects.equals(this.createTime, replyResponseData.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId, commentId, toUserId, fromUserId, fromUserName, fromUserImgPath, reply, createTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplyResponseData {\n");
    
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    commentId: ").append(toIndentedString(commentId)).append("\n");
    sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    fromUserName: ").append(toIndentedString(fromUserName)).append("\n");
    sb.append("    fromUserImgPath: ").append(toIndentedString(fromUserImgPath)).append("\n");
    sb.append("    reply: ").append(toIndentedString(reply)).append("\n");
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

