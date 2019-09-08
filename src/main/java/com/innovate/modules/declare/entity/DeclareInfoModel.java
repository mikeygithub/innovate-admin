package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
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
    private DeclareInfoEntity declareInfoEntity;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<DeclareTeacherEntity> declareTeacherEntities;
    private List<DeclareAttachEntity> declareAttachEntities;
    private List<DeclareStaffInfoEntity> declareStaffInfoEntities;
    private List<DeclareReviewEntity> declareReviewEntities;
    private List<DeclareAwardEntity> declareAwardEntities;
}
