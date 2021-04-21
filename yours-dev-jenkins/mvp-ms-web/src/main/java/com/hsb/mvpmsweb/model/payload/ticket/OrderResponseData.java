package com.hsb.mvpmsweb.model.payload.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Order Response Model")
@Valid
@Accessors(chain = true)
public class OrderResponseData {
	
	@ApiModelProperty(value = "Order ID", required = true, example = "1")
	private int entityId;
	
	@ApiModelProperty(value = "purchase quantity", required = true, example = "1")
	private int count;
	
	@ApiModelProperty(value = "Price", required = true, example = "800.00")
	private BigDecimal price;
	
	@ApiModelProperty(value = "Price for All", required = true, example = "800.00")
	private BigDecimal totalPrice;
	
	@ApiModelProperty(value = "Currency", required = true, example = "HKD")
	private String currency;
	
	@ApiModelProperty(value = "Concert name", required = true, example = "Unparalleled Concert")
	private String concertName;
	
	@ApiModelProperty(value = "Holding date", required = true, example = "2020-10-08")
	private LocalDate date;
	
	@ApiModelProperty(value = "Start time", required = true, example = "20:30:00")
	private LocalTime staratTime;
	
	@ApiModelProperty(value = "End time", required = true, example = "23:30:00")
	private LocalTime endTime;
	
	@ApiModelProperty(value = "Location", required = true, example = "Beijing Workers Gymnasium")
	private String location;
	
	@ApiModelProperty(value = "Cast Member", required = true, example = "Jay Chou")
	private String castMember;

}
