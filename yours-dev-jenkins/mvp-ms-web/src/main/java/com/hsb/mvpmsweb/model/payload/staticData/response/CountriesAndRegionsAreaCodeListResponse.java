package com.hsb.mvpmsweb.model.payload.staticData.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.response.ResponseBody;
import com.hsb.mvpmsweb.model.payload.staticData.CountriesAndRegionsAreaCodeResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to Get Countries And Regions Area Code response data.
 */
@Data
@ApiModel(description = "This entity is used to Get Countries And Regions Area Code response data.")
@Validated
public class CountriesAndRegionsAreaCodeListResponse extends ResponseBody {
	
	@JsonProperty("data")
	private List<CountriesAndRegionsAreaCodeResponseData> data = null;

	public CountriesAndRegionsAreaCodeListResponse (ResponseResult result) {
		super(result);
	}

}
