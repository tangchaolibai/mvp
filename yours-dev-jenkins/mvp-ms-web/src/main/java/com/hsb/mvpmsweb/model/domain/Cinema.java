package com.hsb.mvpmsweb.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
@TableName("CINEMA")
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    /**
     * cinema name
     */
    private String name;

    /**
     * cinema location city
     */
    private String locationCity;

    /**
     * cinema location region
     */
    private String locationRegion;

    /**
     * cinema location business district
     */
    private String locationBusinessDistrict;

    /**
     * cinema address
     */
    private String address;

    /**
     * imax enable
     */
    private Boolean imaxEnable;

    /**
     * Dolby enable
     */
    private Boolean dolbyEnable;

    /**
     * cgs enable
     */
    private Boolean cgsEnable;

    /**
     * refund enable
     */
    private Boolean refundEnable;

    /**
     * endorse enable
     */
    private Boolean endorseEnable;

    /**
     * free for 3d glasses enable
     */
    private Boolean free3dEnable;

}
