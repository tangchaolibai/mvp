package com.hsb.mvpmsweb.model.swiftpass;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import lombok.Setter;
import lombok.ToString;

@Setter
@ApiModel(description = "Find Order By No. Response(swiftpass)")
@Valid
@ToString
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"version", "charset", "signType", "status",
		"message", "resultCode", "mchId", "deviceInfo", "nonceStr",
		"errCode", "errMsg", "sign", "tradeState", "tradeType", "openid"})
public class SPFindOrderResponse { 
	
	@XmlElement(name = "version")
	public String version = null;
	
	@XmlElement(name = "charset")
	public String charset = null;
	
	@XmlElement(name = "sign_type")
	public String signType = null;
	
	@XmlElement(name = "status")
	public String status = null;
	
	@XmlElement(name = "message")
	public String message = null;
	
	@XmlElement(name = "result_code")
	public String resultCode = null;
	
	@XmlElement(name = "mch_id")
	public String mchId = null;
	
	@XmlElement(name = "device_info")
	public String deviceInfo = null;
	
	@XmlElement(name = "nonce_str")
	public String nonceStr = null;

	@XmlElement(name = "err_code")
	public String errCode = null;
	
	@XmlElement(name = "err_msg")
	public String errMsg = null;
	
	@XmlElement(name = "sign")
	public String sign = null;
	
	@XmlElement(name = "trade_state")
	public String tradeState = null;
	
	@XmlElement(name = "trade_type")
	public String tradeType = null;
	
	@XmlElement(name = "openid")
	public String openid = null;

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
	public String getStatus() {
		return status;
	}

	@XmlTransient
	public String getMessage() {
		return message;
	}

	@XmlTransient
	public String getResultCode() {
		return resultCode;
	}

	@XmlTransient
	public String getMchId() {
		return mchId;
	}

	@XmlTransient
	public String getDeviceInfo() {
		return deviceInfo;
	}

	@XmlTransient
	public String getNonceStr() {
		return nonceStr;
	}

	@XmlTransient
	public String getErrCode() {
		return errCode;
	}

	@XmlTransient
	public String getErrMsg() {
		return errMsg;
	}

	@XmlTransient
	public String getSign() {
		return sign;
	}

	@XmlTransient
	public String getTradeState() {
		return tradeState;
	}

	@XmlTransient
	public String getTradeType() {
		return tradeType;
	}

	@XmlTransient
	public String getOpenid() {
		return openid;
	}
	
}
