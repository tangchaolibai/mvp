package com.hsb.mvpmsweb.model.swiftpass;

import io.swagger.annotations.ApiModel;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * This entity is used to Pay Result Notify request.
 */
@ApiModel(description = "This entity is used to Pay Result Notify request.")
@Valid
@ToString
@Setter
@XmlRootElement(name = "xml")
@XmlType(propOrder = { "version", "charset", "sign_type", "status", "message", "result_code", "mch_id", "device_info",
		"nonce_str", "err_code", "err_msg", "openid", "trade_type", "pay_result", "pay_info", "transaction_id",
		"out_transaction_id", "out_trade_no", "total_fee", "fee_type", "attach", "bank_type", "time_end", "sign" })
public class SPPayNotifyResultReqest {

	@XmlElement(name = "version")
	public String version = null;

	@XmlElement(name = "charset")
	public String charset = null;

	@XmlElement(name = "sign_type")
	public String sign_type = null;

	@XmlElement(name = "status")
	public String status = null;

	@XmlElement(name = "message")
	public String message = null;

	@XmlElement(name = "result_code")
	public String result_code = null;

	@XmlElement(name = "mch_id")
	public String mch_id = null;

	@XmlElement(name = "device_info")
	public String device_info = null;

	@XmlElement(name = "nonce_str")
	public String nonce_str = null;

	@XmlElement(name = "err_code")
	public String err_code = null;

	@XmlElement(name = "err_msg")
	public String err_msg = null;

	@XmlElement(name = "trade_type")
	public String trade_type = null;

	@XmlElement(name = "openid")
	public String openid = null;

	@XmlElement(name = "pay_result")
	public String pay_result = null;

	@XmlElement(name = "pay_info")
	public String pay_info = null;

	@XmlElement(name = "transaction_id")
	public String transaction_id = null;

	@XmlElement(name = "out_transaction_id")
	public String out_transaction_id = null;

	@XmlElement(name = "out_trade_no")
	public Integer out_trade_no = null;

	@XmlElement(name = "total_fee")
	public BigDecimal total_fee = null;

	@XmlElement(name = "fee_type")
	public String fee_type = null;

	@XmlElement(name = "attach")
	public String attach = null;

	@XmlElement(name = "bank_type")
	public String bank_type = null;

	@XmlElement(name = "time_end")
	public String time_end = null;

	@XmlElement(name = "sign")
	public String sign = null;

	@XmlTransient
	public String getVersion() {
		return version;
	}

	@XmlTransient
	public String getCharset() {
		return charset;
	}

	@XmlTransient
	public String getSign_type() {
		return sign_type;
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
	public String getResult_code() {
		return result_code;
	}

	@XmlTransient
	public String getMch_id() {
		return mch_id;
	}

	@XmlTransient
	public String getDevice_info() {
		return device_info;
	}

	@XmlTransient
	public String getNonce_str() {
		return nonce_str;
	}

	@XmlTransient
	public String getErr_code() {
		return err_code;
	}

	@XmlTransient
	public String getErr_msg() {
		return err_msg;
	}

	@XmlTransient
	public String getTrade_type() {
		return trade_type;
	}

	@XmlTransient
	public String getOpenid() {
		return openid;
	}

	@XmlTransient
	public String getPay_result() {
		return pay_result;
	}

	@XmlTransient
	public String getPay_info() {
		return pay_info;
	}

	@XmlTransient
	public String getTransaction_id() {
		return transaction_id;
	}

	@XmlTransient
	public String getOut_transaction_id() {
		return out_transaction_id;
	}

	@XmlTransient
	public Integer getOut_trade_no() {
		return out_trade_no;
	}

	@XmlTransient
	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	@XmlTransient
	public String getFee_type() {
		return fee_type;
	}

	@XmlTransient
	public String getAttach() {
		return attach;
	}

	@XmlTransient
	public String getBank_type() {
		return bank_type;
	}

	@XmlTransient
	public String getTime_end() {
		return time_end;
	}

	@XmlTransient
	public String getSign() {
		return sign;
	}

}
