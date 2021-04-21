package com.hsb.mvpmsshare.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.ResponseResult;
import com.hsb.mvpmsshare.model.payload.ShareRelationCountResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Share Relation Count Response.
 */
@ApiModel(description = "This entity is used to describe a Share Relation Count Response.")
@Validated

public class ShareRelationCountResponse   {
  @JsonProperty("data")
  private ShareRelationCountResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public ShareRelationCountResponse data(ShareRelationCountResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public ShareRelationCountResponseData getData() {
    return data;
  }

  public void setData(ShareRelationCountResponseData data) {
    this.data = data;
  }

  public ShareRelationCountResponse result(ResponseResult result) {
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

