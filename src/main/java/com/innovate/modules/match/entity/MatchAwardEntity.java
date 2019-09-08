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
 * @description:参赛项目荣誉成果
 **/
@Data
@TableName("innovate_match_award")
public class MatchAwardEntity implements Serializable {
    @TableId
    private Long awardId;
    private Long matchId;
    private String awardName;
    private Long awardType;
    private Long awardRank;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double awardMoney;
    private String awardFileName;
    private String awardPath;
    private Date awardTime;
    private Long isPrize;
    private Long isDel;
}
