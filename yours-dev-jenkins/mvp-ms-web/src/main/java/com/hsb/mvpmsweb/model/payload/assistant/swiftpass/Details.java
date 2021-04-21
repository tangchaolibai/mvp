package com.hsb.mvpmsweb.model.payload.assistant.swiftpass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to product details response data.
 */
@Data
@ApiModel(description = "This entity is used to product details response data.")
@Validated
@ToString
public class Details {
	
	@JsonProperty("id")
	private String id = null;
	
	@JsonProperty("createDateTime")
	private LocalDateTime createDateTime = null;
	
	@JsonProperty("linkedAttributeId")
	private String linkedAttributeId = null;
	
	@JsonProperty("content")
	private String content = null;
	
	@JsonProperty("photoUrl")
	private String photoUrl = null;
	
	@JsonProperty("comments")
	private String comments = null;
	
}
