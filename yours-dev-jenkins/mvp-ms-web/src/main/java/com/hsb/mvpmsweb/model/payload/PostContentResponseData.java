package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@Validated
@ToString
public class PostContentResponseData {
  
	@JsonProperty("shareId")
	private Integer shareId = null;

}

