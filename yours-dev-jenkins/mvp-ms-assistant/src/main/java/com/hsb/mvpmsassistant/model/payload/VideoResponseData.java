package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to videos response data.
 */
@ApiModel(description = "This entity is used to videos response data.")
@Validated
public class VideoResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("path")
	private String path = null;
	
	@JsonProperty("share_id")
	private Integer shareId = null;

	/**
	 * video id
	 * @return id
	 **/
	@ApiModelProperty(value = "video id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * video name
	 * @return name
	 **/
	@ApiModelProperty(value = "video name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * video thumbnail path
	 * @return path
	 **/
	@ApiModelProperty(value = "video thumbnail path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * share id
	 * @return shareId
	 **/
	@ApiModelProperty(value = "share id")
	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}
	
}
