package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CommentResponseData
 */
@Validated

public class CommentResponseData   {
  @JsonProperty("entityId")
  private Integer entityId = null;

  @JsonProperty("fromUserId")
  private Integer fromUserId = null;

  @JsonProperty("fromUserName")
  private String fromUserName = null;

  @JsonProperty("fromUserImgPath")
  private String fromUserImgPath = null;

  @JsonProperty("comment")
  private String comment = null;

  @JsonProperty("createTime")
  private LocalDateTime createTime = null;

  @JsonProperty("replyList")
  @Valid
  private List<ReplyResponseData> replyList = null;

  public CommentResponseData entityId(Integer entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Comment Entity Id
   * @return entityId
  **/
  @ApiModelProperty(value = "Comment Entity Id")


  public Integer getEntityId() {
    return entityId;
  }

  public void setEntityId(Integer entityId) {
    this.entityId = entityId;
  }

  public CommentResponseData fromUserId(Integer fromUserId) {
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

  public CommentResponseData fromUserName(String fromUserName) {
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

  public CommentResponseData fromUserImgPath(String fromUserImgPath) {
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

  public CommentResponseData comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * From User Comment
   * @return comment
  **/
  @ApiModelProperty(value = "From User Comment")


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public CommentResponseData createTime(LocalDateTime createTime) {
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

  public CommentResponseData replyList(List<ReplyResponseData> replyList) {
    this.replyList = replyList;
    return this;
  }

  public CommentResponseData addReplyListItem(ReplyResponseData replyListItem) {
    if (this.replyList == null) {
      this.replyList = new ArrayList<>();
    }
    this.replyList.add(replyListItem);
    return this;
  }

  /**
   * List All Reply for Comment
   * @return replyList
  **/
  @ApiModelProperty(value = "List All Reply for Comment")

  @Valid

  public List<ReplyResponseData> getReplyList() {
    return replyList;
  }

  public void setReplyList(List<ReplyResponseData> replyList) {
    this.replyList = replyList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentResponseData commentResponseData = (CommentResponseData) o;
    return Objects.equals(this.entityId, commentResponseData.entityId) &&
        Objects.equals(this.fromUserId, commentResponseData.fromUserId) &&
        Objects.equals(this.fromUserName, commentResponseData.fromUserName) &&
        Objects.equals(this.fromUserImgPath, commentResponseData.fromUserImgPath) &&
        Objects.equals(this.comment, commentResponseData.comment) &&
        Objects.equals(this.createTime, commentResponseData.createTime) &&
        Objects.equals(this.replyList, commentResponseData.replyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId, fromUserId, fromUserName, fromUserImgPath, comment, createTime, replyList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentResponseData {\n");
    
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    fromUserName: ").append(toIndentedString(fromUserName)).append("\n");
    sb.append("    fromUserImgPath: ").append(toIndentedString(fromUserImgPath)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    replyList: ").append(toIndentedString(replyList)).append("\n");
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

