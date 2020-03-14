package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:补助/投资/贷款
 **/
@Data
@TableName("innovate_project_subsidies_money")
public class ProjectSubMoneyEntity implements Serializable {
    @TableId
    private Long subId;
    private Long projectId;
    private Long subType;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double subMoney;
    private Date subTime;
    private String subSource;
    private Long isDel;

}
