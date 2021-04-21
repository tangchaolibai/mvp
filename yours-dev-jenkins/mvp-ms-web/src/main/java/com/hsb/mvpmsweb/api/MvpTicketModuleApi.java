package com.hsb.mvpmsweb.api;

import javax.validation.Valid;

import org.dom4j.DocumentException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.hsb.mvpmsweb.api.annotation.NeedToken;
import com.hsb.mvpmsweb.api.annotation.PaymentInstance;
import com.hsb.mvpmsweb.api.annotation.PictureType;
import com.hsb.mvpmsweb.api.annotation.ResponseConfig;
import com.hsb.mvpmsweb.api.exception.MvpTicketException;
import com.hsb.mvpmsweb.constant.HttpStatusCodeConstants;
import com.hsb.mvpmsweb.constant.Swagger2Constants;
import com.hsb.mvpmsweb.model.payload.request.SubmitAndPayRequest;
import com.hsb.mvpmsweb.model.payload.response.BaseResponse;
import com.hsb.mvpmsweb.model.payload.response.SubmitOrderResponse;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Api(tags = Swagger2Constants.TAG_NM_TICKET)
@ApiSupport(order = 50, author = "zhaoyue@formssi.com")
public interface MvpTicketModuleApi {
	
//	@ApiOperation(value = "Performence put on sale", nickname = "putOnSale", notes = "Performence put on sale", response = BaseResponse.class)
//    @NeedToken
//    @ResponseConfig
//    @PostMapping(value = "/product/pp", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiImplicitParams({
//	    @ApiImplicitParam(name = "multipartFile", dataType = "file", paramType = "formData", required = true),
//	    @ApiImplicitParam(name = "putOnSaleRequest", dataType = "PutOnSaleRequest", paramType = "body", required = true)
//	})
//    ResponseEntity<BaseResponse> putOnSalePP(
//    		@Valid @RequestBody(required = true) PutOnSaleRequest putOnSaleRequest,
//    		@PictureType @RequestParam(required = true) MultipartFile multipartFile) throws MvpTicketException;

	@ApiOperationSupport(order = 10, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "Performence put on sale", nickname = "putOnSale", notes = "Performence put on sale", response = PutOnSaleResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = PutOnSaleResponse.class))
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PutOnSaleResponse> putOnSale(
    		@ApiParam(value = "Product Put on Sale Request", required = true)@Valid @RequestBody PutOnSaleRequest putOnSaleRequest) throws MvpTicketException;
	
	@ApiOperationSupport(order = 20, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "Upload picture of product", nickname = "uploadPic", notes = "Upload picture of product", response = BaseResponse.class)
    @NeedToken
    @ResponseConfig
    @PostMapping(value = "/uploadPic", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BaseResponse> uploadPic(
    		@ApiParam(value = "Product Put on Sale Request", required = true)@Valid @RequestParam(value = "productId") int productId,
    		@ApiParam(value = "posterPicture", required = true) @PictureType @RequestParam MultipartFile multipartFile) throws MvpTicketException;
	
	@ApiOperationSupport(order = 30, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "Find products by Id", nickname = "findProducts", notes = "Find products by Id", response = FindProductResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = FindProductResponse.class))
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FindProductResponse> findProducts(@Valid @RequestParam(value = "userId") int userId);
	
	@ApiOperationSupport(order = 40, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "Find product detail by product Id", nickname = "findProductDetail", notes = "Find product detail by product Id", response = FindProductDetailResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = FindProductDetailResponse.class))
    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FindProductDetailResponse> findProductDetail(@Valid @RequestParam(value = "productId") int productId) throws MvpTicketException;
	
	@ApiOperationSupport(order = 50, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "Submit Order", nickname = "SubmitOrder", notes = "Generate order to show uer for confirmation", response = SubmitOrderResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = SubmitOrderResponse.class))
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SubmitOrderResponse> submitOrder(
    		@ApiParam(value = "Product Id", required = true)@Valid @RequestParam(value = "productId") int productId, 
    		@ApiParam(value = "purchase quantity", required = true)@Valid @RequestParam(value = "count") int count) throws MvpTicketException;
	
	@ApiOperationSupport(order = 60, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "pay order", nickname = "payOrder", notes = "pay order", response = PayOrderResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = PayOrderResponse.class))
    @GetMapping(value = "/order/pay", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PayOrderResponse> payOrder(
			@ApiParam(value = "Order ID", required = true) @Valid @RequestParam(value = "orderId")  int orderId,
			@ApiParam(value = "CallBack URL", required = false) @Valid @RequestParam(value = "callBackUrl", required = false)  String callBackUrl,
			@ApiParam(value = "PaymentInstance. Only surpport 'ALIPAYHK' and 'ALIPAYCN'", required = false)
			@Valid @PaymentInstance @RequestParam(value = "paymentInstance", required = false)  String paymentInstance) throws MvpTicketException, JsonProcessingException, DocumentException;

	@ApiOperationSupport(order = 65, author = "zhaoyue@formssi.com")
	@ApiOperation(value = "Submit Order and Pay", notes = "Generate order and pay", response = PayOrderResponse.class)
    @NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = "OK", response = PayOrderResponse.class))
    @PostMapping(value = "/order/submitAndPay", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PayOrderResponse> submitOrderAndPay(
    		@ApiParam(value = "Product Put on Sale Request", required = true)@Valid @RequestBody SubmitAndPayRequest submitAndPayRequest) throws MvpTicketException, JsonProcessingException, DocumentException;
	
	@ApiOperation(value = "Check Order Status", nickname = "checkOrderStatus", notes = "Check Order Status", response = String.class)
	@ApiOperationSupport(order = 70, author = "zhaoyue@formssi.com")
	@NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = String.class))
    @GetMapping(value = "/checkOrderStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CheckOrderStatusResponse> checkOrderStatus(@ApiParam(value = "Order ID", required = true)@RequestParam(value = "orderId") String orderId) throws MvpTicketException;
	
	@ApiOperation(value = "find Order By trade No.", nickname = "findOrderByTradeNo.", notes = "find Order By trade No.", response = FindOrderResponse.class)
	@ApiOperationSupport(order = 80, author = "mojianheng@formssi.com")
	@NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = FindOrderResponse.class))
    @PostMapping(value = "/findOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FindOrderResponse> findOrder(@RequestBody FindOrderRequest request) throws MvpTicketException;
	
	@ApiOperationSupport(order = 90, author = "zhongwanxin@formssi.com")
    @ApiOperation(value = "refund Order", nickname = "refundOrder", notes = "refund Order", response = BaseResponse.class)
    @NeedToken
    @ResponseConfig
    @PostMapping(value = "/refundOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderRefundResponse> refundOrder(@ApiParam(value = "refund Order Request", required = true)@Valid @RequestBody OrderRefundRequest request) throws MvpTicketException;
	
	@ApiOperationSupport(order = 100, author = "zhongwanxin@formssi.com")
	@ApiOperation(value = "pay Result Notify", nickname = "payResultNotify", notes = "pay Result Notify", response = OrderRefundResponse.class)
	@NeedToken
    @ResponseConfig(@ApiResponse(code = 200, message = HttpStatusCodeConstants.MSG_200, response = String.class))
    @PostMapping(value = "/payResultNotify", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<String> notifyPay(@RequestBody String xmlInfo) throws MvpTicketException;

}
