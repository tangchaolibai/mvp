package com.hsb.mvpmsticket.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search photos response data.
 */
@ApiModel(description = "This entity is used to get movies response data.")
@Validated
@Data
public class GetMoviesResponseData {
	@JsonProperty("movies")
	@ApiModelProperty(value = "get movies")
	private List<MovieResponseData> movies = null;

}
