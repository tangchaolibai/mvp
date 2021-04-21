package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe Countries And Regions Id Request.")
@Data
@Accessors(fluent = true)
public class CountriesAndRegionsIdRequest {
	
	@JsonProperty("id")
	private Integer id = null;
	
}
