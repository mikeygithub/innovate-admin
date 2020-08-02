package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.innovate.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:大创申报项目评审
 **/
@Data
@TableName("innovate_declare_review")
public class DeclareReviewEntity implements Serializable {
    @TableId
    private Long reviewId;
    private String apply;
    private Long declareId;
    private Long userId;
    private Long refereeNo;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double score;
    private String opinion;
    private Long reviewYear;

    private Long isDel;


    @TableField(exist = false)
    private DeclareInfoEntity declareInfoEntity;
    @TableField(exist = false)
    private SysUserEntity sysUserEntity;
}
