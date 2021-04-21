package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search videos response data.
 */
@ApiModel(description = "This entity is used to search videos response data.")
@Validated
public class SearchVideosResponseData {
	@JsonProperty("videos")
	private List<VideoResponseData> videos = null;

	/**
	 * search videos
	 * @return videos
	 **/
	@ApiModelProperty(value = "search videos")
	public List<VideoResponseData> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoResponseData> videos) {
		this.videos = videos;
	}

}
