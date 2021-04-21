package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to photo response data.
 */
@ApiModel(description = "This entity is used to photo response data.")
@Validated
public class PhotoResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("path")
	private String path = null;

	@JsonProperty("share_id")
	private Integer shareId = null;

	/**
	 * photo id
	 * @return id
	 **/
	@ApiModelProperty(value = "photo id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * photo name
	 * @return name
	 **/
	@ApiModelProperty(value = "photo name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * photo thumbnail path
	 * @return path
	 **/
	@ApiModelProperty(value = "photo thumbnail path")
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
