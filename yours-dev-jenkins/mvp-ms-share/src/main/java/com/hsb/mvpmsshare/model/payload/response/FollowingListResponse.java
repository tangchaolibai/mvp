package com.hsb.mvpmsshare.model.payload.response;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.FollowingListResponseData;
import com.hsb.mvpmsshare.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Following List Response.
 */
@ApiModel(description = "This entity is used to describe a Following List Response.")
@Validated

public class FollowingListResponse   {
  @JsonProperty("data")
  private FollowingListResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public FollowingListResponse data(FollowingListResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public FollowingListResponseData getData() {
    return data;
  }

  public void setData(FollowingListResponseData data) {
    this.data = data;
  }

  public FollowingListResponse result(ResponseResult result) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FollowingListResponse followingListResponse = (FollowingListResponse) o;
    return Objects.equals(this.data, followingListResponse.data) &&
        Objects.equals(this.result, followingListResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowingListResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

