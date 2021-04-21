package com.hsb.mvpmsshare.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ShareInfoData
 */
@Validated

public class ShareInfoData   {
  @JsonProperty("share")
  private ShareResponseData share = null;

  @JsonProperty("thumbnailUrl")
  private String thumbnailUrl = null;

  public ShareInfoData share(ShareResponseData share) {
    this.share = share;
    return this;
  }

  /**
   * Get share
   * @return share
  **/
  @ApiModelProperty(value = "")
  public ShareResponseData getShare() {
    return share;
  }

  public void setShare(ShareResponseData share) {
    this.share = share;
  }

  public ShareInfoData thumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  /**
   * Get thumbnailUrl
   * @return thumbnailUrl
  **/
  @ApiModelProperty(value = "")


  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

}

