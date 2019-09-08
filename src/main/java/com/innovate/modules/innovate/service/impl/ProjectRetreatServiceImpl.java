package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.innovate.modules.innovate.dao.ProjectRetreatDao;
import com.innovate.modules.innovate.entity.ProjectInfoEntity;
import com.innovate.modules.innovate.entity.ProjectRetreatEntity;
import com.innovate.modules.innovate.service.ProjectInfoService;
import com.innovate.modules.innovate.service.ProjectRetreatService;
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
public class ProjectRetreatServiceImpl extends ServiceImpl<ProjectRetreatDao, ProjectRetreatEntity> implements ProjectRetreatService {

    @Autowired
    private ProjectRetreatService projectRetreatService;
    @Autowired
    private ProjectInfoService innovateProjectInfoService;

    @Override
    public void updateRetreat(Map<String, Object> params) {
        Long projectId = Long.parseLong(params.get("projectId").toString());
        String apply = params.get("apply").toString();
        Long userId = Long.parseLong(params.get("userId").toString());
        String option = params.get("option").toString();
        //项目中的流程值
        Long applyStatus = Long.parseLong(params.get("applyStatus").toString());
        ProjectRetreatEntity projectRetreatEntity = new ProjectRetreatEntity();
        projectRetreatEntity.setProjectId(projectId);
        projectRetreatEntity.setApply(apply);
        projectRetreatEntity.setRetreatOption(option);
        projectRetreatEntity.setUserId(userId);
        projectRetreatEntity.setApplyStatus(applyStatus);
        projectRetreatService.insert(projectRetreatEntity);

        ProjectInfoEntity projectInfo = innovateProjectInfoService.selectById(projectId);

        switch (apply) {
            case "project_audit_apply_status":
                projectInfo.setAuditNoPass(1L);
                break;
            case "project_base_apply_status":
                projectInfo.setBaseNoPass(1L);
                break;
            case "project_match_apply_status":
                projectInfo.setMatchNoPass(1L);
                break;
            case "project_finish_apply_status":
                projectInfo.setFinishNoPass(1L);
                break;
        }
        innovateProjectInfoService.updateAllColumnById(projectInfo);

    }

    @Override
    public List<ProjectRetreatEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

}
