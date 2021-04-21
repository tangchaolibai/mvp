package com.hsb.mvpmsticket.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
@TableName("FILM")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Film Id
     */
    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    /**
     * Chinese Film name
     */
    private String chineseName;

    /**
     * Film name in English
     */
    private String englishName;

    /**
     * Director name
     */
    private String directorName;

    /**
     * Main Actor name
     */
    private String mainActor;

    /**
     * Film Duration
     */
    private Integer duration;

    /**
     * Film Type
     */
    private String type;

    /**
     * Film region
     */
    private String region;

    /**
     * release date
     */
    private LocalDate releaseDate;

    /**
     * Breif Introduction
     */
    private String introduction;

    /**
     * Image url
     */
    private String image;

    /**
     * Film score
     */
    private BigDecimal score;

    /**
     * Amount want to see
     */
    private BigDecimal likeAmount;

    /**
     * 2D，3D or orther
     */
    private String demention;

    /**
     * 国语，英语或其他
     */
    private String language;

}
