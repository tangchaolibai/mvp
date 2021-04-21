package com.hsb.mvpmsweb.model.payload.assistant;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to photo response data.
 */
@Data
@ApiModel(description = "This entity is used to photo response data.")
@Validated
@ToString
public class PhotoResponseData {
	
	private Integer id = null;

	private String name = null;

	private String thumbnailUrl = null;
	
	private Integer shareId = null;
	
	private String productUrl = null;
	
	private Integer views = null;
	
}
