package com.hsb.mvpmsshare.model.payload.response;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.AllFollowingUserListResponseData;
import com.hsb.mvpmsshare.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe an All Following User List Response.
 */
@ApiModel(description = "This entity is used to describe an All Following User List Response.")
@Validated

public class AllFollowingUserListResponse   {
  @JsonProperty("data")
  private AllFollowingUserListResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public AllFollowingUserListResponse data(AllFollowingUserListResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AllFollowingUserListResponseData getData() {
    return data;
  }

  public void setData(AllFollowingUserListResponseData data) {
    this.data = data;
  }

  public AllFollowingUserListResponse result(ResponseResult result) {
    this.result = result;
    return this;
  }

  /**
   * Response Result.
   * @return result
  **/
  @ApiModelProperty(value = "Response Result.")

  @Valid

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
    AllFollowingUserListResponse allFollowingUserListResponse = (AllFollowingUserListResponse) o;
    return Objects.equals(this.data, allFollowingUserListResponse.data) &&
        Objects.equals(this.result, allFollowingUserListResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllFollowingUserListResponse {\n");
    
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

