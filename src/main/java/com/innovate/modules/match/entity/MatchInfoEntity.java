package com.innovate.modules.match.entity;

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
 * @description:参赛项目信息
 **/
@Data
@TableName("innovate_match_info")
public class MatchInfoEntity implements Serializable {
    @TableId
    private Long matchId;
    private Long eventId;
    private Long projectUserId;
    private Long groupId;
    private String matchName;
    private String matchTeamName;
    private Long matchType;
    private Long matchGroupType;
    private String matchDescribe;
    private String matchBrightSpot;
    private String matchExpect;
    private String matchReviewResult;
    private Long projectMatchApplyStatus;
    private Long isUpdate;
    private Long applyUpdate;
    private Long matchNoPass;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double matchScoreAvg;
    private Long isDel;
    private Date matchTime;
}
