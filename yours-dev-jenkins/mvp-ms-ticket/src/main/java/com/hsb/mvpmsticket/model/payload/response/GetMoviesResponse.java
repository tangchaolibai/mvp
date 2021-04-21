package com.hsb.mvpmsticket.model.payload.response;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsticket.model.payload.MovieResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is get movies response.
 */
@ApiModel(description = "This entity is get movies response.")
@Validated
@Data
@Accessors(fluent = true)
public class GetMoviesResponse extends BaseResponse {
	
	@JsonProperty("total")
	private Integer total = null;
	
	@JsonProperty("data")
	private List<MovieResponseData> data = null;

}
