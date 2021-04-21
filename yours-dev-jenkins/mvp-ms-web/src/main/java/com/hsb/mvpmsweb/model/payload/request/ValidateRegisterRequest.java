package com.hsb.mvpmsweb.model.payload.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsweb.api.annotation.AreaCode;
import com.hsb.mvpmsweb.api.annotation.AreaCodeMatchPhoneNumber;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * This entity is used to describe an Register Request.
 */
@ApiModel(description = "This entity is used to validate Register Request.")
@Data
@AreaCodeMatchPhoneNumber(areaCode = "areaCode", mobilePhone = "mobilePhone")
@ToString
public class ValidateRegisterRequest {
	
	@JsonProperty("mobilePhone")
	@NotBlank
	@ApiModelProperty(required = true, value = "Mobile Phone for Register yours system")
	private String mobilePhone = null;

	@JsonProperty("areaCode")
	@AreaCode
	@ApiModelProperty(required = true, value = "Area Code for Register yours system")
	private String areaCode = null;

}

