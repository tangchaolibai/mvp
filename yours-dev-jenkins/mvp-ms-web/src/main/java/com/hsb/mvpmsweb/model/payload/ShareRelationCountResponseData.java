package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * ShareRelationCountResponseData
 */
@Data
@Validated
@ToString
public class ShareRelationCountResponseData {

	@JsonProperty("commentCount")
	private Integer commentCount = null;

	@JsonProperty("shareCount")
	private Integer shareCount = null;

	@JsonProperty("likeCount")
	private Integer likeCount = null;

}

