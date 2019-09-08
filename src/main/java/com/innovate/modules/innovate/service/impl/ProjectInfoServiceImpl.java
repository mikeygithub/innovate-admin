package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.innovate.common.ProjectCalculate;
import com.innovate.modules.innovate.dao.ProjectInfoDao;
import com.innovate.modules.innovate.entity.*;
import com.innovate.modules.innovate.service.*;
import com.innovate.modules.innovate.utils.BaseTeamAreaTotal;
import com.innovate.modules.innovate.utils.ProjectCalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:35
 * @Version 1.0
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoDao, ProjectInfoEntity> implements ProjectCalculate, ProjectInfoService {

    @Autowired
    private BaseInfoService baseInfoService;
    @Autowired
    private ProjectApplyService projectApplyService;
    @Autowired
    private BaseProjectStationService baseProjectStationService;
    @Autowired
    private ProjectRetreatService projectRetreatService;

    ProjectInfoServiceImpl() {
        ProjectCalculateUtil.addObject(this);
    }

    @Override
    public ProjectInfoEntity queryById(Long projectId) {
        return baseMapper.queryById(projectId);
    }

    @Override
    public Integer queryCountPage(Map<String, Object> params) {
        return baseMapper.queryCountPage(params);
    }

    @Override
    public List<ProjectInfoEntity> queryPage(Map<String, Object> params) {
        return baseMapper.queryPage(params);
    }

    @Override
    public List<ProjectInfoEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public List<ProjectInfoEntity> noPass(Map<String, Object> params) {
        return baseMapper.noPass(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

    @Override
    public void status(ProjectInfoModel projectInfoModel) {
        if (projectInfoModel.getProjectInfoEntity().getProjectStatus() > 1){
            //释放工位号（企业状态 2成功 or 3失败）
            Long stationId = projectInfoModel.getProjectInfoEntity().getStationId();
            BaseProjectStationEntity baseProjectStationEntity = baseProjectStationService.selectById(stationId);
            baseProjectStationEntity.setHasApply(0L);
            baseProjectStationService.insertOrUpdate(baseProjectStationEntity);
            //将工位号外键置为null
            projectInfoModel.getProjectInfoEntity().setStationId(null);
        }
        baseMapper.updateAllColumnById(projectInfoModel.getProjectInfoEntity());
        Long projectId = projectInfoModel.getProjectInfoEntity().getProjectId();
        Long status = projectInfoModel.getProjectInfoEntity().getProjectBaseApplyStatus();
        if (status < 6) {
            Map<String, Object> params = new HashMap<>();
            params.put("projectId", projectId);
            params.put("roleId", 1);
            params.put("apply", "project_base_apply_status");
            projectApplyService.apply(params);
        }
    }


    @Override
    public List<Long> queryStationIdList(Long projectStatus) {
        return baseMapper.queryStationIdList(projectStatus);
    }

    @Override
    public Long queryProjectNum(Long projectStatus, Long projectBase, Long projectRegStatus, Long baseId) {
        return baseMapper.queryProjectNum(projectStatus, projectBase, projectRegStatus, baseId);
    }

    @Override
    public Double queryInvest() {
        return baseMapper.queryInvest();
    }

    @Override
    public Long queryAbsorb(Long baseId) {
        return baseMapper.queryAbsorb(baseId);
    }

    @Override
    public Long queryDriveEmNum(Long baseId) {
        return baseMapper.queryDriveEmNum(baseId);
    }

    @Override
    public Long queryIprNum(Long projectProperty, Long baseId) {
        return baseMapper.queryIprNum(projectProperty, baseId);
    }

    @Override
    public Long queryBaseId(Long projectId) {
        return baseMapper.queryBaseId(projectId);
    }

    @Override
    public void station(ProjectInfoModel projectInfoModel) {
        Long projectId = projectInfoModel.getProjectInfoEntity().getProjectId();
        Long updateStationId = projectInfoModel.getProjectInfoEntity().getStationId();
        ProjectInfoEntity projectInfoEntity = baseMapper.selectById(projectId);
        Long stationId = projectInfoEntity.getStationId();
        baseMapper.updateById(projectInfoModel.getProjectInfoEntity());
        if (stationId == null) {
            baseProjectStationService.hasApply(updateStationId);
            Map<String, Object> params = new HashMap<>();
            params.put("projectId", projectId);
            params.put("roleId", 5);
            params.put("apply", "project_base_apply_status");
            projectApplyService.apply(params);
        } else {
            baseProjectStationService.hasApply(updateStationId);
            baseProjectStationService.delApply(stationId);
        }
    }

    @Override
    public void calculate(Long projectId, Long baseId) {
        if (null != baseId) {
            BaseInfoEntity baseInfoEntity = baseInfoService.selectById(baseId);

            //企业使用面积
            Double baseTeamArea = BaseTeamAreaTotal.calculate(baseId);
            //logger.error("tz---工位面积--->"+baseTeamArea);
            baseInfoEntity.setBaseTeamArea(baseTeamArea);

            //场地利用率
            Double userRate = baseTeamArea / (baseInfoEntity.getBaseArea());
            //logger.error("tz---场地利用率--->"+userRate);
            baseInfoEntity.setBaseAreaUseRate(userRate);

            /**
             *    统计项目总数
             *    参数：projectStatus-->项目状态（1在驻B0，2成功C0，3失败D0）
             *          projectBase-->是否有见习基地：1有，2否
             *          projectRegStatus-->是否有工商注册情况：1是，2否
             *          baseId-->所属基地编号
             */
            //就业见习基地数量
            Long baseNum = this.queryProjectNum(1L, 1L, null, baseId);
            if (null == baseNum) {
                baseNum = 0L;
            }
            baseInfoEntity.setBaseNowBaseNum(baseNum);

            //现有已注册企业数量
            Long companyNum = this.queryProjectNum(1L, null, 1L, baseId);
            if (null == companyNum) {
                companyNum = 0L;
            }
            //logger.error("tz--companyNum--->"+companyNum);
            baseInfoEntity.setBaseCompanyNum(companyNum);

            //现有项目组数量
            Long itemNum = this.queryProjectNum(1L, null, null, baseId);
            if (null == itemNum) {
                itemNum = 0L;
            }
            baseInfoEntity.setBaseItemNum(itemNum);

            //累计已注册企业数
            Long allCompany = this.queryProjectNum(null, null, 1L, baseId);
            if (null == allCompany) {
                allCompany = 0L;
            }
            baseInfoEntity.setBaseAllCompanyNum(allCompany);

            //累计项目组数量
            Long allItemNum = this.queryProjectNum(null, null, null, baseId);
            if (null == allItemNum) {
                allItemNum = 0L;
            }
            baseInfoEntity.setBaseAllItemNum(allItemNum);
            //大学生创业数量
            baseInfoEntity.setBaseStuNum(allItemNum);
            //累计初创企业数量
            baseInfoEntity.setBaseAllInitCompany(allItemNum);

            //累计总投资额
            //Double allInvest = innovateProjectInfoService.

            //就业见习人数
            Long absorb = this.queryAbsorb(baseId);
            if (null == absorb) {
                absorb = 0L;
            }
            baseInfoEntity.setBaseYearProbate(absorb);

            //带动就业人数
            Long workerNum = this.queryDriveEmNum(baseId);
            if (null == workerNum) {
                workerNum = 0L;
            }
            baseInfoEntity.setBaseWorkNum(workerNum);
            //直接就业人数
            baseInfoEntity.setBaseDirectNum(workerNum);

            //孵化到期出园率
            Long C0 = this.queryProjectNum(2L, null, null, baseId);
            Long D0 = this.queryProjectNum(3L, null, null, baseId);
            if (C0 == null) {
                C0 = 0L;
            }
            if (D0 == null) {
                D0 = 0L;
            }
            if (0.0 == allItemNum.doubleValue()) {
                baseInfoEntity.setBaseOutRate(0.0);
            } else {
                Double endOutRate = (C0.doubleValue() + D0.doubleValue()) / (allItemNum.doubleValue());
                DecimalFormat df = new DecimalFormat("#0.0000"); //保留小数点后四位
                baseInfoEntity.setBaseOutRate(Double.valueOf(df.format(endOutRate)));
            }
            //孵化到期出园数量
            Long outNum = C0 + D0;
            baseInfoEntity.setBaseOutNum(outNum);

            //当年实体数量
            Long B0 = this.queryProjectNum(1L, null, null, baseId);
            if (B0 == null) {
                B0 = 0L;
            }
            baseInfoEntity.setBaseEntityNum(B0);

            //拥有的有效知识产权数量
            Long iprNum = this.queryIprNum(1L, baseId);
            if (null == iprNum) {
                iprNum = 0L;
            }
            baseInfoEntity.setBaseIprNum(iprNum);

            baseInfoService.updateAllColumnById(baseInfoEntity);
        }
    }
}
