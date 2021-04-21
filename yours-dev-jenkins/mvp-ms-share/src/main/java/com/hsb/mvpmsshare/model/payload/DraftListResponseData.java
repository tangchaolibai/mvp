package com.hsb.mvpmsshare.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsshare.model.payload.PhotosData;
import com.hsb.mvpmsshare.model.payload.VideosData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * This entity is used to describe an Content response data.
 */
@ApiModel(description = "This entity is used to describe an Content response data.")
@Validated

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
	private Integer toUserId = null;

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

	public DraftListResponseData shareId(Integer shareId) {
		this.shareId = shareId;
		return this;
	}

	/**
	 * Unique identifier of Post Content
	 * 
	 * @return shareId
	 **/
	@ApiModelProperty(value = "Unique identifier of Post Content")

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public DraftListResponseData userId(Integer userId) {
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

	public DraftListResponseData postStatus(String postStatus) {
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

	public DraftListResponseData message(String message) {
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

	public DraftListResponseData photos(List<PhotosData> photos) {
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

	public DraftListResponseData videos(VideosData videos) {
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

	public DraftListResponseData fileType(String fileType) {
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

	public DraftListResponseData location(String location) {
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

	public DraftListResponseData toUserId(Integer toUserId) {
		this.toUserId = toUserId;
		return this;
	}

	/**
	 * ToUserId of Content
	 * 
	 * @return toUserId
	 **/
	@ApiModelProperty(value = "ToUserId of Content")

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public DraftListResponseData shareCount(Integer shareCount) {
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

	public DraftListResponseData likeCount(Integer likeCount) {
		this.likeCount = likeCount;
		return this;
	}

	/**
	 * likeCount of Content
	 * 
	 * @return shareCount
	 **/
	@ApiModelProperty(value = "likeCount of Content")

	public Integer geLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public DraftListResponseData commentCount(Integer commentCount) {
		this.commentCount = commentCount;
		return this;
	}

	/**
	 * CommentCount of Content
	 * 
	 * @return shareCount
	 **/
	@ApiModelProperty(value = "CommentCount of Content")

	public Integer geCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public DraftListResponseData viewAmount(Integer viewAmount) {
		this.commentCount = viewAmount;
		return this;
	}

	/**
	 * viewAmount of Content
	 * 
	 * @return shareCount
	 **/
	@ApiModelProperty(value = "viewAmount of Content")

	public Integer geViewAmount() {
		return viewAmount;
	}

	public void setViewAmount(Integer viewAmount) {
		this.viewAmount = viewAmount;
	}

	public DraftListResponseData createTime(LocalDateTime createTime) {
		this.createTime = createTime;
		return this;
	}

	/**
	 * CreateTime of Content
	 * 
	 * @return createTime
	 **/
	@ApiModelProperty(value = "CreateTime of Content")

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public DraftListResponseData lastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
		return this;
	}

	/**
	 * Last Update Time of Content
	 * 
	 * @return lastUpdateTime
	 **/
	@ApiModelProperty(value = "Last Update Time of Content")

	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DraftListResponseData postContentResponseData = (DraftListResponseData) o;
		return Objects.equals(this.shareId, postContentResponseData.shareId)
				&& Objects.equals(this.userId, postContentResponseData.userId)
				&& Objects.equals(this.postStatus, postContentResponseData.postStatus)
				&& Objects.equals(this.message, postContentResponseData.message)
				&& Objects.equals(this.photos, postContentResponseData.photos)
				&& Objects.equals(this.videos, postContentResponseData.videos)
				&& Objects.equals(this.fileType, postContentResponseData.fileType)
				&& Objects.equals(this.location, postContentResponseData.location)
				&& Objects.equals(this.toUserId, postContentResponseData.toUserId)
				&& Objects.equals(this.shareCount, postContentResponseData.shareCount)
				&& Objects.equals(this.createTime, postContentResponseData.createTime)
				&& Objects.equals(this.lastUpdateTime, postContentResponseData.lastUpdateTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(shareId, userId, postStatus, message, photos, videos, fileType, location, toUserId,
				shareCount, createTime, lastUpdateTime);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostContentResponseData {\n");

		sb.append("    shareId: ").append(toIndentedString(shareId)).append("\n");
		sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
		sb.append("    postStatus: ").append(toIndentedString(postStatus)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
		sb.append("    videos: ").append(toIndentedString(videos)).append("\n");
		sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
		sb.append("    location: ").append(toIndentedString(location)).append("\n");
		sb.append("    toUserId: ").append(toIndentedString(toUserId)).append("\n");
		sb.append("    shareCount: ").append(toIndentedString(shareCount)).append("\n");
		sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
		sb.append("    lastUpdateTime: ").append(toIndentedString(lastUpdateTime)).append("\n");
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
