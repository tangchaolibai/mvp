package com.hsb.mvpmsticket.model.payload;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to movie response data.
 */
@ApiModel(description = "This entity is used to movie response data.")
@Validated
@Data
@Accessors(fluent = true)
public class MovieResponseData {
	
	@JsonProperty("id")
	@ApiModelProperty(value = "movie id")
	private Integer id = null;

	@JsonProperty("chineseName")
	private String chineseName = null;
	
	@JsonProperty("director")
	private String director = null;
	
	@JsonProperty("mainActors")
	private List<String> mainActors = null;
	
	@JsonProperty("score")
	private BigDecimal score = null;
	
	@JsonProperty("likeAmount")
	private BigDecimal likeAmount = null;
	
	@JsonProperty("image")
	private String image = null;
	
	@JsonProperty("releaseDate")
	private LocalDate releaseDate = null;
	
	@JsonProperty("demention")
	private String demention = null;
	
}
