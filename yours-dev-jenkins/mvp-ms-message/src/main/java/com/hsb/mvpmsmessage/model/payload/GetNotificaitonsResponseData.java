package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get notificaitons response data.
 */
@ApiModel(description = "This entity is used to get notificaitons response data.")
@Validated
public class GetNotificaitonsResponseData {
	@JsonProperty("notificaiton")
	private List<MessageResponseData> notificaiton = null;

	public GetNotificaitonsResponseData notificaiton(List<MessageResponseData> notificaiton) {
		this.notificaiton = notificaiton;
		return this;
	}

	public GetNotificaitonsResponseData addNotificaitonItem(MessageResponseData notificaitonItem) {
		if (this.notificaiton == null) {
			this.notificaiton = new ArrayList<>();
		}
		this.notificaiton.add(notificaitonItem);
		return this;
	}

	/**
	 * notificaitons
	 * @return notificaiton
	 **/
	@ApiModelProperty(value = "notificaitons")
	public List<MessageResponseData> getNotificaiton() {
		return notificaiton;
	}

	public void setNotificaiton(List<MessageResponseData> notificaiton) {
		this.notificaiton = notificaiton;
	}

}
