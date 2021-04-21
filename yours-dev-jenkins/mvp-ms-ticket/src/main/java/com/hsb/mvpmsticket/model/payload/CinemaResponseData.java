package com.hsb.mvpmsticket.model.payload;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is used to cinema response data.
 */
@ApiModel(description = "This entity is used to cinema response data.")
@Validated
@Data
@Accessors(fluent = true)
public class CinemaResponseData {
	
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("locationCity")
	private String locationCity = null;
	
	@JsonProperty("locationRegion")
	private String locationRegion = null;
	
	@JsonProperty("locationBusiness")
	private String locationBusiness = null;
	
	@JsonProperty("address")
	private String address = null;
	
	@JsonProperty("imaxEnable")
	private Boolean imaxEnable;
	
	@JsonProperty("dolbyEnable")
	private Boolean dolbyEnable;
	
	@JsonProperty("cgsEnable")
	private Boolean cgsEnable;
	
	@JsonProperty("refundEnable")
	private Boolean refundEnable;
	
	@JsonProperty("endorseEnable")
	private Boolean endorseEnable;
	
	@JsonProperty("free3dGlassesEnable")
	private Boolean free3dGlassesEnable;
	
	@JsonProperty("lowestFare")
	private BigDecimal lowestFare;

}
