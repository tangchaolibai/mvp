package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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
@NoArgsConstructor
@AllArgsConstructor
@TableName("PERFORMENCE")
public class Performence implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 演出场次ID
     */
    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    /**
     * 演出名称
     */
    private String name;

    /**
     * 演出日期
     */
    private LocalDate date;

    /**
     * 演出开始时间
     */
    @TableField("startTime")
    private LocalTime startTime;

    /**
     * 演出结束时间
     */
    @TableField("endTime")
    private LocalTime endTime;

    /**
     * 演出地点
     */
    private String location;

    /**
     * 票价
     */
    private BigDecimal price;

    /**
     * 演职人员
     */
    private String castMember;
    
    /**
     * 余票数量
     */
    private Integer ticketLeft;

}
