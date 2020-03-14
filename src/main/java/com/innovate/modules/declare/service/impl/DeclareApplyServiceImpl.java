package com.innovate.modules.declare.service.impl;

import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareApplyService;
import com.innovate.modules.declare.service.DeclareInfoService;
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
public class DeclareApplyServiceImpl implements DeclareApplyService {

    @Autowired
    private DeclareInfoService declareInfoService;

    @Override
    public void apply(Map<String, Object> params) {
        Long declareId = Long.parseLong(params.get("declareId").toString());
        Long roleId = Long.parseLong(params.get("roleId").toString());
        Long applyStatus = null;
        String apply = params.get("apply").toString();
        DeclareInfoEntity declareInfoEntity = declareInfoService.selectById(declareId);

        switch (apply) {
            case "project_audit_apply_status": {
//                项目中的流程值
                applyStatus = declareInfoEntity.getProjectAuditApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.auditListName[applyStatus.intValue()]) {
                    declareInfoEntity.setProjectAuditApplyStatus(++applyStatus);
                }
                break;
            }
        }
        declareInfoService.updateById(declareInfoEntity);
    }
}
