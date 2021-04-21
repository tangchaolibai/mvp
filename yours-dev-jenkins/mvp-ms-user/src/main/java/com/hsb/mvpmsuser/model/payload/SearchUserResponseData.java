package com.hsb.mvpmsuser.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to describe search users  response data.
 */
@ApiModel(description = "This entity is used to describe search users response data.")
@Validated
@Data
@Accessors(fluent = true)
public class SearchUserResponseData {
	@JsonProperty("userId")
	private Integer userId = null;

	@JsonProperty("nickName")
	private String nickName = null;

	@JsonProperty(" followerCount")
	private Integer  followerCount = null;
	
	@JsonProperty("userImage")
	private String userImage = null;
}
