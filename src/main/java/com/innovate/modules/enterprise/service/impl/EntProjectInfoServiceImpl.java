package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntProjectInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.common.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.enterprise.service.EntPersonCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserRoleService;
import com.innovate.modules.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("entProjectInfoService")
public class EntProjectInfoServiceImpl extends ServiceImpl<EntProjectInfoDao, EntProjectInfoEntity> implements EntProjectInfoService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private EntProjectCooperationInfoService entProjectCooperationInfoService;

    @Autowired
    private UserTeacherInfoService userTeacherInfoService;

    @Autowired
    private UserPerInfoService userPerInfoService;

    @Autowired
    private EntEnterpriseInfoService entEnterpriseInfoService;

    @Autowired
    private EntPersonCooperationInfoService entPersonCooperationInfoService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inType", defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"0"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<EntProjectInfoEntity> wrapper = new EntityWrapper<>();
        String type = (String) params.get("inType");
        if("userPerId".equals(type)){ // 学生
            wrapper.isNotNull("user_per_id");
        }else if("userTeacherId".equals(type)){ // 教师
            wrapper.isNotNull("user_teacher_id");
        }else if ("entInfoId".equals(type)){ // 企业
            wrapper.isNotNull("ent_info_id");
        }
        // 角色条件
        if(params.get("user_per_id") != null){ // 学生
            wrapper.eq("user_per_id",params.get("user_per_id"));
        }else if(params.get("user_teacher_id") != null){// 教师
            wrapper.eq("user_teacher_id",params.get("user_teacher_id"));
        }else if(params.get("ent_info_id") != null){// 企业
            wrapper.eq("ent_info_id",params.get("ent_info_id"));
        }
        if("0".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "0");
        }else {
            wrapper.eq("in_apply", "1");
        }
        if(params.get("key") != null){
            wrapper.like("pro_name", (String) params.get("key"));
        }
        Page<EntProjectInfoEntity> page = this.selectPage( new Query<EntProjectInfoEntity>(params).getPage(),wrapper);
        List<EntProjectInfoEntity> records = page.getRecords();
        if(records != null && records.size() > 0){
            for(int i=0;i<records.size();i++){
                EntProjectInfoEntity entity = records.get(i);
                SysUserEntity user = null;
                if("userPerId".equals(type)){ // 学生
                    user = sysUserService.selectById(entity.getUserPerId());
                }else if("userTeacherId".equals(type)){ // 教师
                    user = sysUserService.selectById(entity.getUserTeacherId());
                }else if ("entInfoId".equals(type)){ // 企业
                    user =  sysUserService.selectById(entity.getEntInfoId());
                }
                entity.setSysUser(user);
            }
        }
        return new PageUtils(page);
    }

    @Override
    public R queryEntProjectInfoByIdPerId(Long id) {
        EntProjectInfoEntity entity = baseMapper.queryEntProjectInfoByIdPerId(id);
        if(entity.getUserPersonInfo() != null){
            SysUserEntity user = sysUserService.selectById(entity.getUserPersonInfo().getUserId());
            entity.setSysUser(user);
        }else{
            UserPersonInfoEntity personInfoEntity = userPerInfoService.selectById(entity.getUserPerId());
            SysUserEntity sysUserEntity = sysUserService.selectById(personInfoEntity.getUserId());
            entity.setSysUser(sysUserEntity);
        }
        return R.ok().put("data", entity);
    }

    @Override
    public R queryEntProjectInfoByIdTeacherId(Long id) {
        EntProjectInfoEntity entity = baseMapper.queryEntProjectInfoByIdTeacherId(id);
        if(entity.getUserTeacherInfo() != null){
            SysUserEntity user = sysUserService.selectById(entity.getUserTeacherInfo().getUserId());
            entity.setSysUser(user);
        }else{
            UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(entity.getUserTeacherId());
            SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
            entity.setSysUser(sysUserEntity);
        }
        return R.ok().put("data",entity);
    }

    @Override
    public R queryEntProjectInfoByIdEntId(Long id) {
        EntProjectInfoEntity entity = baseMapper.queryEntProjectInfoByIdEntId(id);
        if(entity.getEntEnterpriseInfo() != null){
            SysUserEntity user = sysUserService.selectById(entity.getEntEnterpriseInfo().getUserId());
            entity.setSysUser(user);
        }else {
            EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(entity.getEntInfoId());
            SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
            entity.setSysUser(sysUserEntity);
        }
        return R.ok().put("data", entity);
    }

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inApply", defValue = "1", defValueEnum = DefValueEnum.STRING)
    @Override
    public R updateEntExamine(Map<String, Object> params) {
        boolean b = baseMapper.updateEntExamine(params);
        return R.ok().put("data",b);
    }

    @DefaultValue(targetType = java.lang.String.class, index = 1, key = "inType",defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @Override
    public R queryProjectPersonCooperationInfo(Long proInfoId, String inType, String inApply) {
        EntProjectInfoEntity project = baseMapper.selectById(proInfoId);
        if(project == null){ return R.error();}
        EntProjectCooperationInfoEntity projectCooperation = entProjectCooperationInfoService.queryEntProjectCooperationInfoByProjectId(project.getProInfoId());
        List<EntPersonCooperationInfoEntity> persons = entPersonCooperationInfoService.queryPersonCooperationInfoByProCooperationInfoIdAndApply(projectCooperation.getProCooperationInfoId(),inApply);

        List<EntPersonCooperationInfoEntity> pers = new ArrayList<>();

        List<EntPersonCooperationInfoEntity> teachers = new ArrayList<>();

        List<EntPersonCooperationInfoEntity> ents = new ArrayList<>();

        if(persons != null && persons.size() > 0){
            for(int i=0; i< persons.size(); i++){
                EntPersonCooperationInfoEntity entity = persons.get(i);
                // 合作者
                if(null != entity.getUserPerId()){// 学生
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(entity.getUserPerId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                    userPersonInfoEntity.setSysUserEntity(sysUserEntity);
                    entity.setUserPersonInfo(userPersonInfoEntity);
                    pers.add(entity);
                }else if (null != entity.getUserTeacherId()){ // 教师
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(entity.getUserTeacherId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                    userTeacherInfoEntity.setSysUserEntity(sysUserEntity);
                    entity.setUserTeacherInfo(userTeacherInfoEntity);
                    teachers.add(entity);
                }else if (null != entity.getEntInfoId()){ // 企业
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(entity.getEntInfoId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                    entEnterpriseInfoEntity.setSysUser(sysUserEntity);
                    entity.setEntEnterpriseInfo(entEnterpriseInfoEntity);
                    ents.add(entity);
                }
            }
        }
        projectCooperation.setPersonCooperationInfos(persons);
        projectCooperation.setPersonCooperationPer(pers);
        projectCooperation.setPersonCooperationTeacher(teachers);
        projectCooperation.setPersonCooperationEnt(ents);
        project.setProjectCooperationInfo(projectCooperation);
        if("userPerId".equals(inType)){ // 学生
            UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(project.getUserPerId());
            SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
            project.setSysUser(sysUserEntity);
            project.setUserPersonInfo(userPersonInfoEntity);
        }else if("userTeacherId".equals(inType)){ // 教师
            UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(project.getUserTeacherId());
            SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
            project.setSysUser(sysUserEntity);
            project.setUserTeacherInfo(userTeacherInfoEntity);
        }else if ("entInfoId".equals(inType)){ // 企业
            EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(project.getEntInfoId());
            SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
            project.setSysUser(sysUserEntity);
            project.setEntEnterpriseInfo(entEnterpriseInfoEntity);
        }
         return R.ok().put("data", project);
    }

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0,
            key = { "inApply", "pageSize", "currPage", "proType" }, defValue = { "1", "12", "1", "0" },
            defValueEnum = { DefValueEnum.STRING, DefValueEnum.INTEGER, DefValueEnum.INTEGER , DefValueEnum.INTEGER})
    @Override
    public R queryWebEntProjectInfos(Map<String, Object> params) {
        EntityWrapper<EntProjectInfoEntity> wrapper = new EntityWrapper<>();
        List<Long> proInfoIds = entProjectCooperationInfoService.queryProInfoIdsByInApply("1");
        if(proInfoIds == null){
            return R.ok().put("data", null);
        }
        wrapper.in("pro_info_id", proInfoIds);
        wrapper.eq("in_apply", params.get("inApply"));
        if(params.get("proType") != null && Integer.valueOf(params.get("proType").toString()) != 0){
            wrapper.eq("pro_type", params.get("proType"));
        }
        Page<EntProjectInfoEntity> page = this.selectPage( new Query<EntProjectInfoEntity>(params).getPage(),wrapper);
        List<EntProjectInfoEntity> records = page.getRecords();
        if(records != null && records.size() > 0){
            for (int i = 0; i < records.size(); i++){
                EntProjectInfoEntity project = records.get(i);
                // 项目发布者
                if(project.getUserPerId() != null){ // 学生
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(project.getUserPerId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                    userPersonInfoEntity.setSysUserEntity(sysUserEntity);
                    project.setUserPersonInfo(userPersonInfoEntity);
                }else if(project.getUserTeacherId() != null){ // 教师
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(project.getUserTeacherId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                    userTeacherInfoEntity.setSysUserEntity(sysUserEntity);
                    project.setUserTeacherInfo(userTeacherInfoEntity);
                }else if(project.getEntInfoId() != null){ // 企业
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(project.getEntInfoId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                    entEnterpriseInfoEntity.setSysUser(sysUserEntity);
                    project.setEntEnterpriseInfo(entEnterpriseInfoEntity);
                }
            }
        }
        return R.ok().put("data", page);
    }

    @Override
    public R insertEntProject(EntProjectInfoEntity entProjectInfo) {
        HashMap<Long, Long> roleMap = new HashMap<>();
        roleMap.put(11L, 11L);
        roleMap.put(12L, 12L);
        roleMap.put(7L, 7L);
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            return  R.error("未登录系统或已过期，请重新登录。");
        }
        List<Long> roles = sysUserRoleService.queryRoleIdList(user.getUserId());
        if(roles != null && roles.size() > 0){
            for(int i = 0; i < roles.size(); i++){
                Long aLong = roles.get(i);
                Long aLong1 = roleMap.get(aLong);
                if(aLong1 != null && aLong1 == 11L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(user.getUserId());
                    entProjectInfo.setUserPerId(userPerId);
                    break;
                }else if (aLong1 != null && aLong1 == 12L){ // 教师
                    Long userTeacherId = userTeacherInfoService.queryUserTeacherIdByUserId(user.getUserId());
                    entProjectInfo.setUserTeacherId(userTeacherId);
                    break;
                }else if (aLong1 != null && aLong1 == 7L){ // 企业
                    Long entInfoId = entEnterpriseInfoService.queryEntInfoIdByUserId(user.getUserId());
                    entProjectInfo.setEntInfoId(entInfoId);
                    break;
                }
            }
        }
        baseMapper.insert(entProjectInfo);
        return R.ok();
    }

    @Override
    public R queryPeojects() {
        HashMap<Long, Long> roleMap = new HashMap<>();
        roleMap.put(11L, 11L);
        roleMap.put(12L, 12L);
        roleMap.put(7L, 7L);
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            return  R.error("未登录系统或已过期，请重新登录。");
        }
        List<Long> roles = sysUserRoleService.queryRoleIdList(user.getUserId());
        List<EntProjectInfoEntity> result = null;
        if(roles != null && roles.size() > 0){
            for(int i = 0; i < roles.size(); i++){
                Long aLong = roles.get(i);
                Long aLong1 = roleMap.get(aLong);
                if(aLong1 != null && aLong1 == 11L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByUserPerId(userPerId);
                    List<Long> pcids = entProjectCooperationInfoService.queryProjectInfoIdByType("user_per_id", userPerId);
                    invokeProject(result, pcids);
                    break;
                }else if (aLong1 != null && aLong1 == 12L){ // 教师
                    Long userTeacherId = userTeacherInfoService.queryUserTeacherIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByUserTeacherId(userTeacherId);
                    List<Long> pcids = entProjectCooperationInfoService.queryProjectInfoIdByType("user_teacher_id", userTeacherId);
                    invokeProject(result, pcids);
                    break;
                }else if (aLong1 != null && aLong1 == 7L){ // 企业
                    Long entInfoId = entEnterpriseInfoService.queryEntInfoIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByEnterId(entInfoId);
                    List<Long> pcids = entProjectCooperationInfoService.queryProjectInfoIdByType("ent_info_id", entInfoId);
                    invokeProject(result, pcids);
                    break;
                }
            }
        }
        return R.ok().put("data", result);
    }

    @Override
    public R queryWebEntProjectInfo(Long projectId, String inApply) {
        EntProjectInfoEntity project = baseMapper.queryProjectByProjectIdAndInApply(projectId, inApply);
        if(project != null ){
            // 项目发布者
            if(project.getUserPerId() != null){ // 学生
                UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(project.getUserPerId());
                SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                project.setSysUser(sysUserEntity);
                project.setUserPersonInfo(userPersonInfoEntity);
            }else if(project.getUserTeacherId() != null){ // 教师
                UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(project.getUserTeacherId());
                SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                project.setSysUser(sysUserEntity);
                project.setUserTeacherInfo(userTeacherInfoEntity);
            }else if(project.getEntInfoId() != null){ // 企业
                EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(project.getEntInfoId());
                SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                project.setSysUser(sysUserEntity);
                project.setEntEnterpriseInfo(entEnterpriseInfoEntity);
            }
        }
        return R.ok().put("data", project);
    }

    /**
     * 提出已存在合作关系的项目
     * @param result
     * @param pcids
     */
    private void invokeProject(List<EntProjectInfoEntity> result, List<Long> pcids) {
        if (result != null && pcids != null) {
            for (int j = 0; j < pcids.size(); j++) {
                for (int k = 0; k < result.size(); k++) {
                    if (result.get(k).getProInfoId() == pcids.get(j)) {
                        result.remove(k);
                    }
                }
            }
        }
    }


}
