package com.hsb.mvpmsuser.model.domain;

import java.io.Serializable;
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
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("COUNTRIES_REGIONS")
public class CountriesAndRegions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ENTITY_ID", type = IdType.AUTO)
    private Integer entityId;
    
    @TableField(value = "NAME_EN_US")
    private String nameEnUs;
    
    @TableField(value = "NAME_ZH_CN")
    private String nameZhCn;
    
    @TableField(value = "NAME_ZH_HK")
    private String nameZhHk;
    
    @TableField(value = "IDENTIFIER")
    private String identifier;
    
    @TableField(value = "AREA_CODE")
    private Integer areaCode;
    
    @TableField(value = "TIME_DIFF")
    private Float timeDifferent;
    
    @TableField(value = "ACTIVE")
    private Boolean active;
    
    @TableField(value = "PROHIBIT")
    private Boolean prohibit;
    
    @TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationDateTime;
    
    @TableField(value = "LAST_UPDATE_DATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdateDateTime;
    
}
