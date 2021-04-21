package com.hsb.mvpmsshare.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * AllFanListResponseData
 */
@Validated

public class AllFanListResponseData   {
  @JsonProperty("userList")
  @Valid
  private List<UserDetailResponseData> userList = null;

  public AllFanListResponseData userList(List<UserDetailResponseData> userList) {
    this.userList = userList;
    return this;
  }

  public AllFanListResponseData addUserListItem(UserDetailResponseData userListItem) {
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
  public List<UserDetailResponseData> getUserList() {
    return userList;
  }

  public void setUserList(List<UserDetailResponseData> userList) {
    this.userList = userList;
  }

}

