package com.hsb.mvpmsshare.model.payload;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * FollowingListResponseData
 */
@Validated

public class FollowingListResponseData   {
	
  @JsonProperty("followingListData")
  private List<ShareInfoData> followingListData = null;

  @JsonProperty("recentShareCount")
  private Integer recentShareCount = null;

  public FollowingListResponseData followingListData(List<ShareInfoData> followingListData) {
    this.followingListData = followingListData;
    return this;
  }

  public FollowingListResponseData addFollowingListDataItem(ShareInfoData followingListDataItem) {
    if (this.followingListData == null) {
      this.followingListData = new ArrayList<>();
    }
    this.followingListData.add(followingListDataItem);
    return this;
  }

  
  @ApiModelProperty(value = "")
  public List<ShareInfoData> getFollowingListData() {
    return followingListData;
  }

  public void setFollowingListData(List<ShareInfoData> followingListData) {
    this.followingListData = followingListData;
  }

  public FollowingListResponseData recentShareCount(Integer recentShareCount) {
    this.recentShareCount = recentShareCount;
    return this;
  }

  /**
   * The Recent Refleshing Count Of Following Share
   * @return recentShareCount
  **/
  @ApiModelProperty(value = "The Recent Refleshing Count Of Following Share")


  public Integer getRecentShareCount() {
    return recentShareCount;
  }

  public void setRecentShareCount(Integer recentShareCount) {
    this.recentShareCount = recentShareCount;
  }

}

