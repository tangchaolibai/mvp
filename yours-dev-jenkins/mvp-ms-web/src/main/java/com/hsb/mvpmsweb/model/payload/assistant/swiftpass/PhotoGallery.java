package com.hsb.mvpmsweb.model.payload.assistant.swiftpass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to photo gallery response data.
 */
@Data
@ApiModel(description = "This entity is used to photo gallery response data.")
@Validated
@ToString
public class PhotoGallery {
	
	@JsonProperty("id")
	private String id = null;
	
	@JsonProperty("linkedId")
	private String linkedId = null;
	
	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("value")
	private BigDecimal value = null;
	
	@JsonProperty("comments")
	private String comments = null;
	
}
