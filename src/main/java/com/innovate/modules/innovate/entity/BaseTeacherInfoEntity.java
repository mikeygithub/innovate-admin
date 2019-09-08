package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:基地教师
 **/
@Data
@TableName("innovate_base_teacher_info")
public class BaseTeacherInfoEntity implements Serializable {
    @TableId
    private Long teacherId;
    private Long baseId;
    private String teacherName;
    private Long teacherSex;
    private String teacherUnit;
    private String teacherJob;
    private String teacherPhone;
    private Long teacherInstinct;
    private Long teacherWorkStatus;
}
