package com.hsb.mvpmsweb.model.payload.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.MobileContactsRequestData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe a Friend By Mobile Contacts Request")
@Data
@Accessors(fluent = true)
public class FriendByMobileContactsRequest {
	
	@JsonProperty("mobileContactsList")
	@ApiModelProperty(required = true,value = "Mobile Contacts List")
	private List<MobileContactsRequestData> mobileContactsList;
	
	@JsonProperty("searchContent")
	@ApiModelProperty(required = true,value = "Search Content")
	private String searchContent;

}
