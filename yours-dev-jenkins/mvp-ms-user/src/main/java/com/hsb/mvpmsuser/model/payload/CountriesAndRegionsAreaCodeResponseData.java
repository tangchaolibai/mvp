package com.hsb.mvpmsuser.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to Countries And Regions Area Code response data.
 */
@ApiModel(description = "This entity is used to Countries And Regions Area Code response data.")
@Validated
public class CountriesAndRegionsAreaCodeResponseData {
	
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name_En_US")
	private String nameEnUs = null;
	
	@JsonProperty("area_code")
	private Integer areaCode = null;
	
	@JsonProperty("active")
	private Boolean active = null;
	
	@JsonProperty("prohibit")
	private Boolean prohibit = null;

	/**
	 * video id
	 * @return id
	 **/
	@ApiModelProperty(value = "video id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Country and region English name
	 * @return nameEnUs
	 **/
	@ApiModelProperty(value = "Country and region English name")
	public String getNameEnUs() {
		return nameEnUs;
	}

	public void setNameEnUs(String nameEnUs) {
		this.nameEnUs = nameEnUs;
	}

	/**
	 * Country and region areaCode
	 * @return areaCode
	 **/
	@ApiModelProperty(value = "Country and region areaCode")
	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * If Country and region is actived
	 * @return active
	 **/
	@ApiModelProperty(value = "If Country and region is actived")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**
	 * If Country and region is prohibited
	 * @return prohibit
	 **/
	@ApiModelProperty(value = "If Country and region is prohibited")
	public Boolean getProhibit() {
		return prohibit;
	}

	public void setProhibit(Boolean prohibit) {
		this.prohibit = prohibit;
	}

}
