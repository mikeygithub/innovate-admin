package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.declare.dao.DeclareRetreatDao;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareRetreatEntity;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.declare.service.DeclareRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Service
public class DeclareRetreatServiceImpl extends ServiceImpl<DeclareRetreatDao, DeclareRetreatEntity> implements DeclareRetreatService {

    @Autowired
    private DeclareRetreatService declareRetreatService;
    @Autowired
    private DeclareInfoService innovateDeclareInfoService;

    @Override
    public void updateRetreat(Map<String, Object> params) {
        Long declareId = Long.parseLong(params.get("declareId").toString());
        String apply = params.get("apply").toString();
        Long userId = Long.parseLong(params.get("userId").toString());
        String option = params.get("option").toString();
        //项目中的流程值
        Long applyStatus = Long.parseLong(params.get("applyStatus").toString());
        DeclareRetreatEntity declareRetreatEntity = new DeclareRetreatEntity();
        declareRetreatEntity.setDeclareId(declareId);
        declareRetreatEntity.setApply(apply);
        declareRetreatEntity.setRetreatOption(option);
        declareRetreatEntity.setUserId(userId);
        declareRetreatEntity.setApplyStatus(applyStatus);
        declareRetreatService.insert(declareRetreatEntity);

        DeclareInfoEntity declareInfo = innovateDeclareInfoService.selectById(declareId);

        switch (apply) {
            case "project_audit_apply_status":
                declareInfo.setAuditNoPass(1L);
                break;
        }
        innovateDeclareInfoService.updateAllColumnById(declareInfo);

    }

    @Override
    public List<DeclareRetreatEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

}
