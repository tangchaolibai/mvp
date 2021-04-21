package com.hsb.mvpmsshare.model.payload;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FriendByMobileContactsResponseData
 */
@Validated

public class FriendByMobileContactsResponseData {

	@JsonProperty("MobileContacts")
	@Valid
	private List<String> mobileContacts = null;

	public List<String> getMobileContacts() {
		return mobileContacts;
	}

	public void setMobileContacts(List<String> mobileContacts) {
		this.mobileContacts = mobileContacts;
	}

}
