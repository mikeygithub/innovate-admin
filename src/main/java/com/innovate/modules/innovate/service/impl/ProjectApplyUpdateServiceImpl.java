package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.ProjectApplyUpdateDao;
import com.innovate.modules.innovate.entity.ProjectApplyUpdateEntity;
import com.innovate.modules.innovate.entity.ProjectInfoEntity;
import com.innovate.modules.innovate.service.ProjectApplyUpdateService;
import com.innovate.modules.innovate.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@Service
public class ProjectApplyUpdateServiceImpl extends ServiceImpl<ProjectApplyUpdateDao, ProjectApplyUpdateEntity> implements ProjectApplyUpdateService {

    @Autowired
    private ProjectApplyUpdateService projectApplyUpdateService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long projectId = Long.parseLong(params.get("projectId").toString());
        Page<ProjectApplyUpdateEntity> page = this.selectPage(
                new Query<ProjectApplyUpdateEntity>(params).getPage(),
                new EntityWrapper<ProjectApplyUpdateEntity>()
                        .eq(projectId != null, "project_id", projectId)
        );
        return new PageUtils(page);
    }

    @Override
    public void update(ProjectApplyUpdateEntity projectApplyUpdateEntity) {
        Long projectId = projectApplyUpdateEntity.getProjectId();
        Long result = projectApplyUpdateEntity.getResult();
        ProjectInfoEntity projectInfoEntity = projectInfoService.selectById(projectId);
        if (1 == result) {//0不通过 1通过
            projectInfoEntity.setIsUpdate(1L);
            projectInfoEntity.setApplyUpdate(0L);
        }
        if (2 == result || 0 == result) {//0不通过 1通过
            projectInfoEntity.setApplyUpdate(0L);
        }
        if (null == result) {//0不通过 1通过
            projectInfoEntity.setApplyUpdate(1L);
        }
        projectInfoService.updateAllColumnById(projectInfoEntity);
        projectApplyUpdateService.updateAllColumnById(projectApplyUpdateEntity);
    }

    @Override
    public void applyUpdate(Map<String, Object> params) {
        System.out.println(new Gson().toJson(params));
        Long projectId = Long.parseLong(params.get("projectId").toString());
        String reason = params.get("reason").toString();
        ProjectInfoEntity projectInfoEntity = projectInfoService.selectById(projectId);
        projectInfoEntity.setApplyUpdate(1L);
        projectInfoService.updateAllColumnById(projectInfoEntity);
        ProjectApplyUpdateEntity projectApplyUpdateEntity = new ProjectApplyUpdateEntity();
        projectApplyUpdateEntity.setProjectId(projectId);
        projectApplyUpdateEntity.setReason(reason);
        projectApplyUpdateService.insert(projectApplyUpdateEntity);
    }

    @Override
    public List<ProjectApplyUpdateEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        List<ProjectApplyUpdateEntity> projectApplyUpdateEntityList = baseMapper.queryAll(params);
        for (ProjectApplyUpdateEntity projectApplyUpdateEntity : projectApplyUpdateEntityList) {
            baseMapper.remove(params);
        }
    }
}
