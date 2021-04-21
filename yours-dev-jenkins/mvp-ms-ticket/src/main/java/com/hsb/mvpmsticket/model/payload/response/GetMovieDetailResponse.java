package com.hsb.mvpmsticket.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsticket.model.payload.MovieDetailResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is get movie detail response.
 */
@ApiModel(description = "This entity is get movie detail response.")
@Validated
@Data
@Accessors(fluent = true)
public class GetMovieDetailResponse extends BaseResponse {
	
	@JsonProperty("data")
	private MovieDetailResponseData data = null;

}
