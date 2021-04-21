package com.hsb.mvpmsshare.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * ShareListResponseData
 */
@Validated

public class ShareListResponseData   {
  @JsonProperty("shareList")
  private List<ShareInfoData> shareList = null;

  public ShareListResponseData shareList(List<ShareInfoData> shareList) {
    this.shareList = shareList;
    return this;
  }

  public ShareListResponseData addShareListItem(ShareInfoData shareListItem) {
    if (this.shareList == null) {
      this.shareList = new ArrayList<>();
    }
    this.shareList.add(shareListItem);
    return this;
  }

  /**
   * List Of Share
   * @return shareList
  **/
  @ApiModelProperty(value = "List Of Share")
  public List<ShareInfoData> getShareList() {
    return shareList;
  }

  public void setShareList(List<ShareInfoData> shareList) {
    this.shareList = shareList;
  }

}

