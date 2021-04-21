package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ORDER_REFUND")
@AllArgsConstructor
@NoArgsConstructor
public class OrderRefund implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *退款单ID
     */
    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;
    
    /**
     *订单成交金额
     */
    @TableField(value = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 该笔退款单的退款金额
     */
    @TableField(value = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 该笔退款单剩余用户支付金额
     */
    @TableField(value = "remainder_amount")
    private BigDecimal remainderAmount;

    /**
     * 退款单状态
     */
    @TableField(value = "refund_status")
    private String refundStatus;

    /**
     * 生成时间
     */
    @TableField(value = "creation_date_time", fill = FieldFill.INSERT)
    private LocalDateTime creationDateTime;

    /**
     * 超时时间
     */
    @TableField(value = "recreation_date_time")
    private String recreationDateTime;
    
    @TableField(value = "message")
    private String message;

}
