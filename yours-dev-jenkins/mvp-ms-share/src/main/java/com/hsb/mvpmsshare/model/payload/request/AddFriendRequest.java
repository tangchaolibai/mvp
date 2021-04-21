package com.hsb.mvpmsshare.model.payload.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a add friend request.
 */
@ApiModel(description = "This entity is used to describe a add friend request.")
@Validated

public class AddFriendRequest   {
  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("fanId")
  private Integer fanId = null;

  @JsonProperty("comfirm")
  private Boolean comfirm = null;

  public AddFriendRequest userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * User Id
   * @return userId
  **/
  @ApiModelProperty(value = "User Id")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public AddFriendRequest fanId(Integer fanId) {
    this.fanId = fanId;
    return this;
  }

  /**
   * Fan Id
   * @return fanId
  **/
  @ApiModelProperty(value = "Fan Id")


  public Integer getFanId() {
    return fanId;
  }

  public void setFanId(Integer fanId) {
    this.fanId = fanId;
  }

  public AddFriendRequest comfirm(Boolean comfirm) {
    this.comfirm = comfirm;
    return this;
  }

  /**
   * comfirm
   * @return comfirm
  **/
  @ApiModelProperty(value = "comfirm")


  public Boolean isComfirm() {
    return comfirm;
  }

  public void setComfirm(Boolean comfirm) {
    this.comfirm = comfirm;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddFriendRequest addFriendRequest = (AddFriendRequest) o;
    return Objects.equals(this.userId, addFriendRequest.userId) &&
        Objects.equals(this.fanId, addFriendRequest.fanId) &&
        Objects.equals(this.comfirm, addFriendRequest.comfirm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, fanId, comfirm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddFriendRequest {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    fanId: ").append(toIndentedString(fanId)).append("\n");
    sb.append("    comfirm: ").append(toIndentedString(comfirm)).append("\n");
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

