package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:参与者
 **/
@Data
@TableName("innovate_declare_staff_info")
public class DeclareStaffInfoEntity implements Serializable {
    @TableId
    private Long staffId;
    private Long declareId;
    private Long instituteId;
    private Long gradeId;
    private String staffName;
    private Long staffSex;
    private String staffStuNo;
    private String staffClassNo;
    private String staffCormNo;
    private String staffTel;
    private String staffQq;
    private String staffEmail;
    private Long isDel;

}
