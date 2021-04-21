package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * InviteFriendByMobileContactsResponseData
 */
@Validated
@Data
public class InviteFriendByMobileContactsResponseData   {
	
  @JsonProperty("message")
  private String message = null;

}

