package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author:tz
 * @create:2018-11-08
 **/
@Data
@TableName(value = "innovate_declare_sighing_opinion")
public class DeclareInfoModel implements Serializable {
    private DeclareInfoEntity declareInfoEntity;//大创项目
    private List<UserPersonInfoEntity> userPersonInfoEntities;//负责人
    private List<DeclareTeacherEntity> declareTeacherEntities;//教师
    private List<UserTeacherInfoEntity> declareUserTeacherInfoEntities;//教师用户
    private List<DeclareAttachEntity> declareAttachEntities;//附件
    private List<DeclareStaffInfoEntity> declareStaffInfoEntities;//员工
    private List<DeclareReviewEntity> declareReviewEntities;//回退
    private List<DeclareAwardEntity> declareAwardEntities;//获奖
}
