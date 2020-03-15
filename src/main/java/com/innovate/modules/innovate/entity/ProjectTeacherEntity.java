package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 **/
@Data
@TableName("innovate_project_teacher")
public class ProjectTeacherEntity {
    @TableId
    private Long projectTeacherId;
    private Long projectId;
    private Long userId;
    private Long isDel;
}
