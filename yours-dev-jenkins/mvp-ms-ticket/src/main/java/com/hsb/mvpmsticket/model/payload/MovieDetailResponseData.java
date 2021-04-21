package com.hsb.mvpmsticket.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Validated
@Data
@Accessors(fluent = true)
public class MovieDetailResponseData extends MovieResponseData {
	
	@JsonProperty("duration")
	private Integer duration = null;
	
	@JsonProperty("type")
	private String type = null;
	
	@JsonProperty("region")
	private String region = null;
	
	@JsonProperty("introduction")
	private String introduction = null;

}
