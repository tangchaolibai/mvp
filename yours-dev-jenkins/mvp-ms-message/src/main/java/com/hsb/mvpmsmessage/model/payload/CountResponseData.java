package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to unRead notificaiton count response data.
 */
@ApiModel(description = "This entity is used to unRead notificaiton count response data.")
@Validated
public class CountResponseData {
	@JsonProperty("count")
	private Integer count = null;

	public CountResponseData count(Integer count) {
		this.count = count;
		return this;
	}

	/**
	 * unRead notificaiton count
	 * @return count
	 **/
	@ApiModelProperty(value = "unRead notificaiton count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
