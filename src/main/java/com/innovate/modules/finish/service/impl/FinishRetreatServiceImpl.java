package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.entity.FinishInfoEntity;
import com.innovate.modules.finish.entity.FinishRetreatEntity;
import com.innovate.modules.finish.dao.FinishRetreatDao;
import com.innovate.modules.finish.service.FinishInfoService;
import com.innovate.modules.finish.service.FinishRetreatService;
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
public class FinishRetreatServiceImpl extends ServiceImpl<FinishRetreatDao, FinishRetreatEntity> implements FinishRetreatService {

    @Autowired
    private FinishRetreatService finishRetreatService;
    @Autowired
    private FinishInfoService innovateFinishInfoService;

    @Override
    public void updateRetreat(Map<String, Object> params) {
        Long finishId = Long.parseLong(params.get("finishId").toString());
        String apply = params.get("apply").toString();
        Long userId = Long.parseLong(params.get("userId").toString());
        String option = params.get("option").toString();
        //项目中的流程值
        Long applyStatus = Long.parseLong(params.get("applyStatus").toString());
        FinishRetreatEntity finishRetreatEntity = new FinishRetreatEntity();
        finishRetreatEntity.setFinishId(finishId);
        finishRetreatEntity.setApply(apply);
        finishRetreatEntity.setRetreatOption(option);
        finishRetreatEntity.setUserId(userId);
        finishRetreatEntity.setApplyStatus(applyStatus);
        finishRetreatService.insert(finishRetreatEntity);

        FinishInfoEntity finishInfo = innovateFinishInfoService.selectById(finishId);

        switch (apply) {
            case "project_finish_apply_status":
                finishInfo.setFinishNoPass(1L);
                break;
        }
        innovateFinishInfoService.updateAllColumnById(finishInfo);

    }

    @Override
    public List<FinishRetreatEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

}
