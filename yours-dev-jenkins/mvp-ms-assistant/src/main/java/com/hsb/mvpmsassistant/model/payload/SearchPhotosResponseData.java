package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search photos response data.
 */
@ApiModel(description = "This entity is used to search photos response data.")
@Validated
public class SearchPhotosResponseData {
	@JsonProperty("photos")
	private List<PhotoResponseData> photos = null;

	/**
	 * search photos
	 * @return photos
	 **/
	@ApiModelProperty(value = "search photos")
	public List<PhotoResponseData> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoResponseData> photos) {
		this.photos = photos;
	}

}
