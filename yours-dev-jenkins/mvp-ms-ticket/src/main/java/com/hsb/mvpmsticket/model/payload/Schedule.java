package com.hsb.mvpmsticket.model.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Validated
@Data
@Accessors(fluent = true)
public class Schedule {
	
	@JsonProperty("filmScheduleId")
	private Integer filmScheduleId;
	
	@JsonProperty("startTime")
	private LocalDate startTime;
	
	@JsonProperty("endTime")
	private LocalDate endTime;
	
	@JsonProperty("hall")
	private String hall;
	
	@JsonProperty("price")
	private BigDecimal price;
	
	@JsonProperty("ticketsLeft")
	private Integer ticketsLeft;
	
	@JsonProperty("currency")
	private String currency;
}
