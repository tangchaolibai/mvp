package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "This entity is used to describe an Invite Friend By Mobile Contacts Request")
@Data
public class InviteFriendByMobileContactsRequest {

	@JsonProperty("mobilePhone")
	@ApiModelProperty(required = true,value = "Mobile Phone")
	private String mobilePhone;
	
}
