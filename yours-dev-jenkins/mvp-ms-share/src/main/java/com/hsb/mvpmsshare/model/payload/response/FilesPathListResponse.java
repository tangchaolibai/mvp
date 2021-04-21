package com.hsb.mvpmsshare.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.FilesPathListResponseData;
import com.hsb.mvpmsshare.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a friend list response.
 */
@ApiModel(description = "This entity is used to describe a filesPathlist response.")
@Validated

public class FilesPathListResponse   {
  @JsonProperty("data")
  private FilesPathListResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;
  
  public FilesPathListResponse data(FilesPathListResponseData data) {
	    this.data = data;
	    return this;
	  }

	  /**
	   * Get data
	   * @return data
	  **/
	  @ApiModelProperty(value = "")
	  public FilesPathListResponseData getData() {
	    return data;
	  }

	  public void setData(FilesPathListResponseData data) {
	    this.data = data;
	  }

	  public FilesPathListResponse result(ResponseResult result) {
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

