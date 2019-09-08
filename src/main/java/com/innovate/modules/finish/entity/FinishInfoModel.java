package com.innovate.modules.finish.entity;

import com.innovate.modules.finish.entity.*;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author:tz
 * @create:2018-11-08
 **/
@Data
public class FinishInfoModel implements Serializable {
    private FinishInfoEntity finishInfoEntity;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<FinishTeacherEntity> finishTeacherEntities;
    private List<FinishAttachEntity> finishAttachEntities;
    private List<FinishStaffInfoEntity> finishStaffInfoEntities;
    private List<FinishReviewEntity> finishReviewEntities;
}
