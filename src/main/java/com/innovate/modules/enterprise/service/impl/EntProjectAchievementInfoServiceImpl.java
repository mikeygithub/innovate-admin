package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.annotation.LimitPage;
import com.innovate.common.enums.DefValueEnum;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntProjectAchievementInfoDao;
import com.innovate.modules.enterprise.entity.*;
import com.innovate.modules.enterprise.service.*;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.innovate.common.utils.ShiroUtils.getUserId;


@Service("entProjectAchievementInfoService")
public class EntProjectAchievementInfoServiceImpl extends ServiceImpl<EntProjectAchievementInfoDao, EntProjectAchievementInfoEntity> implements EntProjectAchievementInfoService {

    @Autowired
    private EntProjectInfoService entProjectInfoService;

    @Autowired
    private UserTeacherInfoService userTeacherInfoService;

    @Autowired
    private UserPerInfoService userPerInfoService;

    @Autowired
    private EntEnterpriseInfoService entEnterpriseInfoService;

    @Autowired
    private EntProjectAchievementInfoService entProjectAchievementInfoService;

    @Autowired
    private EntProjectAttachService entProjectAttachService;

    @Autowired
    private EntProjectCooperationInfoService entProjectCooperationInfoService;

    @Autowired
    private SysUserService sysUserService;

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"0", "userPerId"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public EntProjectAchievementInfoEntity queryProjectAchievementInfo(Map<String, Object> params) {
        Long proAchievementId = (Long)params.get("proAchievementId");
        EntProjectAchievementInfoEntity entProjectAchievementInfoEntity = entProjectAchievementInfoService.selectById(proAchievementId);
        //查询项目信息
        Long proInfoId = entProjectAchievementInfoEntity.getProInfoId();
        EntProjectInfoEntity entProjectInfoEntity = entProjectInfoService.selectById(proInfoId);
        //查询项目成果附件
        EntityWrapper wrapper = new EntityWrapper<EntProjectAchievementInfoEntity>();
        wrapper.eq("pro_info_id", proInfoId);
        wrapper.eq("attach_type", "2");
        List<EntProjectAttachEntity> entProjectAttachEntitys = entProjectAttachService.selectList(wrapper);
        entProjectInfoEntity.setEntProjectAttachEntities(entProjectAttachEntitys);
        entProjectAchievementInfoEntity.setEntProjectInfo(entProjectInfoEntity);
        //查询合作信息
        EntProjectCooperationInfoEntity entProjectCooperationInfoEntity = entProjectCooperationInfoService.selectOne(new EntityWrapper<EntProjectCooperationInfoEntity>().eq("pro_info_id", proInfoId));

        return entProjectAchievementInfoEntity;
    }

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inApply", defValue = "1", defValueEnum = DefValueEnum.STRING)
    @Override
    public R updateProjectExamine(Map<String, Object> params) {
        if(params.get("inApply") != null && !"0".equals(params.get("inApply"))){
            baseMapper.updateProjectExamine(params);
        } else if((params.get("inApply") != null && "0".equals(params.get("inApply")))){
            String id = String.valueOf(params.get("proAchievementId"));
            baseMapper.deleteById(Long.valueOf(id));
        }
        return R.ok();
    }

    @LimitPage(targetType = java.util.Map.class, name = "项目合作分页", index = 0, pageSize = 10,  currPage = 1)
    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inType", defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"0"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Integer pageSize = (Integer) params.get("pageSize");
        Integer currPage = (Integer) params.get("currPage");
        String inApply = (String) params.get("inApply");
        String inType = (String) params.get("inType");
        Integer count = 0;

