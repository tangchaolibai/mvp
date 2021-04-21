package com.hsb.mvpmsweb.model.payload.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe like of Content Request.
 */
@ApiModel(description = "This entity is used to describe like of Content Request.")
@Validated

public class SocialLikeRequest   {
  @JsonProperty("shareId")
  private Integer shareId = null;

  @JsonProperty("fromUserId")
  private Integer fromUserId = null;

  @JsonProperty("like")
  private String likeStatus = null;

  
  public SocialLikeRequest shareId(Integer shareId) {
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

  public SocialLikeRequest likeStatus(String likeStatus) {
    this.likeStatus = likeStatus;
    return this;
  }

  /**
   * like content or cancle like content
   * @return likeStatus
  **/
  @ApiModelProperty(value = "like content or cancle like content")


  public String getLikeStatus() {
    return likeStatus;
  }

  public void setLikeStatus(String likeStatus) {
    this.likeStatus = likeStatus;
  }

  
  public SocialLikeRequest fromUserId(Integer fromUserId) {
	    this.fromUserId = fromUserId;
	    return this;
	  }

	  /**
	   * userId of who like the content
	   * @return fromUserId
	  **/
	  @ApiModelProperty(value = "userId of who like the content")


	  public Integer getFromUserId() {
	    return fromUserId;
	  }

	  public void setFromUserId(Integer fromUserId) {
	    this.fromUserId = fromUserId;
	  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SocialLikeRequest socialLikeRequest = (SocialLikeRequest) o;
    return Objects.equals(this.shareId, socialLikeRequest.shareId) &&
        Objects.equals(this.likeStatus, socialLikeRequest.likeStatus)&&
        Objects.equals(this.fromUserId, socialLikeRequest.fromUserId) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(shareId, fromUserId,likeStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SocialLikeRequest {\n");
    
    sb.append("    shareId: ").append(toIndentedString(shareId)).append("\n");
    sb.append("    fromUserId: ").append(toIndentedString(fromUserId)).append("\n");
    sb.append("    likeStatus: ").append(toIndentedString(likeStatus)).append("\n");
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

