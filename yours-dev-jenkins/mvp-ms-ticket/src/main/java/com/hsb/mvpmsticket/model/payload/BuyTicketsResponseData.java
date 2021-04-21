package com.hsb.mvpmsticket.model.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to search photos response data.
 */
@ApiModel(description = "This entity is used to get movies response data.")
@Validated
@Data
@Accessors(fluent = true)
public class BuyTicketsResponseData {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("cinema_name")
	private String cinemaName;
	
	@JsonProperty("chinma_loc")
	private String cinemaLoc;
	
	@JsonProperty("movie_chi_name")
	private String movieChiName;
	
	@JsonProperty("startTime")
	private LocalDate startTime;
	
	@JsonProperty("price")
	private BigDecimal price;
	
	@JsonProperty("count")
	private Integer count;
	
	private String currency;
	
}