        //查询项目成果信息
        EntityWrapper wrapper = new EntityWrapper<EntProjectAchievementInfoEntity>();
        if("0".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "0");
        }else {
            wrapper.eq("in_apply", "1");
        }
        List<EntProjectAchievementInfoEntity> entProjectAchievementInfoEntities = entProjectAchievementInfoService.selectList(wrapper);

        //查询项目负责人-学生
        if(inType.equals("userPerId")){
            for (int i = 0; i < entProjectAchievementInfoEntities.size(); i++) {
                Long proInfoId = entProjectAchievementInfoEntities.get(i).getProInfoId();
                EntProjectInfoEntity entProjectInfoEntity = entProjectInfoService.selectById(proInfoId);
                if(entProjectInfoEntity.getUserPerId() != null){
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(entProjectInfoEntity.getUserPerId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                    userPersonInfoEntity.setSysUserEntity(sysUserEntity);
                    entProjectInfoEntity.setUserPersonInfo(userPersonInfoEntity);
                    entProjectAchievementInfoEntities.get(i).setEntProjectInfo(entProjectInfoEntity);
                }else {
                    entProjectAchievementInfoEntities.remove(i);
                    --i;
                }
            }
        }
        //查询项目负责人-教师
        if(inType.equals("userTeacherId")){
            for (int i = 0; i < entProjectAchievementInfoEntities.size(); i++) {
                Long proInfoId = entProjectAchievementInfoEntities.get(i).getProInfoId();
                EntProjectInfoEntity entProjectInfoEntity = entProjectInfoService.selectById(proInfoId);
                if(entProjectInfoEntity.getUserTeacherId() != null){
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(entProjectInfoEntity.getUserTeacherId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                    userTeacherInfoEntity.setSysUserEntity(sysUserEntity);
                    entProjectInfoEntity.setUserTeacherInfo(userTeacherInfoEntity);
                    entProjectAchievementInfoEntities.get(i).setEntProjectInfo(entProjectInfoEntity);
                }else {
                    entProjectAchievementInfoEntities.remove(i);
                    --i;
                }
            }
        }
        //查询项目负责人-企业
        if(inType.equals("entInfoId")){
            for (int i = 0; i < entProjectAchievementInfoEntities.size(); i++) {
                Long proInfoId = entProjectAchievementInfoEntities.get(i).getProInfoId();
                EntProjectInfoEntity entProjectInfoEntity = entProjectInfoService.selectById(proInfoId);
                if(entProjectInfoEntity.getEntInfoId() != null){
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(entProjectInfoEntity.getEntInfoId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                    entEnterpriseInfoEntity.setSysUser(sysUserEntity);
                    entProjectInfoEntity.setEntEnterpriseInfo(entEnterpriseInfoEntity);
                    entProjectAchievementInfoEntities.get(i).setEntProjectInfo(entProjectInfoEntity);
                }else {
                    entProjectAchievementInfoEntities.remove(i);
                    --i;
                }
            }
        }
        return new PageUtils(entProjectAchievementInfoEntities, count, pageSize, currPage);
    }


    @LimitPage(targetType = java.util.Map.class, name = "项目合作分页", index = 0, pageSize = 10,  currPage = 1)
    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inType", defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"0"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public PageUtils queryMyList(Map<String, Object> params) {
        Integer pageSize = (Integer) params.get("pageSize");
        Integer currPage = (Integer) params.get("currPage");
        String inApply = (String) params.get("inApply");
        Integer count = 0;
        List<EntProjectInfoEntity> list = new ArrayList<EntProjectInfoEntity>();
        if("userPerId".equals(params.get("inType"))){
            UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.queryByUserId(getUserId());
            Long userPerId = userPersonInfoEntity.getUserPerId();
            List<EntProjectInfoEntity> entProjectInfoEntities = entProjectInfoService.selectList(new EntityWrapper<EntProjectInfoEntity>().eq("user_per_id", userPerId));
            queryByProId(list, entProjectInfoEntities, inApply);
            count = baseMapper.queryCountPage(params);
        }
        if("userTeacherId".equals(params.get("inType"))){
            UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.queryByUserId(getUserId());
            Long userTeacherId = userTeacherInfoEntity.getUserTeacherId();
            List<EntProjectInfoEntity> entProjectInfoEntities = entProjectInfoService.selectList(new EntityWrapper<EntProjectInfoEntity>().eq("user_teacher_id", userTeacherId));
            queryByProId(list, entProjectInfoEntities, inApply);
            count = baseMapper.queryCountPage(params);
        }
        if("entInfoId".equals(params.get("inType"))){
            EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectOne(new EntityWrapper<EntEnterpriseInfoEntity>().eq("user_id", getUserId()));
            Long entInfoId = entEnterpriseInfoEntity.getEntInfoId();
            List<EntProjectInfoEntity> entProjectInfoEntities = entProjectInfoService.selectList(new EntityWrapper<EntProjectInfoEntity>().eq("ent_info_if", entInfoId));
            queryByProId(list, entProjectInfoEntities, inApply);
            count = baseMapper.queryCountPage(params);
        }
        return new PageUtils(list, count, pageSize, currPage);
    }

    /**
     * 根据项目Id查找项目成果
     */
    public void queryByProId(List<EntProjectInfoEntity> list, List<EntProjectInfoEntity> entProjectInfoEntities, String inApply){
        for (int i = 0; i < entProjectInfoEntities.size(); i++) {
            Long proInfoId = entProjectInfoEntities.get(i).getProInfoId();
            EntProjectAchievementInfoEntity projectAchievement = entProjectAchievementInfoService.selectOne(new EntityWrapper<EntProjectAchievementInfoEntity>().eq("pro_info_id", proInfoId));
            if(projectAchievement != null){
                if(projectAchievement.getInApply().equals(inApply)){
                    entProjectInfoEntities.get(i).setEntProjectAchievementInfo(projectAchievement);
                    list.add(entProjectInfoEntities.get(i));
                }
            }
        }
    }

}