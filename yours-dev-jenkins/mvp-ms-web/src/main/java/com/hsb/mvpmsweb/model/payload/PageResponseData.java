package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Data
@Validated
@ToString
public class PageResponseData {
	
	private Integer currentPage = null;
	
	private Long totalCount = null;
	
}
