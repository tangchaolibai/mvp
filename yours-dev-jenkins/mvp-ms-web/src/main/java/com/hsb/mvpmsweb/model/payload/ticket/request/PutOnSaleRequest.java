package com.hsb.mvpmsweb.model.payload.ticket.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.hsb.mvpmsweb.api.annotation.Currency;
import com.hsb.mvpmsweb.api.annotation.StartTimeBeforeEndTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe a performence put on sale Request.")
@Data
@Accessors(chain = true)
@StartTimeBeforeEndTime(startTime = "startTime", endTime = "endTime")
public class PutOnSaleRequest {
	
	//
	@ApiModelProperty(required = true, value = "product name", example = "Jay Chou Concert")
	@NotBlank
	private String productName;
	
	@ApiModelProperty(required = true, value = "currency", example = "HKD", allowableValues = "HKD,CNY")
	@Currency
	private String currency;
	
	@ApiModelProperty(required = true, value = "user ID", example = "40")
	@NotNull
	@Positive
	private int userId;
	
	@ApiModelProperty(required = true, value = "performence name", example = "Unparalleled Concert")
	@NotBlank
	private String performenceName;
	
	@ApiModelProperty(required = true, value = "holding date", example = "2020-11-15")
	@Future
	private LocalDate date;
	
	@ApiModelProperty(required = true, value = "start time", example = "20:30:00")
	@NotNull
	private LocalTime startTime;
	
	@ApiModelProperty(required = true, value = "end time", example = "22:30:00")
	@NotNull
	private LocalTime endTime;
	
	@ApiModelProperty(required = true, value = "location", example = "Beijing Workers Gymnasium")
	@NotBlank
	private String location;
	
	@ApiModelProperty(required = true, value = "price", example = "100.00")
	@DecimalMin("0.00")
	@Digits(integer = 10, fraction = 2)
	@NotNull
	private BigDecimal price;
	
	@ApiModelProperty(required = true, value = "cast member", example = "Jay Zhou")
	@NotBlank
	private String castMember;
	
	@ApiModelProperty(required = true, value = "tickets amount", example = "100", allowableValues = "range[1,1000]")
	@Min(1)
	@Max(1000)
	private int ticketsAmount;
	
}
