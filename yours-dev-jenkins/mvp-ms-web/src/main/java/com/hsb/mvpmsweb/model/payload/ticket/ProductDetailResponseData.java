package com.hsb.mvpmsweb.model.payload.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Product Detail Model")
@Valid
@Accessors(chain = true)
@ToString
public class ProductDetailResponseData {
	
	@ApiModelProperty(value = "Product ID", required = true, example = "1")
	private Integer entityId;
	
	@ApiModelProperty(value = "Concert name", required = true, example = "Unparalleled Concert")
	private String concertName;
	
	@ApiModelProperty(value = "Holding date", required = true, example = "2020-10-08")
	private LocalDate date;
	
	@ApiModelProperty(value = "Start time", required = true, example = "20:30:00")
	private LocalTime startTime;
	
	@ApiModelProperty(value = "End time", required = true, example = "23:30:00")
	private LocalTime endTime;
	
	@ApiModelProperty(value = "Location", required = true, example = "Beijing Workers Gymnasium")
	private String location;
	
	@ApiModelProperty(value = "Cast Member", required = true, example = "Jay Chou")
	private String castMember;
	
	@ApiModelProperty(value = "Price", required = true, example = "800.00")
	private BigDecimal price;
	
	@ApiModelProperty(value = "Currency", required = true, example = "HKD")
	private String currency;
	
	@ApiModelProperty(value = "Order", required = true, example = "1")
	private Integer order;
	
}
