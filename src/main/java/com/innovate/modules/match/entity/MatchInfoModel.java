package com.innovate.modules.match.entity;

import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author:tz
 * @create:2018-11-08
 **/
@Data
public class MatchInfoModel implements Serializable {
    private MatchInfoEntity matchInfoEntity;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<MatchTeacherEntity> matchTeacherEntities;
    private List<MatchAttachEntity> matchAttachEntities;
    private List<MatchStaffInfoEntity> matchStaffInfoEntities;
    private List<MatchAwardEntity> matchAwardEntities;
    private List<MatchReviewEntity> matchReviewEntities;
}
