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
 * @description:收入
 **/
@Data
@TableName("innovate_base_money")
public class BaseMoneyEntity implements Serializable {
    @TableId
    private Long moneyId;
    private Long baseId;
    private Long moneyType;
    private String moneySource;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double moneyNum;
    private Date moneyTime;
}
