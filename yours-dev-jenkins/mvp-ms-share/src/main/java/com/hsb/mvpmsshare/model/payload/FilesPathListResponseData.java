package com.hsb.mvpmsshare.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * FilesPathListResponseData
 */
@Validated

public class FilesPathListResponseData   {
  @JsonProperty("filesPathList")
  @Valid
  private List<String> filesPathList = null;

  public FilesPathListResponseData filesPathList(List<String> filesPathList) {
	    this.filesPathList = filesPathList;
	    return this;
	  }

	  public FilesPathListResponseData addShareListItem(String filesPathListItem) {
	    if (this.filesPathList == null) {
	      this.filesPathList = new ArrayList<>();
	    }
	    this.filesPathList.add(filesPathListItem);
	    return this;
	  }

	  /**
	   * List Of Share
	   * @return filesPathList
	  **/
	  @ApiModelProperty(value = "List Of Share")
	  public List<String> getFilesPathList() {
	    return filesPathList;
	  }

	  public void setFilesPathList(List<String> filesPathList) {
	    this.filesPathList = filesPathList;
	  }
}

