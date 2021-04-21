package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get system messages response data.
 */
@ApiModel(description = "This entity is used to get system messages response data.")
@Validated
public class GetSystemResponseData {
	@JsonProperty("messages")
	private List<SystemResponseData> messages = null;

	public GetSystemResponseData messages(List<SystemResponseData> messages) {
		this.messages = messages;
		return this;
	}

	public GetSystemResponseData addMessagesItem(SystemResponseData messagesItem) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(messagesItem);
		return this;
	}

	/**
	 * system messages
	 * @return messages
	 **/
	@ApiModelProperty(value = "system messages")
	public List<SystemResponseData> getMessages() {
		return messages;
	}

	public void setMessages(List<SystemResponseData> messages) {
		this.messages = messages;
	}

}
