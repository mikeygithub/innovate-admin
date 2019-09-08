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
 * @description:费用
 **/
@Data
@TableName("innovate_base_cost")
public class BaseCostEntity implements Serializable {
    @TableId
    private Long costId;
    private Long baseId;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double personCost;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double areaCost;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double manageCost;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double anotherCost;
    private Date costStartTime;
    private Date costEndTime;

}
