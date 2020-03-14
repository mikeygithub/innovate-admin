package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("innovate_sys_school")
public class InnovateSchoolEntity implements Serializable {

    @TableId
    private Long schoolId;
    private Long SchoolNum;
    private String schoolName;
}
