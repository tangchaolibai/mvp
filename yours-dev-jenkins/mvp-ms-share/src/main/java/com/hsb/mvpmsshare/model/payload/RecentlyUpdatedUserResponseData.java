package com.hsb.mvpmsshare.model.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * RecentlyUpdatedUserResponseData
 */
@Validated

public class RecentlyUpdatedUserResponseData   {
  @JsonProperty("userList")
  @Valid
  private List<UserDetailResponseData> userList = null;

  public RecentlyUpdatedUserResponseData userList(List<UserDetailResponseData> userList) {
    this.userList = userList;
    return this;
  }

  public RecentlyUpdatedUserResponseData addUserListItem(UserDetailResponseData userListItem) {
    if (this.userList == null) {
      this.userList = new ArrayList<>();
    }
    this.userList.add(userListItem);
    return this;
  }

  /**
   * Get userList
   * @return userList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<UserDetailResponseData> getUserList() {
    return userList;
  }

  public void setUserList(List<UserDetailResponseData> userList) {
    this.userList = userList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecentlyUpdatedUserResponseData recentlyUpdatedUserResponseData = (RecentlyUpdatedUserResponseData) o;
    return Objects.equals(this.userList, recentlyUpdatedUserResponseData.userList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecentlyUpdatedUserResponseData {\n");
    
    sb.append("    userList: ").append(toIndentedString(userList)).append("\n");
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

