package com.hsb.mvpmsweb.model.payload.staticData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to Countries And Regions Area Code response data.
 */
@Data
@ApiModel(description = "This entity is used to Countries And Regions Area Code response data.")
@Validated
@ToString
public class CountriesAndRegionsAreaCodeResponseData {
	
	private Integer id = null;

	private String nameEnUs = null;
	
	private String nameZhCn = null;
	
	private String nameZhHk = null;
	
	private Integer areaCode = null;
	
	private Boolean active = null;
	
	private Boolean prohibit = null;

}
