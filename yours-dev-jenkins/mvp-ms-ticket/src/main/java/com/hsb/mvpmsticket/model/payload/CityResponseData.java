package com.hsb.mvpmsticket.model.payload;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to get city data.")
@Validated
@Data
public class CityResponseData {
	
	private int id;
	
	private String cityName;
}
