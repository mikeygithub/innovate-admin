package com.innovate.modules.declare.entity;

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
 * @description:参赛项目荣誉成果
 **/
@Data
@TableName("innovate_declare_award")
public class DeclareAwardEntity implements Serializable {
    @TableId
    private Long awardId;
    private Long declareId;
    private Long awardType;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double awardMoneyAll;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double awardMoneySchool;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double awardMoneyDistrict;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long isPrize;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long isDel;
}
