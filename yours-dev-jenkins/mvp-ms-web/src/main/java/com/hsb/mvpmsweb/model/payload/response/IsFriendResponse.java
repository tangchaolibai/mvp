package com.hsb.mvpmsweb.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.IsFriendResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe an Is Friend response.")
@Validated
@Data
public class IsFriendResponse extends BaseResponse{

	@JsonProperty("data")
	private IsFriendResponseData data = null;
	
}
