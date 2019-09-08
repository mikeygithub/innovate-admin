package com.innovate.modules.innovate.service.impl;

import com.google.gson.Gson;
import com.innovate.common.utils.R;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.innovate.entity.ProjectInfoEntity;
import com.innovate.modules.innovate.service.ProjectApplyService;
import com.innovate.modules.innovate.service.ProjectInfoService;
import com.innovate.modules.sys.entity.SysRoleEntity;
import com.innovate.modules.sys.service.SysRoleService;
import com.innovate.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/24
 **/
@Service
public class ProjectApplyServiceImpl implements ProjectApplyService {

    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public void apply(Map<String, Object> params) {
        System.out.println(new Gson().toJson(params));
        Long projectId = Long.parseLong(params.get("projectId").toString());
        Long roleId = Long.parseLong(params.get("roleId").toString());
        Long applyStatus = null;
        String apply = params.get("apply").toString();
        ProjectInfoEntity projectInfoEntity = projectInfoService.selectById(projectId);

        switch (apply) {
            case "project_audit_apply_status": {
//                项目中的流程值
                applyStatus = projectInfoEntity.getProjectAuditApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.auditListName[applyStatus.intValue()]) {
                    projectInfoEntity.setProjectAuditApplyStatus(++applyStatus);
                }
                break;
            }
            case "project_base_apply_status": {
//                项目中的流程值
                applyStatus = projectInfoEntity.getProjectBaseApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.baseListName[applyStatus.intValue()]) {
                    projectInfoEntity.setProjectBaseApplyStatus(++applyStatus);
                }
                break;
            }
            case "project_match_apply_status": {
//                项目中的流程值
                applyStatus = projectInfoEntity.getProjectMatchApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.matchListName[applyStatus.intValue()]) {
                    projectInfoEntity.setProjectMatchApplyStatus(++applyStatus);
                }
                break;
            }
            case "project_finish_apply_status": {
//                项目中的流程值
                applyStatus = projectInfoEntity.getProjectFinishApplyStatus();
//                判断当前项目审批流程保存的值是否等于角色id，相等便通过
                if (roleId == ConfigApi.finishListName[applyStatus.intValue()]) {
                    projectInfoEntity.setProjectFinishApplyStatus(++applyStatus);
                }
                break;
            }
        }
        projectInfoService.updateById(projectInfoEntity);
    }
}
