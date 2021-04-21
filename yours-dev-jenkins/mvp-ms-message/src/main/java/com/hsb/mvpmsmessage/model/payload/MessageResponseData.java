package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to message response data.
 */
@ApiModel(description = "This entity is used to message response data.")
@Validated
public class MessageResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("msg_type")
	private String msgType = null;

	@JsonProperty("msg")
	private String msg = null;

	@JsonProperty("from_user_id")
	private Integer fromUserId = null;

	@JsonProperty("from_user_name")
	private String fromUserName = null;

	@JsonProperty("from_user_img_path")
	private String fromUserImgPath = null;

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

	public MessageResponseData msgType(String msgType) {
		this.msgType = msgType;
		return this;
	}

	/**
	 * message type
	 * @return msgType
	 **/
	@ApiModelProperty(value = "message type")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
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

	public MessageResponseData fromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
		return this;
	}

	/**
	 * id of who send message to you
	 * @return fromUserId
	 **/
	@ApiModelProperty(value = "id of who send message to you")
	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public MessageResponseData fromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
		return this;
	}

	/**
	 * name of who send message to you
	 * @return fromUserName
	 **/
	@ApiModelProperty(value = "name of who send message to you")
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public MessageResponseData fromUserImgPath(String fromUserImgPath) {
		this.fromUserImgPath = fromUserImgPath;
		return this;
	}

	/**
	 * image path of who send message to you
	 * @return fromUserImgPath
	 **/
	@ApiModelProperty(value = "image path of who send message to you")
	public String getFromUserImgPath() {
		return fromUserImgPath;
	}

	public void setFromUserImgPath(String fromUserImgPath) {
		this.fromUserImgPath = fromUserImgPath;
	}

}
