package com.hsb.mvpmsuser.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsuser.model.payload.SystemSettingResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe edit System Setting response.")
@Validated
@Data
public class EditSystemSettingResponse extends BaseResponse {
	@JsonProperty("data")
	private SystemSettingResponseData data = null;

}
