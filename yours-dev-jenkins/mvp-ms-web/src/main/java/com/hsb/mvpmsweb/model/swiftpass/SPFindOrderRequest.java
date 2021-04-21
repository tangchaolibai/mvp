package com.hsb.mvpmsweb.model.swiftpass;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to find order request(swiftpass).
 */
@ApiModel(description = "This entity is used to find order request(swiftpass).")
@Validated
@ToString
@Setter
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"service", "version", "charset", "signType", "mchId", "outTradeNo", "nonceStr"})
public class SPFindOrderRequest {
	
	@ApiModelProperty(required = true, value = "service", example = "unified.trade.query")
	@XmlElement(name = "service")
	public String service = null;
	
	@ApiModelProperty(required = true, value = "version", example = "2.0")
	@XmlElement(name = "version")
	public String version = null;
	
	@ApiModelProperty(required = true, value = "charset", example = "UTF-8")
	@XmlElement(name = "charset")
	public String charset = null;
	
	@ApiModelProperty(required = true, value = "sign_type", example = "MD5")
	@XmlElement(name = "sign_type")
	public String signType = null;
	
	@ApiModelProperty(required = true, value = "mch_id", example = "127510000155")
	@XmlElement(name = "mch_id")
	public String mchId = null;
	
	@ApiModelProperty(required = true, value = "out_trade_no", example = "141903606228")
	@XmlElement(name = "out_trade_no")
	public String outTradeNo = null;
	
	@ApiModelProperty(required = true, value = "nonce_str", example = "1542940680925")
	@XmlElement(name = "nonce_str")
	public String nonceStr = null;

	@XmlTransient
	public String getService() {
		return service;
	}

	@XmlTransient
	public String getVersion() {
		return version;
	}

	@XmlTransient
	public String getCharset() {
		return charset;
	}

	@XmlTransient
	public String getSignType() {
		return signType;
	}

	@XmlTransient
	public String getMchId() {
		return mchId;
	}

	@XmlTransient
	public String getOutTradeNo() {
		return outTradeNo;
	}

	@XmlTransient
	public String getNonceStr() {
		return nonceStr;
	}
	
}
