package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:员工
 **/
@Data
@TableName("innovate_project_staff_info")
public class ProjectStaffInfoEntity implements Serializable {
    @TableId
    private Long staffId;
    private Long projectId;
    private String staffName;
    private Long staffSex;
    private String staffClassNo;
    private String staffPost;
    private String staffTel;
    private Long staffType;
    private Long isDel;
}
