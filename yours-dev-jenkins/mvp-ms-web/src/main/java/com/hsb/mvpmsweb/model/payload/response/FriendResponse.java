package com.hsb.mvpmsweb.model.payload.response;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.FriendResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This entity is used to describe a Friend response.
 */
@ApiModel(description = "This entity is used to describe a Friend response.")
@Validated

public class FriendResponse   {
  @JsonProperty("data")
  private FriendResponseData data = null;

  @JsonProperty("result")
  private ResponseResult result = null;

  public FriendResponse data(FriendResponseData data) {
    this.data = data;
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public FriendResponseData getData() {
    return data;
  }

  public void setData(FriendResponseData data) {
    this.data = data;
  }

  public FriendResponse result(ResponseResult result) {
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
    FriendResponse friendResponse = (FriendResponse) o;
    return Objects.equals(this.data, friendResponse.data) &&
        Objects.equals(this.result, friendResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendResponse {\n");
    
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

