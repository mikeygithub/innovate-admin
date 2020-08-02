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
 * @description:大创申报项目信息
 **/
@Data
@TableName("innovate_declare_info")
public class DeclareInfoEntity implements Serializable {
    @TableId
    private Long declareId;
    private Long instituteId;
    private Long subjectId;
    private Long declareGrade;
    private Long declareYear;
    private Long projectUserId;
    private Long groupId;
    private String declareNum;
    private String declareName;
    private Long declareType;
    private Long projectAuditApplyStatus;
    private Long isUpdate;
    private Long applyUpdate;
    private Long auditNoPass;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double declareScoreAvg;
    private String declareRecommendLevel;
    private Long declareOrderNo;
    private String declareResult;
    private String declareDescribe;
    private Date declareTime;
    private Long finishStatus;
    private Long isDel;

}
