package com.hsb.mvpmsticket.model.payload.response;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsticket.model.payload.CinemaResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is get cinemas response.
 */
@ApiModel(description = "This entity is get cinemas response.")
@Validated
@Data
@Accessors(fluent = true)
public class GetCinemasResponse extends BaseResponse {
	
	@JsonProperty("data")
	private List<CinemaResponseData> data = null;
	
	@JsonProperty("total")
	private Integer total = null;

}
