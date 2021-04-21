package com.hsb.mvpmsshare.model.payload.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.PhotosData;
import com.hsb.mvpmsshare.model.payload.VideosData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * This entity is used to describe a Content Request.
 */
@ApiModel(description = "This entity is used to describe a Content Request.")
@Validated

public class SocialShareRequest {
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

	@JsonProperty("toUserIds")
	@Valid
	private List<Integer> toUserIds = null;

	@JsonProperty("likeCount")
	private Integer likeCount = null;

	@JsonProperty("commentCount")
	private Integer commentCount = null;

	@JsonProperty("shareCount")
	private Integer shareCount = null;
	
	@JsonProperty("viewAmount")
	private Integer viewAmount = null;

	public SocialShareRequest userId(Integer userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * UserId of Content
	 * 
	 * @return userId
	 **/
	@ApiModelProperty(value = "UserId of Content")

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public SocialShareRequest postStatus(String postStatus) {
		this.postStatus = postStatus;
		return this;
	}

	/**
	 * Post Status of Content
	 * 
	 * @return postStatus
	 **/
	@ApiModelProperty(value = "Post Status of Content")

	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	public SocialShareRequest message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Text Message of Content
	 * 
	 * @return message
	 **/
	@ApiModelProperty(value = "Text Message of Content")

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SocialShareRequest photos(List<PhotosData> photos) {
		this.photos = photos;
		return this;
	}

	/**
	 * Get photos
	 * 
	 * @return photos
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public List<PhotosData> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotosData> photos) {
		this.photos = photos;
	}

	public SocialShareRequest videos(VideosData videos) {
		this.videos = videos;
		return this;
	}

	/**
	 * Get videos
	 * 
	 * @return videos
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public VideosData getVideos() {
		return videos;
	}

	public void setVideos(VideosData videos) {
		this.videos = videos;
	}

	public SocialShareRequest fileType(String fileType) {
		this.fileType = fileType;
		return this;
	}

	/**
	 * FileType of Content
	 * 
	 * @return fileType
	 **/
	@ApiModelProperty(value = "FileType of Content")

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public SocialShareRequest location(String location) {
		this.location = location;
		return this;
	}

	/**
	 * Location of Content
	 * 
	 * @return location
	 **/
	@ApiModelProperty(value = "Location of Content")

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public SocialShareRequest toUserIds(List<Integer> toUserIds) {
		this.toUserIds = toUserIds;
		return this;
	}

	public SocialShareRequest addToUserIdsItem(Integer toUserIdsItem) {
		if (this.toUserIds == null) {
			this.toUserIds = new ArrayList<>();
		}
		this.toUserIds.add(toUserIdsItem);
		return this;
	}

	/**
	 * ToUserIds of Content
	 * 
	 * @return toUserIds
	 **/
	@ApiModelProperty(value = "ToUserIds of Content")

	public List<Integer> getToUserIds() {
		return toUserIds;
	}

	public void setToUserIds(List<Integer> toUserIds) {
		this.toUserIds = toUserIds;
	}

	public SocialShareRequest likeCount(Integer likeCount) {
		this.likeCount = likeCount;
		return this;
	}

	/**
	 * LikeCount of Content
	 * 
	 * @return likeCount
	 **/
	@ApiModelProperty(value = "likeCount of Content")

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public SocialShareRequest commentCount(Integer commentCount) {
		this.commentCount = commentCount;
		return this;
	}

	/**
	 * CommentCount of Content
	 * 
	 * @return commentCount
	 **/
	@ApiModelProperty(value = "commentCount of Content")

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public SocialShareRequest shareCount(Integer shareCount) {
		this.shareCount = shareCount;
		return this;
	}

	/**
	 * ShareCount of Content
	 * 
	 * @return shareCount
	 **/
	@ApiModelProperty(value = "ShareCount of Content")

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public SocialShareRequest viewAmount(Integer viewAmount) {
		this.viewAmount = viewAmount;
		return this;
	}

	/**
	 * ViewAmount of Content
	 * 
	 * @return viewAmount
	 **/
	@ApiModelProperty(value = "ViewAmount of Content")

	public Integer getViewAmount() {
		return viewAmount;
	}

	public void setViewAmount(Integer viewAmount) {
		this.viewAmount = viewAmount;
	}
	
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SocialShareRequest socialShareRequest = (SocialShareRequest) o;
		return Objects.equals(this.userId, socialShareRequest.userId)
				&& Objects.equals(this.postStatus, socialShareRequest.postStatus)
				&& Objects.equals(this.message, socialShareRequest.message)
				&& Objects.equals(this.photos, socialShareRequest.photos)
				&& Objects.equals(this.videos, socialShareRequest.videos)
				&& Objects.equals(this.fileType, socialShareRequest.fileType)
				&& Objects.equals(this.location, socialShareRequest.location)
				&& Objects.equals(this.toUserIds, socialShareRequest.toUserIds)
				&& Objects.equals(this.shareCount, socialShareRequest.shareCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, postStatus, message, photos, videos, fileType, location, toUserIds,
				shareCount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SocialShareRequest {\n");

		sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
		sb.append("    postStatus: ").append(toIndentedString(postStatus)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
		sb.append("    videos: ").append(toIndentedString(videos)).append("\n");
		sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
		sb.append("    location: ").append(toIndentedString(location)).append("\n");
		sb.append("    toUserIds: ").append(toIndentedString(toUserIds)).append("\n");
		sb.append("    shareCount: ").append(toIndentedString(shareCount)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
