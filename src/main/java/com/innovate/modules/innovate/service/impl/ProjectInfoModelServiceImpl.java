package com.innovate.modules.innovate.service.impl;

import com.google.gson.Gson;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.*;
import com.innovate.modules.innovate.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/4
 **/
@Service
public class ProjectInfoModelServiceImpl implements ProjectInfoModelService {
    @Autowired
    private ProjectAttachService projectAttachService;
    @Autowired
    private ProjectAwardService projectAwardService;
    @Autowired
    private UserPerInfoService userPerInfoService;
    @Autowired
    private ProjectStaffInfoService projectStaffInfoService;
    @Autowired
    private ProjectLegalInfoService projectLegalInfoService;
    @Autowired
    private ProjectSubMoneyService projectSubMoneyService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectTeacherService projectTeacherService;
    @Autowired
    private ProjectReviewService projectReviewService;

    private List<ProjectTeacherEntity> projectTeacherEntities;
    private List<UserPersonInfoEntity> userPersonInfoEntities;
    private List<ProjectAttachEntity> projectAttachEntities;
    private List<ProjectSubMoneyEntity> projectSubMoneyEntities;
    private List<ProjectStaffInfoEntity> projectStaffInfoEntities;
    private List<ProjectLegalInfoEntity> projectLegalInfoEntities;
    private List<ProjectAwardEntity> projectAwardEntities;
    private List<ProjectReviewEntity> projectReviewEntities;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Integer totalPage  = projectInfoService.queryCountPage(params);
        Integer currPage  = 1;
        Integer pageSize  = 10;
        try {
            if (params.get("currPage")!=null&&params.get("pageSize")!=null) {

                currPage = Integer.parseInt(params.get("currPage").toString());
                pageSize = Integer.parseInt(params.get("pageSize").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer startPage = 0 + pageSize * (currPage - 1);
        Integer endPage = pageSize;

        params.put("startPage", startPage);
        params.put("endPage", endPage);
//        临时接收项目信息
        List<ProjectInfoEntity> tempLists = null;
//        项目所有信息的临时实体
        ProjectInfoModel tempProjectInfoModel = null;
//        包含项目所有信息的主要实体
        List<ProjectInfoModel> projectInfoModels = new ArrayList<>();

        tempLists = projectInfoService.queryPage(params);

        Map<String, Object> tempParams = new HashMap<>();

        for (ProjectInfoEntity projectInfoEntity : tempLists) {
            tempParams.put("projectId", projectInfoEntity.getProjectId());
            tempProjectInfoModel = this.query(tempParams);
            projectInfoModels.add(tempProjectInfoModel);
        }
        return new PageUtils(projectInfoModels, totalPage, pageSize, currPage);
    }

    @Override
    public List<ProjectInfoModel> queryAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public ProjectInfoModel query(Map<String, Object> params) {

        Long projectId = Long.parseLong(params.get("projectId").toString());
        ProjectInfoModel tempProjectInfoModel = new ProjectInfoModel();

//        根据项目ID查询出对应的项目信息
        ProjectInfoEntity projectInfoEntity = projectInfoService.selectById(projectId);
        tempProjectInfoModel.setProjectInfoEntity(projectInfoEntity);

//        获取项目相关的所有表的数据信息
        userPersonInfoEntities = userPerInfoService.queryAllPersonInfo(params);
        projectTeacherEntities = projectTeacherService.queryAll(params);
        projectAttachEntities = projectAttachService.queryAll(params);
        projectSubMoneyEntities = projectSubMoneyService.queryAll(params);
        projectStaffInfoEntities = projectStaffInfoService.queryAll(params);
        projectLegalInfoEntities = projectLegalInfoService.queryAll(params);
        projectAwardEntities = projectAwardService.queryAll(params);
        projectReviewEntities = projectReviewService.queryAll(params);

//        将项目相关的信息放入tempInnovateProjectInfoModel中
        tempProjectInfoModel.setUserPersonInfoEntities(userPersonInfoEntities);
        tempProjectInfoModel.setProjectTeacherEntities(projectTeacherEntities);
        tempProjectInfoModel.setProjectAttachEntities(projectAttachEntities);
        tempProjectInfoModel.setProjectSubMoneyEntities(projectSubMoneyEntities);
        tempProjectInfoModel.setProjectStaffInfoEntities(projectStaffInfoEntities);
        tempProjectInfoModel.setProjectLegalInfoEntities(projectLegalInfoEntities);
        tempProjectInfoModel.setProjectAwardEntities(projectAwardEntities);
        tempProjectInfoModel.setProjectReviewEntities(projectReviewEntities);

        return tempProjectInfoModel;
    }

    @Override
    public void saveEntity(ProjectInfoModel projectInfoModel) {
        projectInfoService.insert(projectInfoModel.getProjectInfoEntity());
        this.saveOrupdateProps(projectInfoModel);
    }

    @Override
    public void updateEntity(ProjectInfoModel projectInfoModel) {
        projectInfoService.updateById(projectInfoModel.getProjectInfoEntity());
        this.saveOrupdateProps(projectInfoModel);
    }

    @Override
    public void saveOrupdateProps(ProjectInfoModel projectInfoModel) {

        Long projectId = projectInfoModel.getProjectInfoEntity().getProjectId();

        for (ProjectTeacherEntity projectTeacherEntity : projectInfoModel.getProjectTeacherEntities()) {
            projectTeacherEntity.setProjectId(projectId);
            projectTeacherService.insertOrUpdate(projectTeacherEntity);
        }
        for (ProjectAttachEntity projectAttachEntity : projectInfoModel.getProjectAttachEntities()) {
            projectAttachEntity.setProjectId(projectId);
            projectAttachService.insertOrUpdate(projectAttachEntity);
        }
        for (ProjectSubMoneyEntity projectSubMoneyEntity : projectInfoModel.getProjectSubMoneyEntities()) {
            projectSubMoneyEntity.setProjectId(projectId);
            projectSubMoneyService.insertOrUpdate(projectSubMoneyEntity);
        }
        for (ProjectStaffInfoEntity projectStaffInfoEntity : projectInfoModel.getProjectStaffInfoEntities()) {
            projectStaffInfoEntity.setProjectId(projectId);
            projectStaffInfoService.insertOrUpdate(projectStaffInfoEntity);
        }
        for (ProjectLegalInfoEntity projectLegalInfoEntity : projectInfoModel.getProjectLegalInfoEntities()) {
            projectLegalInfoEntity.setProjectId(projectId);
            projectLegalInfoService.insertOrUpdate(projectLegalInfoEntity);
        }
        for (ProjectAwardEntity projectAwardEntity : projectInfoModel.getProjectAwardEntities()) {
            projectAwardEntity.setProjectId(projectId);
            projectAwardService.insertOrUpdate(projectAwardEntity);
        }
    }

    @Override
    public void deleteEntity(Map<String, Object> params) {
        this.deleteProps(params);
        projectInfoService.remove(params);
    }

    @Override
    public void deleteProps(Map<String, Object> params) {
        projectTeacherService.remove(params);
        projectAttachService.remove(params);
        projectSubMoneyService.remove(params);
        projectStaffInfoService.remove(params);
        projectLegalInfoService.remove(params);
        projectAwardService.remove(params);
    }
}
