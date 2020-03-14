package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:法人信息
 **/
@Data
@TableName("innovate_project_legal_info")
public class ProjectLegalInfoEntity implements Serializable {
    @TableId
    private Long legalId;
    private Long projectId;
    private Long gradeId;
    private String legalName;
    private String legalCardNo;
    private Long legalSex;
    private Long legalFace;
    private String legalNative;
    private String legalGrade;
    private String legalClassNo;
    private String legalPost;
    private String legalTel;
    private String legalEmail;
    private Long isDel;
}
