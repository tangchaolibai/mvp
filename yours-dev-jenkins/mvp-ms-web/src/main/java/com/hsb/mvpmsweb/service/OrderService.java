package com.hsb.mvpmsweb.service;

import javax.validation.Valid;

import org.dom4j.DocumentException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hsb.mvpmsweb.api.exception.MvpTicketException;
import com.hsb.mvpmsweb.model.payload.ticket.CheckOrderStatusResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.FindOrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.OrderRefundResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.OrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.PayOrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.request.FindOrderRequest;
import com.hsb.mvpmsweb.model.payload.ticket.request.OrderRefundRequest;

public interface OrderService {

	public FindOrderResponseData findOrder(FindOrderRequest request) throws MvpTicketException;
	
	public OrderRefundResponseData refundOrder( OrderRefundRequest request) throws MvpTicketException;

	public OrderResponseData generateOrder(@Valid int productId, @Valid int count) throws MvpTicketException;
	
	public String notifyPay(String xmlInfo) throws MvpTicketException;

	public PayOrderResponseData payOrder(@Valid int orderId, String paymentInstance, String callBackUrl) throws JsonProcessingException, MvpTicketException, DocumentException;

	public CheckOrderStatusResponseData checkOrderStatus(String orderId) throws MvpTicketException;
	
}
