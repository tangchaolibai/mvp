package com.hsb.mvpmsweb.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.hsb.mvpmsweb.api.exception.MvpTicketException;
import com.hsb.mvpmsweb.api.exception.MvpTicketExceptionCode;
import com.hsb.mvpmsweb.api.exception.MvpWebException;
import com.hsb.mvpmsweb.constant.BaseConstants;
import com.hsb.mvpmsweb.constant.SecureConstants;
import com.hsb.mvpmsweb.constant.SwiftPassConstants;
import com.hsb.mvpmsweb.mapper.OrderMapper;
import com.hsb.mvpmsweb.mapper.OrderRefundMapper;
import com.hsb.mvpmsweb.mapper.PerformenceMapper;
import com.hsb.mvpmsweb.mapper.ProductMapper;
import com.hsb.mvpmsweb.model.domain.OrderRefund;
import com.hsb.mvpmsweb.model.domain.Orders;
import com.hsb.mvpmsweb.model.domain.Performence;
import com.hsb.mvpmsweb.model.domain.Product;

import com.hsb.mvpmsweb.model.payload.ticket.CheckOrderStatusResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.FindOrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.OrderRefundResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.OrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.PayOrderResponseData;
import com.hsb.mvpmsweb.model.payload.ticket.request.FindOrderRequest;
import com.hsb.mvpmsweb.model.payload.ticket.request.OrderRefundRequest;
import com.hsb.mvpmsweb.model.swiftpass.PreOrderRequest;
import com.hsb.mvpmsweb.model.swiftpass.PreOrderResponse;
import com.hsb.mvpmsweb.model.swiftpass.SPFindOrderRequest;
import com.hsb.mvpmsweb.model.swiftpass.SPOrderRefundRequest;
import com.hsb.mvpmsweb.model.swiftpass.SPOrderRefundResponse;
import com.hsb.mvpmsweb.model.swiftpass.SPPayNotifyResultReqest;
import com.hsb.mvpmsweb.service.OrderService;
import com.hsb.mvpmsweb.util.RedisUtils;
import com.hsb.mvpmsweb.util.WebUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Value("${swiftpass.mchId}")
	private String mchId;
	
	@Value("${swiftpass.userKey}")
	private String userKey;
	
	@Value("${swiftpass.findOrder.url}")
	private String findOrderUrl;
	
	@Value("${swiftpass.findOrder.service}")
	private String findOrderService;
	
	@Value("${swiftpass.findOrder.xml.request}")
	private String requestFilePath;

	@Value("${swiftpass.findOrder.xml.response}")
	private String responseFilePath;

	@Value("${swiftpass.findOrder.xml.enable}")
	private Boolean isGenXML;

	@Value("${swiftpass.refund.service}")
	private String orderRefundService;
	
	@Value("${swiftpass.preorder.url}")
	private String preOrderurl;
	
	@Autowired
	private PerformenceMapper performenceMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderRefundMapper orderRefundMapper;
	
	@Autowired
	private PreOrderRequest preOrderRequest;
	
	@Autowired
	private MappingJackson2XmlHttpMessageConverter xmlConverter;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private RestTemplate restTemplate;
	
	protected static final String SUCCESS = "success";
	protected static final String FAIL = "fail";
	public static final String DATE_FORMAT = "yyyyMMddhhmmss";
    
	private ObjectMapper xmlMapper;
	private String userKeyStr;
	 
    @PostConstruct
    private void init() {
        xmlMapper = xmlConverter.getObjectMapper();
        userKeyStr = "key=" + userKey;
    }

	@Override
	public synchronized OrderResponseData generateOrder(@Valid int productId, @Valid int count) throws MvpTicketException {
		Product product = productMapper.selectById(productId);
		if (product == null)
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_002_01);
		Performence performence = performenceMapper.selectById(product.getPerformenceId());
		if (performence == null)
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_002_02);
		int ticketLeft = performence.getTicketLeft();
		if (ticketLeft == 0)
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_001_01);
		ticketLeft--;
		performence.setTicketLeft(ticketLeft);
		performenceMapper.updateById(performence);
		
		Orders order = new Orders()
				.setCount(count)
				.setCurrency(product.getCurrency())
				.setPrice(performence.getPrice().multiply(new BigDecimal(count)))
				.setProductDescription("测试商品")
				.setProductId(productId)
				.setStatus("NOTPAY")
				.setTimeoutMinutes(10);
		orderMapper.insert(order);
		return new OrderResponseData()
				.setCastMember(performence.getCastMember())
				.setConcertName(performence.getName())
				.setCount(count)
				.setCurrency(product.getCurrency())
				.setDate(performence.getDate())
				.setEndTime(performence.getEndTime())
				.setEntityId(order.getEntityId())
				.setLocation(performence.getLocation())
				.setPrice(performence.getPrice())
				.setStaratTime(performence.getStartTime())
				.setTotalPrice(order.getPrice());
	}

	@Override
	public PayOrderResponseData payOrder(@Valid int orderId, String paymentInstance, String callBackUrl) throws JsonProcessingException, MvpTicketException, DocumentException {
		Orders order = orderMapper.selectById(orderId);
		if(order == null) throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_02);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		preOrderRequest = generatePreOrderRequest(order, paymentInstance, callBackUrl);
		String xmlRequest = xmlMapper.writeValueAsString(preOrderRequest);
		logger.info("Xml Request : {}", xmlRequest);
		logger.info("================== Calling Pre-Order Interface Start. Order ID : {}. Timestamp : {}", orderId, LocalDateTime.now());
		HttpEntity<String> formEntity = new HttpEntity<>(xmlRequest, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(preOrderurl, formEntity, String.class);
		logger.info("================== Calling Pre-Order Interface End. Order ID : {}. Timestamp : {}", orderId, LocalDateTime.now());
		logger.info("Xml Response : {}", responseEntity.getBody());
		PreOrderResponse response = xmlMapper.readValue(responseEntity.getBody(), PreOrderResponse.class);
		if("0".equals(response.getStatus())) {
			PayOrderResponseData data = new PayOrderResponseData();
			if(!validateSign(responseEntity)) 
				throw new MvpTicketException(MvpTicketExceptionCode.ERROR_001_05);
			if(!"0".equals(response.getResultCode())) 
				return data.setIsSuccess(false).setErrorCode(response.getErrCode()).setErrorMsg(response.getErrMsg());
			return data.setIsSuccess(true).setOrderString(response.getOrderString());
		} else {
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_001_02, new String[] {response.getMessage()});
		}
	}
	
	private PreOrderRequest generatePreOrderRequest(Orders order, String paymentInstance, String callBackUrl) {
		preOrderRequest.setOutTradeNo(String.valueOf(order.getEntityId()));
		preOrderRequest.setBody(order.getProductDescription());
		//测试用，1分钱
		preOrderRequest.setTotalFee(String.valueOf(1));
		preOrderRequest.setMchCreateIp(NetUtil.getIpByHost(NetUtil.getLocalhostStr()));
		if("HKD".equals(order.getCurrency())) 
			preOrderRequest.setPaymentInst(paymentInstance);
		preOrderRequest.setTimeStart(DateUtil.format(order.getCreationDateTime(), DATE_FORMAT));
		preOrderRequest.setTimeExpire(DateUtil.format(order.getCreationDateTime().plusMinutes(order.getTimeoutMinutes()), DATE_FORMAT));
		preOrderRequest.setNonceStr(RandomStringUtils.randomAlphanumeric(32));
		preOrderRequest.setCallbackUrl(callBackUrl);
		preOrderRequest.setSign(generateSign(preOrderRequest));
		logger.info("Request Entity of Pre-Order interface of order ID {} : {}", order.getEntityId(), preOrderRequest);
		return preOrderRequest;
	}

	private boolean validateSign(ResponseEntity<String> responseEntity) throws JsonProcessingException {
		Map<String, String> fieldMap = xmlMapper.readValue(responseEntity.getBody(), new TypeReference<Map<String, String>>() {});
		String sign = generateSign(fieldMap);
		String signTarget = fieldMap.get("sign");
		return sign.equals(signTarget);
	}
	
	private String generateSign(Map<String, String> map) {
		Map<String, String> mapFilted = map.entrySet().stream()
				.filter(e -> !"sign".equals(e.getKey()))
				.filter(e -> StrUtil.isNotBlank(e.getValue()))
				.collect(Collectors.toMap(Entry<String, String>::getKey, Entry<String, String>::getValue));
		Map<String, String> sortedMap = new TreeMap<>(mapFilted);
		StringBuilder builder = new StringBuilder();
		sortedMap.entrySet().stream().forEach(e -> {builder.append(e.getKey() + "=" + e.getValue() + "&");});
		builder.append(userKeyStr);
		logger.info("Origin string bulder {}", builder.toString());
		return generateSign(builder.toString(), map.get("sign_type"));
	}
	
	private <T> String generateSign(T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		Map<String, String> fieldsMap = Stream.of(fields).collect(Collectors.toMap(this::getRealName, field -> getValue(field, t)));
		return generateSign(fieldsMap);
	}

	private String generateSign(String originStr, String signType) {
		if(StrUtil.isBlank(signType))
			generateSign(originStr, SecureConstants.SECURE_MD5);
		String sign = null;
		switch (signType.toUpperCase()) {
		case SecureConstants.SECURE_MD5:
			sign = SecureUtil.md5(originStr);
			break;
		case SecureConstants.SECURE_SHA256:
			sign = SecureUtil.sha256(originStr);
			break;
		default:
			sign = SecureUtil.md5(originStr);
		}
		return sign.toUpperCase();
	}

	private String getRealName(Field field) {
		String name = null;
		if(field.getAnnotation(JacksonXmlProperty.class) != null) {
			JacksonXmlProperty annotation = field.getAnnotation(JacksonXmlProperty.class);
			name = annotation.localName();
		} else {
			name = field.getName();
		}
		return name;
	}
	
	private <T> String getValue(Field field, T t) {
		field.setAccessible(true);
		try {
			String value = (String)field.get(t);
			return value != null ? value : "";
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(MvpTicketExceptionCode.ERROR_001_04);
		}
	}

	@Override
	public CheckOrderStatusResponseData checkOrderStatus(String orderId) throws MvpTicketException {
		Orders order = orderMapper.selectById(orderId);
		if (order == null) {
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_02);
		}
		String status = order.getStatus();
		if(!"NOTPAY".equals(status))
			return new CheckOrderStatusResponseData(status);
		else {
			FindOrderRequest findOrderRequest = new FindOrderRequest();
			findOrderRequest.setOutTradeNo(orderId);
			FindOrderResponseData data = findOrder(findOrderRequest);
			if(SUCCESS.equalsIgnoreCase(data.getTradeState()))
				return new CheckOrderStatusResponseData(SUCCESS);
		}
		return new CheckOrderStatusResponseData(FAIL);
	}

	@Override
	public FindOrderResponseData findOrder(FindOrderRequest request) throws MvpTicketException {
		FindOrderResponseData result = new FindOrderResponseData();
		StringBuffer buffer = new StringBuffer();
        try {
        	SPFindOrderRequest spRequest = toSpRequst(request);
            String xmlInfo = WebUtils.beanToXml(spRequest, SPFindOrderRequest.class);
        	
        	if(isGenXML) {
        		Document doc = DocumentHelper.parseText(xmlInfo);
        		WebUtils.genXml(doc, requestFilePath + "/findOrderRequest.xml");
        	}
        	
            String oldStr = WebUtils.genOriStrByXmlBean(spRequest) + userKeyStr;
            String sign = null;
            switch (spRequest.getSignType().toUpperCase()) {
            // 目前仅提供'MD5'加密
			case SecureConstants.SECURE_MD5:
				sign = WebUtils.genMD5(oldStr);
				break;
			case SecureConstants.SECURE_SHA256:
				sign = WebUtils.genSHA256(oldStr);
				break;
			case SecureConstants.SECURE_RSA:
			default:
				sign = WebUtils.genMD5(oldStr);
				break;
			}
            logger.info("Sign: [{}]", sign);
            
            xmlInfo = StrUtil.replace(xmlInfo, "</xml>", "<sign>" + sign + "</sign></xml>");
            logger.info("Request XML:\n{}", xmlInfo);
            
            URL url = new URL(findOrderUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod(BaseConstants.REQUEST_METHOD_POST);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), BaseConstants.ENCODING_UTF8);
            out.write(xmlInfo);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), BaseConstants.ENCODING_UTF8));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                buffer.append(line);
            }
        } catch (IOException | DocumentException e) {
            throw new MvpTicketException(MvpTicketExceptionCode.ERROR_004_02, new String[] { e.getMessage() });
        } catch (MvpWebException e) {
        	throw new MvpTicketException(MvpTicketExceptionCode.ERROR_004_03, new String[] { e.getMessage() });
		}
        
        result = toFindOrderResponseData(buffer.toString());
        return result;
	}

	@Override
	public OrderRefundResponseData refundOrder(OrderRefundRequest request) throws MvpTicketException {
		OrderRefundResponseData result = new OrderRefundResponseData();
		Orders order = orderMapper.selectById(request.getTransaction_id());
		if (order == null) {
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_02);
		} else {
			if (request.getRefund_fee().compareTo(order.getPrice()) == -1) {
				throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_03);
			}
			order.setStatus("REFUND");
			orderMapper.updateById(order);
		}
		SPOrderRefundRequest spRequest = new SPOrderRefundRequest();
		spRequest.setTransaction_id(request.getTransaction_id());
		spRequest.setRefund_fee(request.getRefund_fee());
		spRequest.setOut_trade_no(request.getOutTradeNo());
		spRequest.setNonce_str(WebUtils.genRandomStr(32));
		spRequest.setService(orderRefundService);
		spRequest.setMch_id(mchId);
		spRequest.setOp_user_id(mchId);
		spRequest.setSign_type(SecureConstants.SECURE_MD5);
		spRequest.setVersion(SwiftPassConstants.API_VERSION_DEFAULT);
		spRequest.setCharset(BaseConstants.ENCODING_UTF8);
		spRequest.setTotal_fee(order.getPrice());
		spRequest.setRefund_channel("ORIGINAL");
		OrderRefund orderRefund;
		if (spRequest.out_refund_no == null) {
			orderRefund = new OrderRefund();
			orderRefund.setOrderId(spRequest.transaction_id);
			orderRefund.setPayAmount(order.getPrice());
			orderRefund.setRefundAmount(spRequest.refund_fee);
			orderRefund.setRemainderAmount(order.getPrice());
			orderRefundMapper.insert(orderRefund);
		} else {
			orderRefund = orderRefundMapper.selectById(spRequest.out_refund_no);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			orderRefund.setRecreationDateTime(format.format(LocalDateTime.now()));
			orderRefundMapper.updateById(orderRefund);
		}
		spRequest.setOut_refund_no(orderRefund.getEntityId());
		StringBuffer buffer = new StringBuffer();
		try {
			String xmlInfo = WebUtils.beanToXml(spRequest, SPOrderRefundRequest.class);

			if (isGenXML) {
				Document doc = DocumentHelper.parseText(xmlInfo);
				WebUtils.genXml(doc, requestFilePath + "/refundOrderRequest.xml");
			}

			String oldStr = WebUtils.genOriStrByXmlBean(spRequest) + userKeyStr;
			String sign = null;
			switch (spRequest.sign_type.toUpperCase()) {
			case SecureConstants.SECURE_MD5:
				sign = WebUtils.genMD5(oldStr);
				break;
			case SecureConstants.SECURE_SHA256:
				sign = WebUtils.genSHA256(oldStr);
				break;
			case SecureConstants.SECURE_RSA:
				break;
			default:
				break;
			}
			logger.info("Sign: [{}]", sign);

			xmlInfo = StrUtil.replace(xmlInfo, "</xml>", "<sign>" + sign + "</sign></xml>");
			logger.info("Request XML:\n{}", xmlInfo);

			URL url = new URL(findOrderUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(BaseConstants.REQUEST_METHOD_POST);
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), BaseConstants.ENCODING_UTF8);
			out.write(xmlInfo);
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), BaseConstants.ENCODING_UTF8));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				buffer.append(line);
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		} catch (MvpWebException e) {
			e.printStackTrace();
		}

		result = toOrdeRefundResponseData(buffer.toString());
		if (result != null) {
			if (result.getResult_code() != null) {
				orderRefund.setRemainderAmount(order.getPrice().subtract(request.getRefund_fee()));
				orderRefund.setRefundStatus("Y");
				orderRefund.setMessage(result.getMessage());
			} else {
				orderRefund.setRemainderAmount(orderRefund.getRemainderAmount().subtract(BigDecimal.valueOf(0)));
				orderRefund.setRefundStatus("N");
				orderRefund.setMessage(result.getErr_msg());
			}
			orderRefundMapper.updateById(orderRefund);
		} else
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_04);

		return result;
	}
 
	@Override
	public String notifyPay(String xmlInfo) throws MvpTicketException {
		if ("".equals(xmlInfo) || xmlInfo == null) {
			throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_04);
		}
		SPPayNotifyResultReqest request = toPayResultNotifyRequestData(xmlInfo);
		Orders order = orderMapper.selectById(request.getOut_trade_no());
		Integer outTradeNo = (Integer) redisUtils.get(request.getOut_trade_no().toString());
		if (outTradeNo == null) {
			redisUtils.set(request.getOut_trade_no().toString(), request.getOut_trade_no());
			if (order == null) {
				redisUtils.del(request.getOut_trade_no().toString());
				throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_02);
			}
			if ("NOTPAY".equals(order.getStatus())) {
				// validation
				if (!request.getOut_trade_no().equals(order.getEntityId())
						|| order.getPrice().compareTo(request.getTotal_fee())!=0) {
					redisUtils.del(request.getOut_trade_no().toString());
					throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_05);
				}
				// update status
				if ("0".equals(request.getPay_result()))
					order.setStatus("SUCCESS");
				else
					order.setStatus("FAIL");
				orderMapper.updateById(order);
			}
			redisUtils.del(request.getOut_trade_no().toString());
		} else {
			redisUtils.del(request.getOut_trade_no().toString());
			return "fail";
		}
		// return
		redisUtils.del(request.getOut_trade_no().toString());
		return "success";
	}

	private SPFindOrderRequest toSpRequst(FindOrderRequest request) {
		SPFindOrderRequest result = new SPFindOrderRequest();
		if(request != null) {
			result.setService(findOrderService);
			result.setSignType(SecureConstants.SECURE_MD5);
			result.setVersion(SwiftPassConstants.API_VERSION_DEFAULT);
			result.setCharset(BaseConstants.ENCODING_UTF8);
			result.setMchId(mchId);
			result.setNonceStr(WebUtils.genRandomStr(32));
			result.setOutTradeNo(request.getOutTradeNo());
		}
		return result;
	}

	private FindOrderResponseData toFindOrderResponseData(String xmlInfo) throws MvpTicketException {
		FindOrderResponseData result = new FindOrderResponseData();
		if(xmlInfo != null) {
			logger.info("Response XML:\n{}", xmlInfo);
			try {
				if(isGenXML) {
					Document doc = DocumentHelper.parseText(xmlInfo);
					WebUtils.genXml(doc, responseFilePath + "/findOrderResponse.xml");
				}
				
				Object responseObj = WebUtils.xmlToJson(xmlInfo).get(SwiftPassConstants.OBJECT_XML);
				JSONObject responseJsonObject = JSONObject.parseObject(responseObj.toString());
				String responseOriStr = WebUtils.genOriStrByJsonObject(responseJsonObject) + userKeyStr;
				if(!StrUtil.equals(WebUtils.genMD5(responseOriStr), WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.API_SIGN))) {
					logger.error("Sign: [{}] verification failed!", WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.API_SIGN));
					throw new MvpTicketException(MvpTicketExceptionCode.ERROR_004_05);
				}
				
				String status = WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.STATUS);
				result.setStatus(status);
				if(StrUtil.equals(status, SwiftPassConstants.STATUS_SUCCESS)) {
					String resultCode = WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.RESULT_CODE);
					result.setErrCode(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.ERROR_CODE));
					result.setErrMsg(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.ERROR_MSG));
					result.setMchId(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.MCH_ID));
					result.setResultCode(resultCode);
					if(StrUtil.equals(resultCode, SwiftPassConstants.RESULT_CODE_SUCCESS)) {
						String tradeState = WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.TRADE_STATE);
						result.setTradeState(tradeState);
						if(StrUtil.equals(tradeState, SwiftPassConstants.TRADE_STATE_SUCCESS)) {
							result.setOpenid(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.OPEN_ID));
							result.setTradeType(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.TRADE_TYPE));
							result.setTransactionId(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.TRANSACTION_ID));
							result.setOutTransactionId(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.OUT_TRANSACTION_ID));
							result.setOutTradeNo(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.OUT_TRADE_NO));
							result.setTotalFee(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.TOTAL_FEE));
							result.setTimeEnd(WebUtils.getJsonObjectValue(responseJsonObject, SwiftPassConstants.TIME_END));
						}
					}else {
						throw new MvpTicketException(MvpTicketExceptionCode.ERROR_003_02);
					}
				}else {
					throw new MvpTicketException(MvpTicketExceptionCode.ERROR_004_02, new String[] { StrUtil.toString(responseJsonObject.get(SwiftPassConstants.MESSAGE)) });
				}
			} catch (DocumentException | JSONException | MvpWebException e) {
				throw new MvpTicketException(MvpTicketExceptionCode.ERROR_004_03, new String[] { e.getMessage() });
			}
		}
		return result;
	}

	private OrderRefundResponseData toOrdeRefundResponseData(String xmlInfo) throws MvpTicketException {
		OrderRefundResponseData result = new OrderRefundResponseData();
		if (xmlInfo != null) {
			logger.info("Response XML:\n{}", xmlInfo);
			try {
				if (isGenXML) {
					Document doc = DocumentHelper.parseText(xmlInfo);
					WebUtils.genXml(doc, responseFilePath + "/refundOrderResponse.xml");
				}
				
				SPOrderRefundResponse spresult = (SPOrderRefundResponse) WebUtils.xmlToBean(xmlInfo,
						SPOrderRefundResponse.class);
				String responseOriStr = WebUtils.genOriStrByXmlBean(spresult) + userKeyStr;
				if (!StrUtil.equals(WebUtils.genMD5(responseOriStr), spresult.getSign())) {
					logger.error("Sign: [{}] verification failed!", spresult.getSign());
					throw new MvpTicketException(MvpTicketExceptionCode.ERROR_001_05);
				}
				
				result.setCharset(spresult.getCharset());
				result.setCoupon_refund_fee(spresult.getCoupon_refund_fee());
				result.setDevice_info(spresult.getDevice_info());
				result.setErr_code(spresult.getErr_code());
				result.setErr_msg(spresult.getErr_msg());
				result.setMch_id(spresult.getMch_id());
				result.setMessage(spresult.getMessage());
				result.setNonce_str(spresult.getNonce_str());
				result.setOut_refund_no(spresult.getOut_refund_no());
				result.setOut_trade_no(spresult.getOut_trade_no());
				result.setRefund_channel(spresult.getRefund_channel());
				result.setRefund_fee(spresult.getRefund_fee());
				result.setRefund_id(spresult.getRefund_id());
				result.setResult_code(spresult.getResult_code());
				result.setSign(spresult.getSign());
				result.setSign_type(spresult.getSign_type());
				result.setStatus(spresult.getStatus());
				result.setTransaction_id(spresult.getTransaction_id());
				result.setVersion(spresult.getVersion());
			} catch (DocumentException | MvpWebException e) {
				e.printStackTrace();
			} 
		}
		return result;
	}

	private SPPayNotifyResultReqest toPayResultNotifyRequestData(String xmlInfo) throws MvpTicketException {
		SPPayNotifyResultReqest spresult = null;
		if (xmlInfo != null) {
			logger.info("Response XML:\n{}", xmlInfo);
			try {
				if (isGenXML) {
					Document doc = DocumentHelper.parseText(xmlInfo);
					WebUtils.genXml(doc, responseFilePath + "/refundOrderResponse.xml");
				}
				
				spresult = (SPPayNotifyResultReqest) WebUtils.xmlToBean(xmlInfo, SPPayNotifyResultReqest.class);
				String responseOriStr = WebUtils.genOriStrByXmlBean(spresult) + userKeyStr;
				String sign = WebUtils.genMD5(responseOriStr);
				if (!StrUtil.equals(sign, spresult.getSign())) {
					logger.error("Sign: [{}] verification failed!", spresult.getSign());
					throw new MvpTicketException(MvpTicketExceptionCode.ERROR_001_05);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (MvpWebException e) {
				e.printStackTrace();
			}
		}
		return spresult;
	}

}
