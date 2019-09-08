package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Data
@TableName("innovate_project_retreat")
public class ProjectRetreatEntity implements Serializable {
    @TableId
    private Long retreatId;
    private Long projectId;
    private Long userId;
    private String apply;
    private Long applyStatus;
    private Long projectProgress;
    private String retreatOption;
    private Long hasUpdate;
    private Long isDel;
}
