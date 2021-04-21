package com.hsb.mvpmsweb.model.payload;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a photo response data.
 */
@ApiModel(description = "This entity is used to describe a photo response data.")
@Validated

public class PhotosData   {
	
  @JsonProperty("photoId")
  private Integer photoId = null;

  @JsonProperty("photoName")
  private String photoName = null;

  @JsonProperty("photoDes")
  private String photoDes = null;

  @JsonProperty("photoLoc")
  private String photoLoc = null;

  @JsonProperty("photoPath")
  private String photoPath = null;

  public PhotosData photoName(String photoName) {
    this.photoName = photoName;
    return this;
  }

  /**
   * photoName of photo
   * @return photoName
  **/
  @ApiModelProperty(value = "photoName of photo")


  public String getPhotoName() {
    return photoName;
  }

  public void setPhotoName(String photoName) {
    this.photoName = photoName;
  }

  public PhotosData photoDes(String photoDes) {
    this.photoDes = photoDes;
    return this;
  }

  /**
   * photoDes of photo
   * @return photoDes
  **/
  @ApiModelProperty(value = "photoDes of photo")


  public String getPhotoDes() {
    return photoDes;
  }

  public void setPhotoDes(String photoDes) {
    this.photoDes = photoDes;
  }

  public PhotosData photoLoc(String photoLoc) {
    this.photoLoc = photoLoc;
    return this;
  }

  /**
   * photoName of photo
   * @return photoLoc
  **/
  @ApiModelProperty(value = "photoName of photo")


  public String getPhotoLoc() {
    return photoLoc;
  }

  public void setPhotoLoc(String photoLoc) {
    this.photoLoc = photoLoc;
  }

  public PhotosData photoPath(String photoPath) {
    this.photoPath = photoPath;
    return this;
  }

  /**
   * photoName of photo
   * @return photoPath
  **/
  @ApiModelProperty(value = "photoName of photo")


  public String getPhotoPath() {
    return photoPath;
  }

  public void setPhotoPath(String photoPath) {
    this.photoPath = photoPath;
  }
  
  public Integer getPhotoId() {
	return photoId;
  }

  public void setPhotoId(Integer photoId) {
	this.photoId = photoId;
  }

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhotosData photosData = (PhotosData) o;
    return Objects.equals(this.photoName, photosData.photoName) &&
        Objects.equals(this.photoDes, photosData.photoDes) &&
        Objects.equals(this.photoLoc, photosData.photoLoc) &&
        Objects.equals(this.photoPath, photosData.photoPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(photoName, photoDes, photoLoc, photoPath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhotosData {\n");
    
    sb.append("    photoName: ").append(toIndentedString(photoName)).append("\n");
    sb.append("    photoDes: ").append(toIndentedString(photoDes)).append("\n");
    sb.append("    photoLoc: ").append(toIndentedString(photoLoc)).append("\n");
    sb.append("    photoPath: ").append(toIndentedString(photoPath)).append("\n");
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

