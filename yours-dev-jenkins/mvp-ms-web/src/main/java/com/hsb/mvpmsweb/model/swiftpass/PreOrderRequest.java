package com.hsb.mvpmsweb.model.swiftpass;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
@Component
@ConfigurationProperties(prefix = "swiftpass.preorder.request")
@JacksonXmlRootElement(localName = "xml")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PreOrderRequest {
	
	private String service = "pay.alipay.app.intl";
	
	private String version;
	
	private String charset;
	
	@JacksonXmlProperty(localName = "sign_type")
	private String signType = "MD5";
	
	@JacksonXmlProperty(localName = "mch_id")
	private String mchId = "305510000018";
	
	@JacksonXmlProperty(localName = "out_trade_no")
	private String outTradeNo;
	
	@JacksonXmlProperty(localName = "device_info")
	private String deviceInfo;
	
	private String body;
	
	private String attach;
	
	@JacksonXmlProperty(localName = "total_fee")
	private String totalFee;
	
	@JacksonXmlProperty(localName = "mch_create_ip")
	private String mchCreateIp;
	
	@JacksonXmlProperty(localName = "payment_inst")
	private String paymentInst;
	
	@JacksonXmlProperty(localName = "notify_url")
	private String notifyUrl;
	
	@JacksonXmlProperty(localName = "callback_url")
	private String callbackUrl;
	
	@JacksonXmlProperty(localName = "time_start")
	private String timeStart;
	
	@JacksonXmlProperty(localName = "time_expire")
	private String timeExpire;
	
	@JacksonXmlProperty(localName = "op_user_id")
	private String opUserId;
	
	@JacksonXmlProperty(localName = "goods_tag")
	private String goodsTag;
	
	@JacksonXmlProperty(localName = "nonce_str")
	private String nonceStr;
	
	private String sign;

}
