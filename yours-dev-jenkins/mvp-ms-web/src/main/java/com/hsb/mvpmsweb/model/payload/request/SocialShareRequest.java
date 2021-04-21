package com.hsb.mvpmsweb.model.payload.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * This entity is used to describe a Content Request.
 */
@Data
@ApiModel(description = "This entity is used to describe a Content Request.")
@Validated
@ToString
public class SocialShareRequest {
	
	@JsonProperty("userId")
	private Integer userId = null;

	@JsonProperty("postStatus")
	private String postStatus = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("contentRequestData")
	private List<ContentRequestData> contentRequestData = null;

	@JsonProperty("fileType")
	private String fileType = null;

	@JsonProperty("location")
	private String location = null;

	@JsonProperty("toUserId")
	@Valid
	private List<Integer> toUserId = null;
	
	@JsonProperty("productUrl")
	private String productUrl;

}
