package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe a Mobile Contacts Request Data.")
@Data
public class MobileContactsRequestData {

	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("mobilePhone")
	private String mobilePhone;
	
}
