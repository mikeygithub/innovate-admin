package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:参赛项目评审
 **/
@Data
@TableName("innovate_match_review")
public class MatchReviewEntity implements Serializable {
    @TableId
    private Long reviewId;
    private String apply;
    private Long matchId;
    private Long userId;
    private Long refereeNo;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double score;
    private String opinion;
    private Long isDel;
}
