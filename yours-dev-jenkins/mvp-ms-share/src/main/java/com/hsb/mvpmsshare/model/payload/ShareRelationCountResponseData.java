package com.hsb.mvpmsshare.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * ShareRelationCountResponseData
 */
@Validated

public class ShareRelationCountResponseData   {
  @JsonProperty("commentCount")
  private Integer commentCount = null;

  @JsonProperty("shareCount")
  private Integer shareCount = null;

  @JsonProperty("likeCount")
  private Integer likeCount = null;

  public ShareRelationCountResponseData commentCount(Integer commentCount) {
    this.commentCount = commentCount;
    return this;
  }

  /**
   * The Comment Count Of Share
   * @return commentCount
  **/
  @ApiModelProperty(value = "The Comment Count Of Share")
  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public ShareRelationCountResponseData shareCount(Integer shareCount) {
    this.shareCount = shareCount;
    return this;
  }

  /**
   * The Share Count Of Share
   * @return shareCount
  **/
  @ApiModelProperty(value = "The Share Count Of Share")
  public Integer getShareCount() {
    return shareCount;
  }

  public void setShareCount(Integer shareCount) {
    this.shareCount = shareCount;
  }

  public ShareRelationCountResponseData likeCount(Integer likeCount) {
    this.likeCount = likeCount;
    return this;
  }

  /**
   * The Like Count Of Share
   * @return likeCount
  **/
  @ApiModelProperty(value = "The Like Count Of Share")
  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

}

