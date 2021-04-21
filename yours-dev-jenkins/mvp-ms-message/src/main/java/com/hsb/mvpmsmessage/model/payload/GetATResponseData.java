package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get AT me response data.
 */
@ApiModel(description = "This entity is used to get AT me response data.")
@Validated
public class GetATResponseData {
	@JsonProperty("replys")
	private List<ATResponseData> replys = null;

	public GetATResponseData replys(List<ATResponseData> replys) {
		this.replys = replys;
		return this;
	}

	public GetATResponseData addReplysItem(ATResponseData replysItem) {
		if (this.replys == null) {
			this.replys = new ArrayList<>();
		}
		this.replys.add(replysItem);
		return this;
	}

	/**
	 * reply
	 * @return replys
	 **/
	@ApiModelProperty(value = "reply")
	public List<ATResponseData> getReplys() {
		return replys;
	}

	public void setReplys(List<ATResponseData> replys) {
		this.replys = replys;
	}

}
