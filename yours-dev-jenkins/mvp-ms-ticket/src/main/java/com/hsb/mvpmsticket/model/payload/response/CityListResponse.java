package com.hsb.mvpmsticket.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsticket.model.payload.CityListResponseListData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is city list response.")
@Validated
@Data
@Accessors(fluent = true)
public class CityListResponse extends BaseResponse {
	
	@JsonProperty("data")
	private CityListResponseListData data;

}
