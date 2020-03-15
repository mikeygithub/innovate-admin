package com.innovate.modules.check.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 中期检查获奖信息
 */
@Data
@TableName("innovate_check_award")
public class InnovateCheckAwardEntity implements Serializable {
    @TableId
    private Long awardId;
    private Long checkId;
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
