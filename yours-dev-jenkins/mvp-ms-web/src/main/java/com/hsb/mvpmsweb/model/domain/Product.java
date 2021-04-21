package com.hsb.mvpmsweb.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上架商品ID
     */
    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 币种
     */
    private String currency;

    /**
     * 商品缩略图路径
     */
    private String photoPath;
    
    /**
     * 商户ID
     */
    private Integer userId;
    
    /**
     * 演出ID
     */
    private Integer performenceId;
    
    /**
     * Creation timestamp
     */
    @TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationDateTime;

}
