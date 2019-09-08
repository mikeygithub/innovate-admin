package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:项目附件
 **/
@Data
@TableName("innovate_project_attach")
public class ProjectAttachEntity implements Serializable {
    @TableId
    private Long attachId;
    private Long projectId;
    private String attachName;
    private String attachPath;
    private String attachTime;
    private Long isDel;
}
