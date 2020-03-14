package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/7
 **/
@Data
@TableName("innovate_sys_institute")
public class InnovateInstituteEntity implements Serializable {
    @TableId
    private Long instituteId;
    private String instituteName;
    private Long declareProjectNum;
    private Long declareNewProjectNum;
    private Long declareTrainProjectNum;
    private Long declarePracticeProjectNum;
    private Long declareStudentNum;
    private Long declareTeacherNum;

}
