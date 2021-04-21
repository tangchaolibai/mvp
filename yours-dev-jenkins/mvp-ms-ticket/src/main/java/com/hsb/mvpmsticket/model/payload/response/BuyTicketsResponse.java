package com.hsb.mvpmsticket.model.payload.response;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsb.mvpmsticket.model.payload.BuyTicketsResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * This entity is buy movie tickets response.
 */
@ApiModel(description = "This entity is buy movie tickets response.")
@Validated
@Data
@Accessors(fluent = true)
public class BuyTicketsResponse extends BaseResponse {
	
	@JsonProperty("data")
	private BuyTicketsResponseData data = null;


}
