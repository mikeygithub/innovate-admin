package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@Data
@TableName("innovate_project_apply_update")
public class ProjectApplyUpdateEntity implements Serializable {
    @TableId
    private Long applyId;
    private Long projectId;
    private String reason;
    private Long result;
    private String applyOption;
    private Long isDel;
}
