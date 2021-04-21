package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a discover response data.
 */
@ApiModel(description = "This entity is used to describe a discover response data.")
@Validated

public class DiscoverResponseData   {
  @JsonProperty("likeCount")
  private Integer likeCount = null;

  @JsonProperty("isLike")
  private Boolean isLike = null;

  @JsonProperty("commentCount")
  private Integer commentCount = null;

  @JsonProperty("shareCount")
  private Integer shareCount = null;

  public DiscoverResponseData likeCount(Integer likeCount) {
    this.likeCount = likeCount;
    return this;
  }

  /**
   * Like Share Count
   * @return likeCount
  **/
  @ApiModelProperty(value = "Like Share Count")


  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public DiscoverResponseData isLike(Boolean isLike) {
    this.isLike = isLike;
    return this;
  }

  /**
   * Is Like Share
   * @return isLike
  **/
  @ApiModelProperty(value = "Is Like Share")


  public Boolean isIsLike() {
    return isLike;
  }

  public void setIsLike(Boolean isLike) {
    this.isLike = isLike;
  }

  public DiscoverResponseData commentCount(Integer commentCount) {
    this.commentCount = commentCount;
    return this;
  }

  /**
   * Share Comment Count
   * @return commentCount
  **/
  @ApiModelProperty(value = "Share Comment Count")


  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public DiscoverResponseData shareCount(Integer shareCount) {
    this.shareCount = shareCount;
    return this;
  }

  /**
   * Share Count
   * @return shareCount
  **/
  @ApiModelProperty(value = "Share Count")


  public Integer getShareCount() {
    return shareCount;
  }

  public void setShareCount(Integer shareCount) {
    this.shareCount = shareCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DiscoverResponseData discoverResponseData = (DiscoverResponseData) o;
    return Objects.equals(this.likeCount, discoverResponseData.likeCount) &&
        Objects.equals(this.isLike, discoverResponseData.isLike) &&
        Objects.equals(this.commentCount, discoverResponseData.commentCount) &&
        Objects.equals(this.shareCount, discoverResponseData.shareCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(likeCount, isLike, commentCount, shareCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DiscoverResponseData {\n");
    
    sb.append("    likeCount: ").append(toIndentedString(likeCount)).append("\n");
    sb.append("    isLike: ").append(toIndentedString(isLike)).append("\n");
    sb.append("    commentCount: ").append(toIndentedString(commentCount)).append("\n");
    sb.append("    shareCount: ").append(toIndentedString(shareCount)).append("\n");
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

