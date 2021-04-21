package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * FriendListResponseData
 */
@Validated

public class FriendListResponseData   {
  @JsonProperty("fansList")
  @Valid
  private List<FriendResponseData> fansList = null;

  public FriendListResponseData fansList(List<FriendResponseData> fansList) {
    this.fansList = fansList;
    return this;
  }

  public FriendListResponseData addFansListItem(FriendResponseData fansListItem) {
    if (this.fansList == null) {
      this.fansList = new ArrayList<>();
    }
    this.fansList.add(fansListItem);
    return this;
  }

  /**
   * List All Fans
   * @return fansList
  **/
  @ApiModelProperty(value = "List All Fans")

  @Valid

  public List<FriendResponseData> getFansList() {
    return fansList;
  }

  public void setFansList(List<FriendResponseData> fansList) {
    this.fansList = fansList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FriendListResponseData friendListResponseData = (FriendListResponseData) o;
    return Objects.equals(this.fansList, friendListResponseData.fansList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fansList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FriendListResponseData {\n");
    
    sb.append("    fansList: ").append(toIndentedString(fansList)).append("\n");
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

