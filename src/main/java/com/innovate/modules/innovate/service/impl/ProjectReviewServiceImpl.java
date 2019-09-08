package com.innovate.modules.innovate.service.impl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.innovate.modules.innovate.dao.ProjectReviewDao;
import com.innovate.modules.innovate.entity.InnovateReviewGroupUserEntity;
import com.innovate.modules.innovate.entity.ProjectInfoEntity;
import com.innovate.modules.innovate.entity.ProjectReviewEntity;
import com.innovate.modules.innovate.entity.ProjectTeacherEntity;
import com.innovate.modules.innovate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:43
 * @Version 1.0
 */
@Service
public class ProjectReviewServiceImpl extends ServiceImpl<ProjectReviewDao, ProjectReviewEntity> implements ProjectReviewService {
    @Autowired
    private ProjectReviewService projectReviewService;
    @Autowired
    private ProjectApplyService projectApplyService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectTeacherService projectTeacherService;
    @Autowired
    private InnovateReviewGroupUserService innovateReviewGroupUserService;

    @Override
    public List<ProjectReviewEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

    @Override
    public Long queryCount(Map<String, Object> params){
        return baseMapper.queryCount(params);
    }

    @Override
    public Double queryScoreAvg(Map<String, Object> params) {
        return baseMapper.queryScoreAvg(params);
    }

    @Override
    public void reviewUser(Map<String,Object> params) {
        Long projectId = Long.parseLong(params.get("projectId").toString());
        Long groupId = Long.parseLong(params.get("groupId").toString());
        Long userId = Long.parseLong(params.get("userId").toString());
        String apply = params.get("apply").toString();
        String reApply = params.get("reApply").toString();
        ProjectInfoEntity projectInfoEntity = projectInfoService.selectById(projectId);
        projectInfoEntity.setGroupId(groupId);
        projectInfoService.updateById(projectInfoEntity);
        List<ProjectTeacherEntity> projectTeacherEntities = projectTeacherService.queryAll(params);
        List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities = innovateReviewGroupUserService.queryAllGroupUser(groupId);
        projectReviewService.remove(params);
        ProjectReviewEntity projectReviewEntity = null;
        for (int index = 0; index < innovateReviewGroupUserEntities.size(); index++) {
            for (int indexJ = 0; indexJ < projectTeacherEntities.size(); indexJ++) {
                if (innovateReviewGroupUserEntities.get(index).getUserId() != projectTeacherEntities.get(indexJ).getUserId()) {
                    projectReviewEntity = new ProjectReviewEntity();
                    projectReviewEntity.setApply(apply);
                    projectReviewEntity.setProjectId(projectId);
                    projectReviewEntity.setUserId(innovateReviewGroupUserEntities.get(index).getUserId());
                    projectReviewService.insert(projectReviewEntity);
                }
            }
        }
        if (reApply.equals("false")) {
            projectApplyService.apply(params);
        }
    }

    @Override
    public void score(ProjectReviewEntity projectReviewEntity) {
        Long projectId = projectReviewEntity.getProjectId();
        projectReviewService.updateById(projectReviewEntity);
        String apply = projectReviewEntity.getApply();
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("apply", apply);
        params.put("roleId", 6);
        Long count = projectReviewService.queryCount(params);
        if (count == 0L){
            projectApplyService.apply(params);
            //计算平均分
            Double scoreAvg = projectReviewService.queryScoreAvg(params);
            ProjectInfoEntity projectInfoEntity = projectInfoService.selectById(projectId);
            projectInfoEntity.setProjectScoreAvg(scoreAvg);
            projectInfoService.updateById(projectInfoEntity);
        }
    }

    @Override
    public ProjectReviewEntity queryScore(Map<String,Object> params) {
        return baseMapper.queryScore(params);
    }
}
