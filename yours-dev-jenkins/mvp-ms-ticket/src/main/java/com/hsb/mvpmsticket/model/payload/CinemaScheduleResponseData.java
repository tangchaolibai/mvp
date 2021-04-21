package com.hsb.mvpmsticket.model.payload;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to cinema and scheduled films response data.")
@Validated
@Data
@Accessors(fluent = true)
public class CinemaScheduleResponseData {
	
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("address")
	private String address;
	
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
	
	@JsonProperty("playingFilmList")
	private List<FilmSchedulePayload> playingFilmList;
	
	@JsonProperty("filmTotal")
	private Integer filmTotal;

}
