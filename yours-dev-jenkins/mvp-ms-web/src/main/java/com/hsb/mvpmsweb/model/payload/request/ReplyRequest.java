package com.hsb.mvpmsweb.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "This entity is used to describe a reply request.")
@Validated
@Data
public class ReplyRequest {
	
	@JsonProperty("shareId")
	@ApiModelProperty(required = true,value = "Share Id")
	private Integer shareId = null;

	@JsonProperty("commentId")
	@ApiModelProperty(required = true,value = "Comment Id")
	private Integer commentId = null;

	@JsonProperty("fromUserId")
	@ApiModelProperty(required = true,value = "From User Id")
	private Integer fromUserId = null;

	@JsonProperty("toUserId")
	@ApiModelProperty(required = true,value = "To User Id")
	private Integer toUserId = null;

	@JsonProperty("reply")
	@ApiModelProperty(required = true,value = "Reply")
	private String reply = null;
	
}
