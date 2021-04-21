package com.hsb.mvpmsticket.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get cinemas response data.
 */
@ApiModel(description = "This entity is used to get cinemas response data.")
@Validated
public class GetCinemasResponseData {
	@JsonProperty("cinemas")
	private List<CinemaResponseData> cinemas = null;

	/**
	 * get cinemas
	 * @return cinemas
	 **/
	@ApiModelProperty(value = "get cinemas")
	public List<CinemaResponseData> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<CinemaResponseData> cinemas) {
		this.cinemas = cinemas;
	}

}
