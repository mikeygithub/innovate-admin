package com.innovate.modules.finish.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 大创结题项目信息
 *
 * @Author Mikey
 * @Email 1625017540@qq.com
 * @Date 2019-09-18 22:20:42
 */
@Data
@TableName("innovate_finish_info")
public class FinishInfoEntity implements Serializable {
    @TableId
    private Long finishId;
    private Long projectUserId;
    private Long groupId;
    private String finishName;
    private Long finishGrade;
    private Long finishYear;
    private Long finishType;
    //项目描述
    private String finishDescribe;
    private Long projectFinishApplyStatus;
    private Long isUpdate;
    private Long applyUpdate;
    private Long finishNoPass;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double finishScoreAvg;
    private Date finishTime;
    private Long declareId;
    private Long isDel;

}
