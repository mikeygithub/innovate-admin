package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.sys.entity.SysUserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:责任人
 **/
@Data
@TableName("innovate_user_person_info")
public class UserPersonInfoEntity implements Serializable {
    @TableId
    private Long userPerId;
    private Long userId;
    private Long gradeId;
    private String perCardNo;
    private Long perSex;
    private String perPost;
    private String perPoliticsType;
    private String perStuNo;
    private String perClassNo;
    private String perCormNo;
    private String perNative;
    private String perQq;
    private String perSchoolPost;
    private String perSchoolHonor;
    private String perSocialPractice;
    @TableField(exist = false)
    private SysUserEntity sysUserEntity;
    @TableField(exist = false)
    private Long isDel;
    private Integer perWorking;          //'工作方式',
    private Integer perAge;              //'年龄',
    private String perInterest;         //'兴趣爱好',
    private Integer perEmploy;           //'是否已经被企业录用  0:是，1：否',
}
