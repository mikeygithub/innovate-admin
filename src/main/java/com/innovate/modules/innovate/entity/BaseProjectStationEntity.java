package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:工位信息
 **/
@Data
@TableName("innovate_base_project_station")
public class BaseProjectStationEntity implements Serializable {
    @TableId
    private Long stationId;
    private Long baseId;
    private String stationNum;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double stationArea;
    private Long hasApply;
}
