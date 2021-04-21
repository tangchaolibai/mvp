package com.hsb.mvpmsuser.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsuser.model.payload.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsuser.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to Get Countries And Regions Area Code response data.
 */
@ApiModel(description = "This entity is used to Modify Countries And Regions Active Field response data.")
@Validated
public class CountriesAndRegionsResponse extends ResponseBody {
	
	@JsonProperty("data")
	private CountriesAndRegionsAreaCodeResponseData data = null;

	public CountriesAndRegionsResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public CountriesAndRegionsAreaCodeResponseData getData() {
		return data;
	}

	public void setData(CountriesAndRegionsAreaCodeResponseData data) {
		this.data = data;
	}

}
