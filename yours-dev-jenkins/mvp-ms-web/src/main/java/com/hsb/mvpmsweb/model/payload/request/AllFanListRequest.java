package com.hsb.mvpmsweb.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "This entity is used to describe an AllFanListRequest.")
@Validated
@Data
public class AllFanListRequest {

	@JsonProperty("toUserId")
	@ApiModelProperty(required = true,value = "To User Id")
	private Integer toUserId;

}
