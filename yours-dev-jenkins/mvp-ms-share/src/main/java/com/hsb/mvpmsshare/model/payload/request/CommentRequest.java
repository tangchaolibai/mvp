package com.hsb.mvpmsshare.model.payload.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a comment request.
 */
@ApiModel(description = "This entity is used to describe a comment request.")
@Validated

public class CommentRequest   {
  @JsonProperty("shareId")
  private Integer shareId = null;

  @JsonProperty("entityId")
  private Integer entityId = null;

  @JsonProperty("fromUserId")
  private Integer fromUserId = null;

  @JsonProperty("toUserId")
  private Integer toUserId = null;

  @JsonProperty("comment")
  private String comment = null;

  public CommentRequest shareId(Integer shareId) {
    this.shareId = shareId;
    return this;
  }

  /**
   * Share Id
   * @return shareId
  **/
  @ApiModelProperty(value = "Share Id")


  public Integer getShareId() {
    return shareId;
  }

  public void setShareId(Integer shareId) {
    this.shareId = shareId;
  }

  public CommentRequest entityId(Integer entityId) {
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

  public CommentRequest fromUserId(Integer fromUserId) {
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

  public CommentRequest toUserId(Integer toUserId) {
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

  public CommentRequest comment(String comment) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentRequest commentRequest = (CommentRequest) o;
    return Objects.equals(this.shareId, commentRequest.shareId) &&
        Objects.equals(this.entityId, commentRequest.entityId) &&
        Objects.equals(this.fromUserId, commentRequest.fromUserId) &&
        Objects.equals(this.toUserId, commentRequest.toUserId) &&
        Objects.equals(this.comment, commentRequest.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shareId, entityId, fromUserId, toUserId, comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentRequest {\n");
    
    sb.append("    shareId: ").append(toIndentedString(shareId)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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

