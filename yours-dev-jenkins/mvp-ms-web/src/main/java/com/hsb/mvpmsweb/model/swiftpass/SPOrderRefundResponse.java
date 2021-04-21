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
@ApiModel(description = "Order Refund Model")
@Valid
@ToString
@XmlRootElement(name = "xml")
@XmlType(propOrder = { "version", "charset", "sign_type", "status", "message", "result_code", "mch_id", "device_info",
		"nonce_str", "err_code", "err_msg", "sign", "transaction_id", "out_trade_no", "out_refund_no", "refund_id",
		"refund_channel", "refund_fee", "coupon_refund_fee" })
public class SPOrderRefundResponse {

	@XmlElement
	public String version = null;

	@XmlElement
	public String charset = null;

	@XmlElement
	public String sign_type = null;

	@XmlElement
	public String status = null;

	@XmlElement
	public String message = null;

	@XmlElement
	public String result_code = null;

	@XmlElement
	public String mch_id = null;

	@XmlElement
	public String device_info = null;

	@XmlElement
	public String nonce_str = null;

	@XmlElement
	public String err_code = null;

	@XmlElement
	public String err_msg = null;

	@XmlElement
	public String sign = null;

	@XmlElement
	public String transaction_id = null;

	@XmlElement
	public String out_trade_no = null;

	@XmlElement
	public String out_refund_no = null;

	@XmlElement
	public String refund_id = null;

	@XmlElement
	public String refund_channel = null;

	@XmlElement
	public String refund_fee = null;

	@XmlElement
	public String coupon_refund_fee = null;

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
	public String getSign() {
		return sign;
	}

	@XmlTransient
	public String getTransaction_id() {
		return transaction_id;
	}

	@XmlTransient
	public String getOut_trade_no() {
		return out_trade_no;
	}

	@XmlTransient
	public String getOut_refund_no() {
		return out_refund_no;
	}

	@XmlTransient
	public String getRefund_id() {
		return refund_id;
	}

	@XmlTransient
	public String getRefund_channel() {
		return refund_channel;
	}

	@XmlTransient
	public String getRefund_fee() {
		return refund_fee;
	}

	@XmlTransient
	public String getCoupon_refund_fee() {
		return coupon_refund_fee;
	}

}
