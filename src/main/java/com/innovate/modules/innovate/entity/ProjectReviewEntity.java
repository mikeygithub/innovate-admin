package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:评审
 **/
@Data
@TableName("innovate_project_review")
public class ProjectReviewEntity implements Serializable {
    @TableId
    private Long reviewId;
    private String apply;
    private Long projectId;
    private Long userId;
    private Long refereeNo;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double score;
    private String opinion;
    private Long isDel;
}
