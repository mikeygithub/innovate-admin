package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:参赛信息
 **/
@Data
@TableName("innovate_project_match_info")
public class ProjectMatchInfoEntity implements Serializable {
    @TableId
    private Long matchId;
    private String matchName;
    private String matchType;
    private String matchDescribe;
    private String matchInnovate;
    private String matchReview;
    private Long projectId;
}
