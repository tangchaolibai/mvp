package com.hsb.mvpmsuser.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsuser.model.payload.CountriesAndRegionsAreaCodeResponseData;
import com.hsb.mvpmsuser.model.payload.ResponseResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to Get Countries And Regions Area Code response data.
 */
@ApiModel(description = "This entity is used to Get Countries And Regions Area Code response data.")
@Validated
public class CountriesAndRegionsAreaCodeListResponse extends ResponseBody {
	
	@JsonProperty("data")
	private List<CountriesAndRegionsAreaCodeResponseData> data = null;

	public CountriesAndRegionsAreaCodeListResponse (ResponseResult result) {
		super(result);
	}

	/**
	 * Get data
	 * @return data
	 **/
	@ApiModelProperty(value = "")
	public List<CountriesAndRegionsAreaCodeResponseData> getData() {
		return data;
	}

	public void setData(List<CountriesAndRegionsAreaCodeResponseData> data) {
		this.data = data;
	}

}
