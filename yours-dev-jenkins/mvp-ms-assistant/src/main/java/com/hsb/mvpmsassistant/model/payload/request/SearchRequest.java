package com.hsb.mvpmsassistant.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search request.
 */
@ApiModel(description = "This entity is used to search request.")
@Validated
public class SearchRequest {
	@JsonProperty("keyWord")
	private String keyWord = null;

	/**
	 * Keyword of search.
	 * @return keyWord
	 **/
	@ApiModelProperty(value = "Keyword of search.")
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
