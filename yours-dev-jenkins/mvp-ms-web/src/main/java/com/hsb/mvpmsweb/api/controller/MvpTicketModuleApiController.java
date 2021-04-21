package com.hsb.mvpmsweb.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hsb.mvpmsweb.api.MvpTicketModuleApi;
import com.hsb.mvpmsweb.api.annotation.PaymentInstance;
import com.hsb.mvpmsweb.api.exception.MvpTicketException;
import com.hsb.mvpmsweb.model.payload.ResponseResult;
import com.hsb.mvpmsweb.model.payload.request.SubmitAndPayRequest;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.response.SubmitOrderResponse;
import com.hsb.mvpmsweb.model.payload.ticket.CheckOrderStatusResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.FindOrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.OrderRefundResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.OrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.PayOrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.ProductDetailResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.ProductResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.PutOnSaleResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.request.FindOrderRequest;
import com.hsb.mvpmsweb.model.payload.ticket.request.OrderRefundRequest;
import com.hsb.mvpmsweb.model.payload.ticket.request.PutOnSaleRequest;
import com.hsb.mvpmsweb.model.payload.ticket.response.CheckOrderStatusResponse;
import com.hsb.mvpmsweb.model.payload.ticket.response.FindOrderResponse;
import com.hsb.mvpmsweb.model.payload.ticket.response.FindProductDetailResponse;
import com.hsb.mvpmsweb.model.payload.ticket.response.FindProductResponse;
import com.hsb.mvpmsweb.model.payload.ticket.response.OrderRefundResponse;
import com.hsb.mvpmsweb.model.payload.ticket.response.PayOrderResponse;
import com.hsb.mvpmsweb.model.payload.ticket.response.PutOnSaleResponse;
import com.hsb.mvpmsweb.service.OrderService;
import com.hsb.mvpmsweb.service.ProductService;

@RestController
public class MvpTicketModuleApiController implements MvpTicketModuleApi {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;

	@Override
	public ResponseEntity<PutOnSaleResponse> putOnSale(@Valid PutOnSaleRequest putOnSaleRequest) throws MvpTicketException {
		PutOnSaleResponseData data = productService.putOnSale(putOnSaleRequest);
		PutOnSaleResponse response = new PutOnSaleResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BaseResponse> uploadPic(@Valid int productId, MultipartFile multipartFile) throws MvpTicketException {
		productService.uploadPic(productId, multipartFile);
		BaseResponse response = new BaseResponse(ResponseResult.DefaultSuccessResponse);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FindProductResponse> findProducts(@Valid int userId) {
		List<ProductResponseData> data = productService.getProductListByUserId(userId);
		FindProductResponse response = new FindProductResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FindProductDetailResponse> findProductDetail(@Valid int productId) throws MvpTicketException {
		ProductDetailResponseData data = productService.getProductDetailById(productId);
		FindProductDetailResponse response = new FindProductDetailResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<SubmitOrderResponse> submitOrder(@Valid int productId, @Valid int count) throws MvpTicketException {
		OrderResponseData data = orderService.generateOrder(productId, count);
		SubmitOrderResponse response = new SubmitOrderResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<PayOrderResponse> payOrder(@Valid int orderId, String callBackUrl, @PaymentInstance String paymentInstance) throws MvpTicketException, JsonProcessingException, DocumentException {
		PayOrderResponseData data = orderService.payOrder(orderId, paymentInstance, callBackUrl);
		PayOrderResponse response = new PayOrderResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<PayOrderResponse> submitOrderAndPay(@Valid @RequestBody SubmitAndPayRequest submitAndPayRequest) throws MvpTicketException, JsonProcessingException, DocumentException {
		OrderResponseData orderResponseData = orderService.generateOrder(submitAndPayRequest.getProductId(), submitAndPayRequest.getCount());
		PayOrderResponseData data = orderService.payOrder(orderResponseData.getEntityId(), submitAndPayRequest.getPaymentInstance(), submitAndPayRequest.getCallBackUrl());
		PayOrderResponse response = new PayOrderResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<CheckOrderStatusResponse> checkOrderStatus(String orderId) throws MvpTicketException {
		CheckOrderStatusResponseData data = orderService.checkOrderStatus(orderId);
		CheckOrderStatusResponse response = new CheckOrderStatusResponse(ResponseResult.DefaultSuccessResponse, data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<FindOrderResponse> findOrder(FindOrderRequest request) throws MvpTicketException {
		FindOrderResponseData data = orderService.findOrder(request);
		FindOrderResponse response = new FindOrderResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<OrderRefundResponse> refundOrder(@Valid OrderRefundRequest request) throws MvpTicketException {
		OrderRefundResponseData data = orderService.refundOrder(request);
		OrderRefundResponse response = new OrderRefundResponse(ResponseResult.DefaultSuccessResponse);
		response.setData(data);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<String> notifyPay(String request) throws MvpTicketException{
		String response = orderService.notifyPay(request);
		return ResponseEntity.ok(response);
	}

}
