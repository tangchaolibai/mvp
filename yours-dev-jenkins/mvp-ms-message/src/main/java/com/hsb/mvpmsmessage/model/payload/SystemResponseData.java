package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to system messages response data.
 */
@ApiModel(description = "This entity is used to system messages response data.")
@Validated
public class SystemResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("msg")
	private String msg = null;
	
	@JsonProperty("creation_date_time")
	private LocalDateTime creationDateTime = null;
	
	@JsonProperty("is_read")
	private String isRead = null;

	/**
	 * message id
	 * @return id
	 **/
	@ApiModelProperty(value = "message id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * send message time
	 * @return creation_date_time
	 **/
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	/**
	 * if message is read
	 * @return is_read
	 **/
	public String getIsRead() {
		return isRead;
	}
	
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	/**
	 * message content
	 * @return msg
	 **/
	@ApiModelProperty(value = "message content")
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
