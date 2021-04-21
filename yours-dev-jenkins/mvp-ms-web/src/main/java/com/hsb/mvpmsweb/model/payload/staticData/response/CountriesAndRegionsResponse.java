package com.hsb.mvpmsweb.model.payload.staticData.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;
import com.hsb.mvpmsweb.model.payload.staticData.CountriesAndRegionsAreaCodeResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * This entity is used to Get Countries And Regions Area Code response data.
 */
@Data
@ApiModel(description = "This entity is used to Modify Countries And Regions Active Field response data.")
@Validated
public class CountriesAndRegionsResponse extends ResponseBody {
	
	@JsonProperty("data")
	private CountriesAndRegionsAreaCodeResponseData data = null;

	public CountriesAndRegionsResponse (ResponseResult result) {
		super(result);
	}

}
