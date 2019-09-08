package com.innovate.modules.finish.entity;

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
 * @description:大创结题项目信息
 **/
@Data
@TableName("innovate_finish_info")
public class FinishInfoEntity implements Serializable {
    @TableId
    private Long finishId;
    private Long projectUserId;
    private Long groupId;
    private String finishName;
    private Long finishType;
    private Long projectFinishApplyStatus;
    private Long isUpdate;
    private Long applyUpdate;
    private Long finishNoPass;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double finishScoreAvg;
    private Date finishTime;
    private Long isDel;

}
