package com.innovate.modules.finish.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Program: innovate-admin
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-05 22:54
 * @Describe： 签署意见
 **/
@Data
@TableName("innovate_finish_signing_opinion")
public class FinishSigningOpinionEntity implements Serializable {
    @TableId
    private Long signingOpinionsId;
    private Long finishId;
    private Long userId;
    private String signingOpinion;
    private Date signingOpinionTime;
    private Integer isDel;
}
