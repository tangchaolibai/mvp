package com.hsb.mvpmsweb.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@TableName("FILM_SCHEDULE")
public class FilmSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "entity_id", type = IdType.AUTO)
    private Integer entityId;

    private Integer filmId;

    private Integer cinemaId;

    private LocalDate date;

    /**
     * hall
     */
    private String hall;

    /**
     * Start time
     */
    private LocalDate startTime;

    /**
     * End time
     */
    private LocalDate endTime;

    /**
     * price
     */
    private BigDecimal price;

    /**
     * Tickets left
     */
    private Integer ticketsLeft;

    private String currency;
    
}
