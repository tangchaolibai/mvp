package com.hsb.mvpmsweb.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a video response data.
 */
@ApiModel(description = "This entity is used to describe a video response data.")
@Validated

public class VideosData   {
	
  @JsonProperty("videoId")
  private Integer videoId = null;

  @JsonProperty("videoName")
  private String videoName = null;

  @JsonProperty("videoDes")
  private String videoDes = null;

  @JsonProperty("videoLoc")
  private String videoLoc = null;

  @JsonProperty("videoPath")
  private String videoPath = null;
  
  @JsonProperty("videoSeconds")
  private Integer videoSeconds = null;

  @JsonProperty("thumbnailUrl")
  private String thumbnailUrl = null;

  public VideosData videoName(String videoName) {
    this.videoName = videoName;
    return this;
  }

  /**
   * photoName of video
   * @return videoName
  **/
  @ApiModelProperty(value = "photoName of video")


  public String getVideoName() {
    return videoName;
  }

  public void setVideoName(String videoName) {
    this.videoName = videoName;
  }

  public VideosData videoDes(String videoDes) {
    this.videoDes = videoDes;
    return this;
  }

  /**
   * photoDes of video
   * @return videoDes
  **/
  @ApiModelProperty(value = "photoDes of video")


  public String getVideoDes() {
    return videoDes;
  }

  public void setVideoDes(String videoDes) {
    this.videoDes = videoDes;
  }

  public VideosData videoPath(String videoPath) {
    this.videoLoc = videoPath;
    return this;
  }

  /**
   * videoPath of video
   * @return videoPath
  **/
  @ApiModelProperty(value = "videoPath of video")


  public String getVideoPath() {
    return videoPath;
  }

  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
  }
  
  public VideosData videoLoc(String videoLoc) {
	    this.videoLoc = videoLoc;
	    return this;
	  }

	  /**
	   * photoName of video
	   * @return videoLoc
	  **/
	  @ApiModelProperty(value = "photoName of video")


	  public String getVideoLoc() {
	    return videoLoc;
	  }

	  public void setVideoLoc(String videoLoc) {
	    this.videoLoc = videoLoc;
	  }

  public VideosData videoSeconds(Integer videoSeconds) {
    this.videoSeconds = videoSeconds;
    return this;
  }

  /**
   * photoName of video
   * @return videoSeconds
  **/
  @ApiModelProperty(value = "photoName of video")


  public Integer getVideoSeconds() {
    return videoSeconds;
  }

  public void setVideoSeconds(Integer videoSeconds) {
    this.videoSeconds = videoSeconds;
  }

  public VideosData thumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  /**
   * thumbnailUrl of video
   * @return thumbnailUrl
  **/
  @ApiModelProperty(value = "thumbnailUrl of video")


  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  public Integer getVideoId() {
	return videoId;
  }

  public void setVideoId(Integer videoId) {
	this.videoId = videoId;
  }

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VideosData videosData = (VideosData) o;
    return Objects.equals(this.videoName, videosData.videoName) &&
        Objects.equals(this.videoDes, videosData.videoDes) &&
        Objects.equals(this.videoLoc, videosData.videoLoc) &&
        Objects.equals(this.videoPath, videosData.videoPath) &&
        Objects.equals(this.videoSeconds, videosData.videoSeconds) &&
        Objects.equals(this.thumbnailUrl, videosData.thumbnailUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(videoName, videoDes, videoLoc, videoPath, videoSeconds, thumbnailUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VideosData {\n");
    
    sb.append("    videoName: ").append(toIndentedString(videoName)).append("\n");
    sb.append("    videoDes: ").append(toIndentedString(videoDes)).append("\n");
    sb.append("    videoLoc: ").append(toIndentedString(videoLoc)).append("\n");
    sb.append("    videoPath: ").append(toIndentedString(videoPath)).append("\n");
    sb.append("    videoSeconds: ").append(toIndentedString(videoSeconds)).append("\n");
    sb.append("    thumbnailUrl: ").append(toIndentedString(thumbnailUrl)).append("\n");
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

