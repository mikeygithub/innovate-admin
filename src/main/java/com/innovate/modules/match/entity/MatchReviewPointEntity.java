package com.innovate.modules.match.entity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Program: innovate-admin
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-28 18:58
 * @Describe：
 **/
@Data
@TableName("innovate_match_review_point")
public class MatchReviewPointEntity implements Serializable{
    @TableId
    private Long reviewPointId;
    private String reviewPoint;
    private String reviewContent;
    private Long reviewScores;
    private Long reviewType;
    private Long isDel;
}
