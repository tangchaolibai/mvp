package com.hsb.mvpmsweb.model.payload.response;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.SearchUserResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe search users response.")
@Validated
@Data
public class SearchUserResponse extends BaseResponse {
	@JsonProperty("data")
	private List<SearchUserResponseData> data = null;
	
}
