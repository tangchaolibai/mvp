package com.hsb.mvpmsticket.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to buy tickets request.
 */
@ApiModel(description = "This entity is used to buy tickets.")
@Validated
public class BuyTicketsRequest {
	@JsonProperty("sessions_id")
	private Integer sessionsId = null;
	
	@JsonProperty("ticket_count")
	private Integer ticketCount = null;

	/**
	 * Movie Sessions Id.
	 * @return sessionId
	 **/
	@ApiModelProperty(value = "Movie Sessions Id.")
	public Integer getSessionsId() {
		return sessionsId;
	}

	public void setSessionsId(Integer sessionsId) {
		this.sessionsId = sessionsId;
	}

	/**
	 * Buy Ticket count.
	 * @return ticketCount
	 **/
	@ApiModelProperty(value = "Buy Ticket count.")
	public Integer getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(Integer ticketCount) {
		this.ticketCount = ticketCount;
	}
	
}
