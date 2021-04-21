package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get likes response data.
 */
@ApiModel(description = "This entity is used to get likes response data.")
@Validated
public class GetLikesResponseData {
	@JsonProperty("likes")
	private List<LikeResponseData> likes = null;

	public GetLikesResponseData likes(List<LikeResponseData> likes) {
		this.likes = likes;
		return this;
	}

	public GetLikesResponseData addLikesItem(LikeResponseData likesItem) {
		if (this.likes == null) {
			this.likes = new ArrayList<>();
		}
		this.likes.add(likesItem);
		return this;
	}

	/**
	 * likes
	 * @return likes
	 **/
	@ApiModelProperty(value = "likes")
	public List<LikeResponseData> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeResponseData> likes) {
		this.likes = likes;
	}

}
