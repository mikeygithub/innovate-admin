package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:在驻企业信息
 **/
@Data
@TableName("innovate_project_info")
public class ProjectInfoEntity implements Serializable {
    @TableId
    private Long projectId;
    private Long projectUserId;
    private Long stationId;
    private Long groupId;
    private String projectName;
    private Timestamp projectSetDate;
    private Timestamp projectInDate;
    private Long projectRegStatus;
    private Timestamp projectRegDate;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double projectRegMoney;
    private String projectContent;
    private Long projectIndustry;
    private Long projectType;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double projectInitMoney;
    private Long projectStudyNum;
    private Long projectProperty;
    private Long projectBase;
    private Long perNumFresh;
    private Long perNumPast;
    private Long perNumDriveEmNum;
    private Long perNumDrivePtNum;
    private Long perNumAbsorbEm;
    private Long perNumAbsorbPt;
    private Long projectStatus;
    private Long projectAuditApplyStatus;
    private Long projectBaseApplyStatus;
    private Long projectMatchApplyStatus;
    private Long projectFinishApplyStatus;
    private Long isUpdate;
    private Long applyUpdate;
    private Long auditNoPass;
    private Long baseNoPass;
    private Long matchNoPass;
    private Long finishNoPass;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double projectScoreAvg;
    private Long isDel;

}
