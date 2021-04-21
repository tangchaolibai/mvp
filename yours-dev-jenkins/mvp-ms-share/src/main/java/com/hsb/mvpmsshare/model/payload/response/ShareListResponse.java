package com.hsb.mvpmsshare.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.ResponseResult;
import com.hsb.mvpmsshare.model.payload.ShareListResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Share List Response.
 */
@ApiModel(description = "This entity is used to describe a Share List Response.")
@Validated

public class ShareListResponse   {
  @JsonProperty("data")
  private ShareListResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public ShareListResponse data(ShareListResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public ShareListResponseData getData() {
    return data;
  }

  public void setData(ShareListResponseData data) {
    this.data = data;
  }

  public ShareListResponse result(ResponseResult result) {
    this.result = result;
    return this;
  }

  /**
   * Response Result.
   * @return result
  **/
  @ApiModelProperty(value = "Response Result.")
  public ResponseResult getResult() {
    return result;
  }

  public void setResult(ResponseResult result) {
    this.result = result;
  }

}

