package com.hsb.mvpmsweb.model.payload;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * This entity is used to describe an Content response data.
 */
@Data
@ApiModel(description = "This entity is used to describe an Content response data.")
@Validated
@ToString
public class DraftListResponseData {

	@JsonProperty("shareId")
	private Integer shareId = null;

	@JsonProperty("userId")
	private Integer userId = null;

	@JsonProperty("postStatus")
	private String postStatus = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("photos")
	private List<PhotosData> photos = null;

	@JsonProperty("videos")
	private VideosData videos = null;

	@JsonProperty("fileType")
	private String fileType = null;

	@JsonProperty("location")
	private String location = null;

	@JsonProperty("toUserId")
	@Valid
	private String toUserId = null;

	@JsonProperty("shareCount")
	private Integer shareCount = null;

	@JsonProperty("likeCount")
	private Integer likeCount = null;

	@JsonProperty("commentCount")
	private Integer commentCount = null;

	@JsonProperty("viewAmount")
	private Integer viewAmount = null;

	@JsonProperty("createTime")
	private LocalDateTime createTime = null;

	@JsonProperty("lastUpdateTime")
	private LocalDateTime lastUpdateTime = null;
	
	@JsonProperty("productUrl")
	private String productUrl;

}
