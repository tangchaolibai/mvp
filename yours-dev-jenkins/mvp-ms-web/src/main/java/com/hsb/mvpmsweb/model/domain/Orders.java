package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ORDERS")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    /**
     * 商品ID
     */
    private Integer productId;
    
    /**
     * 购买数量
     */
    private Integer count;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 成交价格
     */
    private BigDecimal price;

    /**
     * 货币
     */
    private String currency;

    /**
     * 生成时间
     */
    @TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationDateTime;

    /**
     * 超时时间
     */
    private Integer timeoutMinutes;

    /**
     * 订单状态，SUCCESS—支付成功，REFUND—轉入退款，NOTPAY—未支付，CLOSED—已關閉，REVOK—已撤銷
     */
    private String status;

}
