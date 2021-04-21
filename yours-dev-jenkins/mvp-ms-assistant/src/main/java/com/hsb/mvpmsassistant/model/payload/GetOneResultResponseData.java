package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get one search result response data.
 */
@ApiModel(description = "This entity is used to get one search result response data.")
@Validated
public class GetOneResultResponseData {
	@JsonProperty("result")
	private Object result = null;

	/**
	 * get one search result
	 * @return result
	 **/
	@ApiModelProperty(value = "get one search result")
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
