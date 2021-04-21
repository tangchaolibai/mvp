package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a discover response data.
 */
@Data
@ApiModel(description = "This entity is used to describe a discover response data.")
@Validated
@ToString
public class DiscoverResponseData {

	@JsonProperty("likeCount")
	private Integer likeCount = null;

	@JsonProperty("isLike")
	private Boolean isLike = null;

	@JsonProperty("commentCount")
	private Integer commentCount = null;

	@JsonProperty("shareCount")
	private Integer shareCount = null;

}

