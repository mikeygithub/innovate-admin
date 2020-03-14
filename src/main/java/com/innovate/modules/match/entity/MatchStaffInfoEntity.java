package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:参与者
 **/
@Data
@TableName("innovate_match_staff_info")
public class MatchStaffInfoEntity implements Serializable {
    @TableId
    private Long staffId;
    private Long matchId;
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
