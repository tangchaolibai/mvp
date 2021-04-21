package com.hsb.mvpmsshare.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.AllFanListResponseData;
import com.hsb.mvpmsshare.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe an All Fan List Response.
 */
@ApiModel(description = "This entity is used to describe an All Fan List Response.")
@Validated
public class AllFanListResponse   {
  @JsonProperty("data")
  private AllFanListResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public AllFanListResponse data(AllFanListResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public AllFanListResponseData getData() {
    return data;
  }

  public void setData(AllFanListResponseData data) {
    this.data = data;
  }

  public AllFanListResponse result(ResponseResult result) {
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

