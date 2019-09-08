package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("innovate_sys_subject")
public class InnovateSubjectEntity {

    @TableId
    private Long subjectId;
    private Long subjectNum;
    private String subjectName;
}
