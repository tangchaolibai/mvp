package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to like response data.
 */
@ApiModel(description = "This entity is used to like response data.")
@Validated
public class LikeResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("share_id")
	private Integer shareId = null;

	@JsonProperty("from_user_id")
	private Integer fromUserId = null;

	@JsonProperty("creation_date_time")
	private LocalDateTime creationDateTime = null;
	
	/**
	 * like id
	 * @return id
	 **/
	@ApiModelProperty(value = "like id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LikeResponseData fromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
		return this;
	}

	/**
	 * id of who like you
	 * @return fromUserId
	 **/
	@ApiModelProperty(value = "id of who like you")
	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	/**
	 * like time
	 * @return creation_date_time
	 **/
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

}
