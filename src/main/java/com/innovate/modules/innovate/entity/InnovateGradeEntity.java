package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Data
@TableName("innovate_sys_grade")
public class InnovateGradeEntity implements Serializable {

    @TableId
    private Long gradeId;
    private String gradeName;
    private String groupContent;
}
