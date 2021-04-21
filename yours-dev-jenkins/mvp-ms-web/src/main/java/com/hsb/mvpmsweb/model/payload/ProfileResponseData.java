package com.hsb.mvpmsweb.model.payload;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@Validated
@ApiModel(description = "This entity is used to describe a profile response data.")
@ToString
public class ProfileResponseData {

	 @JsonProperty("shareList")
	 private List<ShareInfoData> shareList = null;
	 
	 @JsonProperty("followerCount")
	 private Integer followerCount;
	 
	 @JsonProperty("followingCount")
	 private Integer followingCount;
	 
	 @JsonProperty("likedCount")
	 private Integer likedCount;
	 
	 @JsonProperty("isFollowing")
	 private Boolean isFollowing;

}
