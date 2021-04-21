package com.hsb.mvpmsweb.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("USER_INFO")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique Sequence
     */
    @TableId(value = "ENTITY_ID", type = IdType.AUTO)
    private Integer entityId;

    /**
     * 0 - Normal User,1 - Merchant
     */
    @TableField("USER_ROLE")
    private Integer userRole;

    /**
     * mobile phone number(Login Id)
     */
    @TableField("MOBILE_PHONE")
    private String mobilePhone;

    /**
     * Area Code
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * User Name
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * User Nick Name
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * User Email
     */
    @TableField("EMAIL")
    private String email;

    /**
     * User Introduction
     */
    @TableField("INTRODUCTION")
    private String introduction;

    /**
     * 0 - Male,1 - Female
     */
    @TableField("GENDER")
    private Integer gender;

    /**
     * Date of Birth
     */
    @TableField("DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    /**
     * Location
     */
    @TableField("LOCATION")
    private String location;

    /**
     * Count of user followers
     */
    @TableField("FOLLOWER_COUNT")
    private Integer followerCount;

    /**
     * Count of user fans
     */
    @TableField("FAN_COUNT")
    private Integer fanCount;

    /**
     * url of user image
     */
    @TableField("USER_IMG_PATH")
    private String userImgPath;
    
    /**
     * url of cover image
     */
    @TableField("COVER_IMG_PATH")
    private String coverImgPath;

    /**
     * Login Id(Unique Sequence)
     */
    @TableField("LOGIN_ID")
    private String loginId;

    /**
     * Login Passowrd
     */
    @TableField("PASSWORD")
    private String password;

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

    /**
     * Update Version
     */
    @TableField("VERSION")
    @Version
    private Integer version;
    
    /**
     * "0"refers no cancellation, "1" refers logged off.
     */
    @TableField("LOGED_OFF")
    private Boolean logedOff;
    
    @TableField("SHOP_ID")
    private String shopId;
    
}
