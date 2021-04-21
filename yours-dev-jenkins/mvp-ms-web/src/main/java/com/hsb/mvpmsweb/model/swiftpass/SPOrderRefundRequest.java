package com.hsb.mvpmsweb.model.swiftpass;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to find order request.
 */
@ApiModel(description = "This entity is used to refund order request.")
@Validated
@ToString
@Setter
@XmlRootElement(name = "xml")
@XmlType(propOrder = { "service", "version", "charset", "sign_type", "mch_id", "out_trade_no", "transaction_id",
		"out_refund_no", "total_fee", "refund_fee", "op_user_id", "refund_channel", "nonce_str" })
public class SPOrderRefundRequest {

	@ApiModelProperty(required = true, value = "service", example = "unified.trade.refund")
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
	public String sign_type = null;

	@ApiModelProperty(required = true, value = "mch_id", example = "127510000155")
	@XmlElement(name = "mch_id")
	public String mch_id = null;

	@ApiModelProperty(required = true, value = "out_trade_no", example = "55")
	@XmlElement(name = "out_trade_no")
	public String out_trade_no = null;

	@ApiModelProperty(required = true, value = "transaction_id", example = "001")
	@XmlElement(name = "transaction_id")
	public Integer transaction_id = null;

	@ApiModelProperty(required = true, value = "out_refund_no", example = "88")
	@XmlElement(name = "out_refund_no")
	public Integer out_refund_no = null;

	@ApiModelProperty(required = true, value = "total_fee", example = "400")
	@XmlElement(name = "total_fee")
	public BigDecimal total_fee = null;

	@ApiModelProperty(required = true, value = "refund_fee", example = "400")
	@XmlElement(name = "refund_fee")
	public BigDecimal refund_fee = null;

	@ApiModelProperty(required = true, value = "op_user_id", example = "60")
	@XmlElement(name = "op_user_id")
	public String op_user_id = null;

	@ApiModelProperty(required = true, value = "refund_channel", example = "ORIGINAL")
	@XmlElement(name = "refund_channel")
	public String refund_channel = null;

	@ApiModelProperty(required = true, value = "nonce_Str", example = "1542940680925")
	@XmlElement(name = "nonce_str")
	public String nonce_str = null;

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
	public String getSign_type() {
		return sign_type;
	}

	@XmlTransient
	public String getMch_id() {
		return mch_id;
	}

	@XmlTransient
	public String getOut_trade_no() {
		return out_trade_no;
	}

	@XmlTransient
	public Integer getTransaction_id() {
		return transaction_id;
	}

	@XmlTransient
	public String getNonce_str() {
		return nonce_str;
	}

	@XmlTransient
	public Integer getOut_refund_no() {
		return out_refund_no;
	}

	@XmlTransient
	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	@XmlTransient
	public BigDecimal getRefund_fee() {
		return refund_fee;
	}

	@XmlTransient
	public String getOp_user_id() {
		return op_user_id;
	}

	@XmlTransient
	public String getRefund_channel() {
		return refund_channel;
	}
}
