package com.hsb.mvpmsweb.model.payload;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * RecentlyUpdatedUserResponseData
 */
@Data
@Validated
@ToString
public class RecentlyUpdatedUserResponseData {

	@JsonProperty("userList")
	@Valid
	private List<UserDetailResponseData> userList = null;

}

