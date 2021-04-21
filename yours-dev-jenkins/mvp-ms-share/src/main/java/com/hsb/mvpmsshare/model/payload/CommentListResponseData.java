package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CommentListResponseData
 */
@Validated

public class CommentListResponseData   {
  @JsonProperty("commentCount")
  private Integer commentCount = null;

  @JsonProperty("shareId")
  private Integer shareId = null;

  @JsonProperty("commentList")
  @Valid
  private List<CommentResponseData> commentList = null;

  public CommentListResponseData commentCount(Integer commentCount) {
    this.commentCount = commentCount;
    return this;
  }

  /**
   * Comment Count
   * @return commentCount
  **/
  @ApiModelProperty(value = "Comment Count")


  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public CommentListResponseData shareId(Integer shareId) {
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

  public CommentListResponseData commentList(List<CommentResponseData> commentList) {
    this.commentList = commentList;
    return this;
  }

  public CommentListResponseData addCommentListItem(CommentResponseData commentListItem) {
    if (this.commentList == null) {
      this.commentList = new ArrayList<>();
    }
    this.commentList.add(commentListItem);
    return this;
  }

  /**
   * List All Comment
   * @return commentList
  **/
  @ApiModelProperty(value = "List All Comment")

  @Valid

  public List<CommentResponseData> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<CommentResponseData> commentList) {
    this.commentList = commentList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentListResponseData commentListResponseData = (CommentListResponseData) o;
    return Objects.equals(this.commentCount, commentListResponseData.commentCount) &&
        Objects.equals(this.shareId, commentListResponseData.shareId) &&
        Objects.equals(this.commentList, commentListResponseData.commentList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentCount, shareId, commentList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentListResponseData {\n");
    
    sb.append("    commentCount: ").append(toIndentedString(commentCount)).append("\n");
    sb.append("    shareId: ").append(toIndentedString(shareId)).append("\n");
    sb.append("    commentList: ").append(toIndentedString(commentList)).append("\n");
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

