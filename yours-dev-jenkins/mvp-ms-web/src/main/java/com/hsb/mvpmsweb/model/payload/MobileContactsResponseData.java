package com.hsb.mvpmsweb.model.payload;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@Valid
@ToString
public class MobileContactsResponseData {

	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("mobilePhone")
	private String mobilePhone;
	
	@JsonProperty("initials")
	private String initials;
	
}
