package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
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
@Accessors(fluent = true)
@TableName("SETTING")
public class SystemSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique Sequence
     */
    @TableId(value = "ENTITY_ID", type = IdType.AUTO)
    private Integer entityId;

    /**
     * userId
     */
    @TableField("USER_ID")
    private Integer userId;

    /**
     * language
     */
    @TableField("LANGUAGE")
    private String language;

    /**
     * private and Safety
     */
    @TableField("PRIVATE_ACCOUNT")
    private String privateAccount;

    @TableField("SEARCH_ALLOW")
    private String searchAllow;
    
    @TableField("ONLINE_SHOW")
    private String onlineShow;

    /**
     * feedback and help
     */
    @TableField("FEEDBACK_HELP")
    private String feedbackHelp;

    /**
     * Creation timestamp
     */
    @TableField(value = "CREATION_DATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime creationDateTime;

    /**
     * Last Update timestamp
     */
    @TableField(value = "LAST_UPDATE_DATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdateDateTime;

}
