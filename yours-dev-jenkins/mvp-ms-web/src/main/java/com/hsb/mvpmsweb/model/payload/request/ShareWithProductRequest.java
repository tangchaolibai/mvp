package com.hsb.mvpmsweb.model.payload.request;

import com.hsb.mvpmsweb.model.payload.ticket.request.PutOnSaleRequest;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to describe an post content with product request.")
@Data
public class ShareWithProductRequest {
	
	private SocialShareRequest socialShareRequest;
	
	private PutOnSaleRequest putOnSaleRequest;

}
