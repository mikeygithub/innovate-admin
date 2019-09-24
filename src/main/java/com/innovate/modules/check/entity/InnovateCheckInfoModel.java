package com.innovate.modules.check.entity;

import com.innovate.modules.declare.entity.DeclareInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName innovate-admin
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 9/20/19 10:40 AM
 * @Version 1.0
 * @Description:
 **/
@Data
public class InnovateCheckInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;
    //中期检查
    private InnovateCheckInfoEntity innovateCheckInfoEntity;
    //附件
    private List<InnovateCheckAttachEntity> innovateCheckAttachEntities;
    //对应的大创项目
    private DeclareInfoEntity declareInfoEntity;
    //回退记录
    private List<InnovateCheckRetreatEntity> innovateCheckRetreatEntities;
    //获奖信息
    private List<InnovateCheckAwardEntity> innovateCheckAwardEntities;
    //评委评分
    private List<InnovateCheckReviewEntity> innovateCheckReviewEntities;
}
