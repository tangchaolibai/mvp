package com.hsb.mvpmsticket.model.payload;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Validated
@Data
@Accessors(fluent = true)
public class FilmSchedulePayload {
	
	@JsonProperty("filmId")
	private Integer filmId;
	
	@JsonProperty("score")
	private String score;
	
	@JsonProperty("iamge")
	private String iamge;
	
	@JsonProperty("chineseName")
	private String chineseName;
	
	@JsonProperty("duration")
	private Integer duration;
	
	@JsonProperty("type")
	private List<String> type;
	
	@JsonProperty("mainActors")
	private List<String> mainActors;
	
	@JsonProperty("scheduleList")
	private List<Schedule> scheduleList;
	
	@JsonProperty("total")
	private Integer total;
}
