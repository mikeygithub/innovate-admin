package com.innovate.modules.finish.service.impl;

import com.innovate.modules.finish.entity.FinishInfoEntity;
import com.innovate.modules.finish.service.FinishApplyService;
import com.innovate.modules.finish.service.FinishInfoService;
import com.innovate.modules.innovate.config.ConfigApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/24
 **/
@Service
public class FinishApplyServiceImpl implements FinishApplyService {

    @Autowired
    private FinishInfoService finishInfoService;

    @Override
    public void apply(Map<String, Object> params) {
        Long finishId = Long.parseLong(params.get("finishId").toString());
        Long roleId = Long.parseLong(params.get("roleId").toString());
        Long applyStatus = null;
        String apply = params.get("apply").toString();
        FinishInfoEntity finishInfoEntity = finishInfoService.selectById(finishId);

        switch (apply) {
            case "project_finish_apply_status": {
//                项目中的流程值
                applyStatus = finishInfoEntity.getProjectFinishApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.finishListName[applyStatus.intValue()]) {
                    finishInfoEntity.setProjectFinishApplyStatus(++applyStatus);
                }
                break;
            }
        }
        finishInfoService.updateById(finishInfoEntity);
    }
}
