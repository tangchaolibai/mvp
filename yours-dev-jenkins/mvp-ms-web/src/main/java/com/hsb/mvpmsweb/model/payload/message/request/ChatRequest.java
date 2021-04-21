package com.hsb.mvpmsweb.model.payload.message.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * ChatRequest
 */
@Data
@Validated
@ToString
public class ChatRequest {

	@JsonProperty("toUserId")
	private Integer toUserId = null;

	@JsonProperty("fromUserId")
	private Integer fromUserId = null;

	@JsonProperty("msgType")
	private String msgType = null;

	@JsonProperty("message")
	private String message = null;

}
