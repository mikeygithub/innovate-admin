package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.sys.entity.SysUserEntity;
import lombok.Data;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 **/
@Data
@TableName("innovate_user_teacher_info")
public class UserTeacherInfoEntity {
    @TableId
    private Long userTeacherId;
    private Long userId;
    private String teacherCardNo;
    private Long teacherSex;
    private String teacherPost;
    private Long teacherTitle;
    @TableField(exist = false)
    private SysUserEntity sysUserEntity;
    @TableField(exist = false)
    private Long isDel;
    private Integer teacherBackground;
    private Integer teacherDegree;
    private String teacherStudy;
    private String teacherScientific;
}
