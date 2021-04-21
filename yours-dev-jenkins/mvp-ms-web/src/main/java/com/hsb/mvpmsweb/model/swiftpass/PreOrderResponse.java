package com.hsb.mvpmsweb.model.swiftpass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@JacksonXmlRootElement(localName = "xml")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreOrderResponse {

	private String version;
	
	private String charset;
	
	@JacksonXmlProperty(localName = "sign_type")
	private String signType;
	
	private String status;
	
	private String message;
	
	@JacksonXmlProperty(localName = "result_code")
	private String resultCode;
	
	@JacksonXmlProperty(localName = "nonce_str")
	private String nonceStr;
	
	@JacksonXmlProperty(localName = "err_code")
	private String errCode;
	
	@JacksonXmlProperty(localName = "err_msg")
	private String errMsg; 
	
	private String sign;
	
	@JacksonXmlProperty(localName = "pay_info")
	private String orderString;

}
