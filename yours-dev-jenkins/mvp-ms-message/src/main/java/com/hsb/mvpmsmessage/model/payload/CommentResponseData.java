package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to comment response data.
 */
@ApiModel(description = "This entity is used to comment response data.")
@Validated
public class CommentResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("share_id")
	private Integer shareId = null;

	@JsonProperty("from_user_id")
	private Integer fromUserId = null;

	@JsonProperty("msg")
	private String msg = null;
	
	@JsonProperty("creation_date_time")
	private LocalDateTime creationDateTime = null;

	public CommentResponseData id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * comment id
	 * @return id
	 **/
	@ApiModelProperty(value = "comment id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CommentResponseData shareId(Integer shareId) {
		this.shareId = shareId;
		return this;
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

	public CommentResponseData fromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
		return this;
	}

	/**
	 * id of who comment to you
	 * @return fromUserId
	 **/
	@ApiModelProperty(value = "id of who comment to you")
	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public CommentResponseData msg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * comment content
	 * @return msg
	 **/
	@ApiModelProperty(value = "comment content")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * comment time
	 * @return creation_date_time
	 **/
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

}
