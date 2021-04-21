package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel(description = "This entity is used to my relation count response data.")
@Validated
@ToString
public class MyRelationCountResponseData {
	
	@JsonProperty("likedCount")
	private Integer likedCount;
	
	@JsonProperty("followerCount")
	private Integer followerCount;
	
	@JsonProperty("followingCount")
	private Integer followingCount;
	
	@JsonProperty("isFollowing")
	private boolean isFollowing;
	
}
