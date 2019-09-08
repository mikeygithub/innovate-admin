package com.innovate.modules.innovate.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/26
 **/
@Data
public class ProjectInfoModel implements Serializable {
    private ProjectInfoEntity projectInfoEntity;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<ProjectTeacherEntity> projectTeacherEntities;
    private List<ProjectAttachEntity> projectAttachEntities;
    private List<ProjectSubMoneyEntity> projectSubMoneyEntities;
    private List<ProjectStaffInfoEntity> projectStaffInfoEntities;
    private List<ProjectLegalInfoEntity> projectLegalInfoEntities;
    private List<ProjectAwardEntity> projectAwardEntities;
    private List<ProjectReviewEntity> projectReviewEntities;
}
