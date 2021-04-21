package com.hsb.mvpmsshare.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;

/**
 * ShareResponseData
 */
@Validated

public class ShareResponseData   {
  @JsonProperty("shareId")
  private Integer shareId = null;

  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("postStatus")
  private String postStatus = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("fileType")
  private String fileType = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("toUserId")
  private Integer toUserId = null;

  @JsonProperty("shareCount")
  private Integer shareCount = null;

  @JsonProperty("likeCount")
  private Integer likeCount = null;

  @JsonProperty("commentCount")
  private Integer commentCount = null;

  @JsonProperty("viewAmount")
  private Integer viewAmount = null;

  @JsonProperty("createTime")
  private LocalDateTime createTime = null;

  @JsonProperty("lastUpdateTime")
  private LocalDateTime lastUpdateTime = null;

  public ShareResponseData shareId(Integer shareId) {
    this.shareId = shareId;
    return this;
  }

  /**
   * Unique identifier of Share
   * @return shareId
  **/
  @ApiModelProperty(value = "Unique identifier of Share")


  public Integer getShareId() {
    return shareId;
  }

  public void setShareId(Integer shareId) {
    this.shareId = shareId;
  }

  public ShareResponseData userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * userId Of Content
   * @return userId
  **/
  @ApiModelProperty(value = "userId Of Content")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public ShareResponseData postStatus(String postStatus) {
    this.postStatus = postStatus;
    return this;
  }

  /**
   * Post Status Of Content
   * @return postStatus
  **/
  @ApiModelProperty(value = "Post Status Of Content")


  public String getPostStatus() {
    return postStatus;
  }

  public void setPostStatus(String postStatus) {
    this.postStatus = postStatus;
  }

  public ShareResponseData message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Text Message Of Content
   * @return message
  **/
  @ApiModelProperty(value = "Text Message Of Content")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ShareResponseData fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * FileType Of Content
   * @return fileType
  **/
  @ApiModelProperty(value = "FileType Of Content")


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public ShareResponseData location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Location Of Content
   * @return location
  **/
  @ApiModelProperty(value = "Location Of Content")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public ShareResponseData toUserId(Integer toUserId) {
    this.toUserId = toUserId;
    return this;
  }

  public ShareResponseData tooUserId(Integer toUserId) {
    this.toUserId = (toUserId);
    return this;
  }

  /**
   * ToUserIds Of Content
   * @return toUserId
  **/
  @ApiModelProperty(value = "ToUserIds Of Content")


  public Integer getToUserId() {
    return toUserId;
  }

  public void setToUserId(Integer toUserId) {
    this.toUserId = toUserId;
  }

  public ShareResponseData shareCount(Integer shareCount) {
    this.shareCount = shareCount;
    return this;
  }

  /**
   * Get shareCount
   * @return shareCount
  **/
  @ApiModelProperty(value = "")


  public Integer getShareCount() {
    return shareCount;
  }

  public void setShareCount(Integer shareCount) {
    this.shareCount = shareCount;
  }

  public ShareResponseData likeCount(Integer likeCount) {
    this.likeCount = likeCount;
    return this;
  }

  /**
   * Get likeCount
   * @return likeCount
  **/
  @ApiModelProperty(value = "")


  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public ShareResponseData commentCount(Integer commentCount) {
    this.commentCount = commentCount;
    return this;
  }

  /**
   * Get commentCount
   * @return commentCount
  **/
  @ApiModelProperty(value = "")


  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public ShareResponseData viewAmount(Integer viewAmount) {
    this.viewAmount = viewAmount;
    return this;
  }

  /**
   * Get viewAmount
   * @return viewAmount
  **/
  @ApiModelProperty(value = "")


  public Integer getViewAmount() {
    return viewAmount;
  }

  public void setViewAmount(Integer viewAmount) {
    this.viewAmount = viewAmount;
  }

  public ShareResponseData createTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   * @return createTime
  **/
  @ApiModelProperty(value = "")
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public ShareResponseData lastUpdateTime(LocalDateTime lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
    return this;
  }

  /**
   * Get lastUpdateTime
   * @return lastUpdateTime
  **/
  @ApiModelProperty(value = "")
  public LocalDateTime getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

}

