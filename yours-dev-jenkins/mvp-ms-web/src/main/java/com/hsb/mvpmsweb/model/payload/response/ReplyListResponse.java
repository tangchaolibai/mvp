package com.hsb.mvpmsweb.model.payload.response;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ReplyResponseData;
import com.hsb.mvpmsweb.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe a Reply List response.")
@Validated
@Data
public class ReplyListResponse {

	@JsonProperty("data")
	private List<ReplyResponseData> data = null;
	
	@JsonProperty("result")
	private ResponseResult result = null;
		
}
