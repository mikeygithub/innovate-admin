package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:项目指导老师
 **/
@Data
@TableName("innovate_declare_teacher")
public class DeclareTeacherEntity implements Serializable {
    @TableId
    private Long declareTeacherId;
    private Long declareId;
    private Long userId;
    private String position;
    private Long isDel;
}
