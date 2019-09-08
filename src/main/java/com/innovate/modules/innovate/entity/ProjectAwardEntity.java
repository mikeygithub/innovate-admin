package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:荣誉成果
 **/
@Data
@TableName("innovate_project_award")
public class ProjectAwardEntity implements Serializable {
    @TableId
    private Long awardId;
    private Long projectId;
    private String awardName;
    private Long awardType;
    private Long awardRank;
    private Date awardTime;
    private Long isDel;
}
