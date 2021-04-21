package com.hsb.mvpmsweb.model.payload.assistant.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search request.
 */
@Data
@ApiModel(description = "This entity is used to search request.")
@Validated
@ToString
public class SearchRequest {
	
	@JsonProperty("keyWord")
	private String keyWord = null;

}
