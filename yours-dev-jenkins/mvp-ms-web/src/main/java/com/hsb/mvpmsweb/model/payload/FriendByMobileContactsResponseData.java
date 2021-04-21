package com.hsb.mvpmsweb.model.payload;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * FriendByMobileContactsResponseData
 */
@Data
@Validated
@ToString
public class FriendByMobileContactsResponseData {

	@JsonProperty("MobileContacts")
	@Valid
	private List<MobileContactsResponseData> mobileContacts = null;

}
