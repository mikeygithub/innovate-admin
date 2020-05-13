package com.innovate.modules.finish.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:项目指导老师
 **/
@Data
@TableName("innovate_finish_teacher")
public class FinishTeacherEntity implements Serializable {
    @TableId
    private Long finishTeacherId;
    private Long finishId;
    private Long userId;
    private Date createTime;
    private Long isDel;
}
