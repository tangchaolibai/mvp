package com.hsb.mvpmsweb.model.payload.response;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.FollowingUserByUserNameResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Following User By UserName Response.
 */
@ApiModel(description = "This entity is used to describe a Following User By UserName Response.")
@Validated

public class FollowingUserByUserNameResponse   {
  @JsonProperty("data")
  private FollowingUserByUserNameResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public FollowingUserByUserNameResponse data(FollowingUserByUserNameResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public FollowingUserByUserNameResponseData getData() {
    return data;
  }

  public void setData(FollowingUserByUserNameResponseData data) {
    this.data = data;
  }

  public FollowingUserByUserNameResponse result(ResponseResult result) {
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
    FollowingUserByUserNameResponse followingUserByUserNameResponse = (FollowingUserByUserNameResponse) o;
    return Objects.equals(this.data, followingUserByUserNameResponse.data) &&
        Objects.equals(this.result, followingUserByUserNameResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowingUserByUserNameResponse {\n");
    
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

