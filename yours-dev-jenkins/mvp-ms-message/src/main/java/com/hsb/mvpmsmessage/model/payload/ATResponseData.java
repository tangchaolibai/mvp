package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to AT me response data.
 */
@ApiModel(description = "This entity is used to AT me response data.")
@Validated
public class ATResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("comment_id")
	private Integer commentId = null;

	@JsonProperty("from_user_id")
	private Integer fromUserId = null;

	@JsonProperty("to_user_id")
	private Integer toUserId = null;

	@JsonProperty("reply")
	private String reply = null;
	
	@JsonProperty("creation_date_time")
	private LocalDateTime creationDateTime = null;

	/**
	 * reply id
	 * @return id
	 **/
	@ApiModelProperty(value = "reply id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ATResponseData commentId(Integer commentId) {
		this.commentId = commentId;
		return this;
	}

	/**
	 * comment id
	 * @return commentId
	 **/
	@ApiModelProperty(value = "comment id")
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public ATResponseData fromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
		return this;
	}

	/**
	 * id of who reply you
	 * @return fromUserId
	 **/
	@ApiModelProperty(value = "id of who reply you")
	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public ATResponseData toUserId(Integer toUserId) {
		this.toUserId = toUserId;
		return this;
	}

	/**
	 * id of who get reply
	 * @return toUserId
	 **/
	@ApiModelProperty(value = "id of who get reply")
	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	/**
	 * reply content
	 * @return reply
	 **/
	@ApiModelProperty(value = "reply content")
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * reply time
	 * @return creation_date_time
	 **/
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
}
