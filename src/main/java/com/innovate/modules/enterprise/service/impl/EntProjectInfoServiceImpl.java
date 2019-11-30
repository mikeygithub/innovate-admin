package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntProjectInfoDao;
import com.innovate.modules.enterprise.entity.*;
import com.innovate.common.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.innovate.common.utils.ShiroUtils.getUserId;


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
    @Autowired
    private EntStudentAttachmentService studentAttachmentService;
    @Autowired
    private EntTeacherAttachmentService teacherAttachmentService;
    @Autowired
    private EntEnterpriseAttachmentService enterpriseAttachmentService;
    @Autowired
    private EntPersonCooperationInfoService personCooperationInfoService;
    @Autowired
    private EntProjectAttachService entProjectAttachService;
    @Autowired
    private EntProjectAchievementInfoService entProjectAchievementInfoService;


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
        if(params.get("key") != null && !"".equals(params.get("key"))){
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

    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inType", defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"0"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public PageUtils queryListByUserId(Map<String, Object> params) {
        EntityWrapper<EntProjectInfoEntity> wrapper = new EntityWrapper<>();
        // 角色条件
        if(params.get("inType").equals("userPerId")){ // 学生
            Long userPerId = userPerInfoService.queryUserPerIdByUserId(getUserId());
            wrapper.eq("user_per_id",userPerId);
        }else if(params.get("inType").equals("userTeacherId")){// 教师
            Long userTeacherId = userTeacherInfoService.queryUserTeacherIdByUserId(getUserId());
            wrapper.eq("user_teacher_id",userTeacherId);
        }else if(params.get("inType").equals("entInfoId")){// 企业
            Long entInfoId = entEnterpriseInfoService.queryEntInfoIdByUserId(getUserId());
            wrapper.eq("ent_info_id",entInfoId);
        }
        if("0".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "0");
        }else {
            wrapper.eq("in_apply", "1");
        }
        if(!params.get("key").equals("")){
            wrapper.like("pro_name", (String) params.get("key"));
        }
        Page<EntProjectInfoEntity> page = this.selectPage( new Query<EntProjectInfoEntity>(params).getPage(),wrapper);
        List<EntProjectInfoEntity> records = page.getRecords();
//        if(records != null && records.size() > 0){
//            for(int i=0;i<records.size();i++){
//                EntProjectInfoEntity entity = records.get(i);
//                SysUserEntity user = null;
//                if("userPerId".equals(type)){ // 学生
//                    user = sysUserService.selectById(entity.getUserPerId());
//                }else if("userTeacherId".equals(type)){ // 教师
//                    user = sysUserService.selectById(entity.getUserTeacherId());
//                }else if ("entInfoId".equals(type)){ // 企业
//                    user =  sysUserService.selectById(entity.getEntInfoId());
//                }
//                entity.setSysUser(user);
//            }
//        }
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
        //项目附件
        EntityWrapper<EntProjectAttachEntity> wrapperAttach = new EntityWrapper<EntProjectAttachEntity>();
        wrapperAttach.eq("pro_info_id", id);
        List<EntProjectAttachEntity> attachments = entProjectAttachService.selectList(wrapperAttach);
        entity.setEntProjectAttachEntities(attachments);
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
        //项目附件
        EntityWrapper<EntProjectAttachEntity> wrapperAttach = new EntityWrapper<EntProjectAttachEntity>();
        wrapperAttach.eq("pro_info_id", id);
        List<EntProjectAttachEntity> attachments = entProjectAttachService.selectList(wrapperAttach);
        entity.setEntProjectAttachEntities(attachments);
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
        //项目附件
        EntityWrapper<EntProjectAttachEntity> wrapperAttach = new EntityWrapper<EntProjectAttachEntity>();
        wrapperAttach.eq("pro_info_id", id);
        List<EntProjectAttachEntity> attachments = entProjectAttachService.selectList(wrapperAttach);
        entity.setEntProjectAttachEntities(attachments);
        return R.ok().put("data", entity);
    }

    @Transactional
    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inApply", defValue = "1", defValueEnum = DefValueEnum.STRING)
    @Override
    public R updateEntExamine(Map<String, Object> params) {
        boolean b = false;
        if("1".equals(params.get("inApply"))){
            b = baseMapper.updateEntExamine(params);
        }else if ("0".equals(params.get("inApply"))){
            Long proInfoId = Long.valueOf(params.get("proInfoId").toString());
            baseMapper.deleteById(proInfoId);
            EntProjectCooperationInfoEntity cooperationInfo = entProjectCooperationInfoService.queryEntProjectCooperationInfoByProjectId(proInfoId);
            b = entProjectCooperationInfoService.deleteByProInfoId(proInfoId);
            if(cooperationInfo != null) {
                b = entPersonCooperationInfoService.deleteByProCooperationInfoId(cooperationInfo.getProCooperationInfoId());
            }
        }
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
        roleMap.put(2L, 2L);
        roleMap.put(3L, 3L);
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            return  R.error("未登录系统或已过期，请重新登录。");
        }
        List<Long> roles = sysUserRoleService.queryRoleIdList(user.getUserId());
        if(roles != null && roles.size() > 0){
            for(int i = 0; i < roles.size(); i++){
                Long aLong = roles.get(i);
                Long aLong1 = roleMap.get(aLong);
                if(aLong1 != null && aLong1 == 11L || aLong1 == 2L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(user.getUserId());
                    entProjectInfo.setUserPerId(userPerId);
                    break;
                }else if (aLong1 != null && aLong1 == 12L  || aLong1 == 3L){ // 教师
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
        roleMap.put(2L, 2L);
        roleMap.put(3L, 3L);
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
                if(aLong1 != null && aLong1 == 11L || aLong1 == 2L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByUserPerId(userPerId);
                    List<Long> pcids = entProjectCooperationInfoService.queryProjectInfoIdByType("user_per_id", userPerId);
                    invokeProject(result, pcids);
                    break;
                }else if (aLong1 != null && aLong1 == 12L || aLong1 == 3L){ // 教师
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
    public R queryPeojectsByAchieve() {
        HashMap<Long, Long> roleMap = new HashMap<>();
        roleMap.put(11L, 11L);
        roleMap.put(12L, 12L);
        roleMap.put(7L, 7L);
        roleMap.put(2L, 2L);
        roleMap.put(3L, 3L);
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
                if(aLong1 != null && aLong1 == 11L || aLong1 == 2L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByUserPerId(userPerId);
                    invokeProjectAchieve(result);
                    break;
                }else if (aLong1 != null && aLong1 == 12L || aLong1 == 3L){ // 教师
                    Long userTeacherId = userTeacherInfoService.queryUserTeacherIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByUserTeacherId(userTeacherId);
                    invokeProjectAchieve(result);
                    break;
                }else if (aLong1 != null && aLong1 == 7L){ // 企业
                    Long entInfoId = entEnterpriseInfoService.queryEntInfoIdByUserId(user.getUserId());
                    result = baseMapper.queryProjectsByEnterId(entInfoId);
                    invokeProjectAchieve(result);
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

    @Override
    public R queryProjectInfoByIdAndType(Long proInfoId, String inType) {
        EntProjectInfoEntity project = selectById(proInfoId);
        if(project != null){
            //负责人信息
            SysUserEntity sysUserEntity = new SysUserEntity();
            if(project.getUserPerId() != null){
                UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(project.getUserPerId());
                sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
            }else if (project.getUserTeacherId() != null){
                UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(project.getUserTeacherId());
                sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
            }else if (project.getEntInfoId() != null){
                EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(project.getEntInfoId());
                sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
            }
            project.setSysUser(sysUserEntity);
            //项目附件
            EntityWrapper<EntProjectAttachEntity> wrapperAttach = new EntityWrapper<EntProjectAttachEntity>();
            wrapperAttach.eq("pro_info_id", project.getProInfoId());
            List<EntProjectAttachEntity> attachments = entProjectAttachService.selectList(wrapperAttach);
            project.setEntProjectAttachEntities(attachments);
            // 合作项目信息
            EntityWrapper<EntProjectCooperationInfoEntity> wrapper = new EntityWrapper<EntProjectCooperationInfoEntity>();
            wrapper.eq("pro_info_id",project.getProInfoId());
            List<EntProjectCooperationInfoEntity> list = entProjectCooperationInfoService.selectList(wrapper);
            EntProjectCooperationInfoEntity projectCooperationInfo = list != null && list.size() > 0 ? list.get(0) : null;
            project.setProjectCooperationInfo(projectCooperationInfo);
            // 项目合作者信息
            if(projectCooperationInfo != null) {
                EntityWrapper<EntPersonCooperationInfoEntity> personWrapper = new EntityWrapper<>();
                List<EntPersonCooperationInfoEntity> persons = personCooperationInfoService.selectList(personWrapper);

                // 因为有三个不同的用户类型，所以这里需要给三个ArrayList
                ArrayList<EntPersonCooperationInfoEntity> ents = new ArrayList<EntPersonCooperationInfoEntity>();
                ArrayList<EntPersonCooperationInfoEntity> teachers = new ArrayList<EntPersonCooperationInfoEntity>();
                ArrayList<EntPersonCooperationInfoEntity> students = new ArrayList<EntPersonCooperationInfoEntity>();
                projectCooperationInfo.setPersonCooperationEnt(ents);
                projectCooperationInfo.setPersonCooperationTeacher(teachers);
                projectCooperationInfo.setPersonCooperationPer(students);
                if(persons != null && !persons.isEmpty()){
                    for (int i=0; i<persons.size(); i++) {
                        EntPersonCooperationInfoEntity person = persons.get(i);
                        // 合作者用户类型
                        if(person.getEntInfoId() != null){ // 企业
                            EntEnterpriseInfoEntity ent = entEnterpriseInfoService.selectById(person.getEntInfoId());
                            SysUserEntity sysUser = sysUserService.selectById(ent.getUserId());
                            ent.setSysUser(sysUser);
                            person.setEntEnterpriseInfo(ent);
                            ents.add(person);
                        }else if(person.getUserTeacherId() != null){ // 教师
                            UserTeacherInfoEntity teacher = userTeacherInfoService.selectById(person.getUserTeacherId());
                            SysUserEntity sysUser = sysUserService.selectById(teacher.getUserId());
                            teacher.setSysUserEntity(sysUser);
                            person.setUserTeacherInfo(teacher);
                            teachers.add(person);
                        }else if(person.getUserPerId() != null){ // 学生
                            UserPersonInfoEntity student = userPerInfoService.selectById(person.getUserPerId());
                            SysUserEntity sysUser = sysUserService.selectById(student.getUserId());
                            student.setSysUserEntity(sysUser);
                            person.setUserPersonInfo(student);
                            students.add(person);
                        }
                    }
                }
            }
            return R.ok().put("data", project);
        }else{
            return R.error();
        }
    }

    // 当前不能使用默认值，可能会出现数据不一致的问题，暂时去掉
    //@DefaultValue(targetType = java.lang.String.class, index = 1, key = "inType",defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @Override
    public R queryProjectInfoByTypeAndId(Long proInfoId, String inType) {
        EntProjectInfoEntity project = selectById(proInfoId);
        if(project != null){
            // 项目发布者 + 专利信息（项目要求相关文件）
            if("entInfoId".equals(inType)){// 企业
                EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(project.getEntInfoId());
                SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                entEnterpriseInfoEntity.setSysUser(sysUserEntity);
                project.setEntEnterpriseInfo(entEnterpriseInfoEntity);
                EntityWrapper<EntEnterpriseAttachmentEntity> wrapper = new EntityWrapper<EntEnterpriseAttachmentEntity>();
                wrapper.eq("pro_info_id", project.getProInfoId()).eq("ent_info_id", entEnterpriseInfoEntity.getEntInfoId());
                List<EntEnterpriseAttachmentEntity> attachments = enterpriseAttachmentService.selectList(wrapper);
                project.setEnterpriseAttachments(attachments);
            }else if("userPerId".equals(inType)){ // 学生
                UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(project.getUserPerId());
                SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                userPersonInfoEntity.setSysUserEntity(sysUserEntity);
                project.setUserPersonInfo(userPersonInfoEntity);
                EntityWrapper<EntStudentAttachmentEntity> wrapper = new EntityWrapper<EntStudentAttachmentEntity>();
                wrapper.eq("pro_info_id", project.getProInfoId()).eq("user_per_id", userPersonInfoEntity.getUserPerId());
                List<EntStudentAttachmentEntity> attachments = studentAttachmentService.selectList(wrapper);
                project.setStudentAttachments(attachments);
            }else if("userTeacherId".equals(inType)){ // 教师
                UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(project.getUserTeacherId());
                SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                userTeacherInfoEntity.setSysUserEntity(sysUserEntity);
                project.setUserTeacherInfo(userTeacherInfoEntity);
                EntityWrapper<EntTeacherAttachmentEntity> wrapper = new EntityWrapper<EntTeacherAttachmentEntity>();
                List<EntTeacherAttachmentEntity> attachments = teacherAttachmentService.selectList(wrapper);
                project.setTeacherAttachments(attachments);
            }
            // 合作项目信息
            EntityWrapper<EntProjectCooperationInfoEntity> wrapper = new EntityWrapper<EntProjectCooperationInfoEntity>();
            wrapper.eq("pro_info_id",project.getProInfoId());
            List<EntProjectCooperationInfoEntity> list = entProjectCooperationInfoService.selectList(wrapper);
            EntProjectCooperationInfoEntity projectCooperationInfo = list != null && list.size() > 0 ? list.get(0) : null;
            project.setProjectCooperationInfo(projectCooperationInfo);
            // 项目合作者信息
            if(projectCooperationInfo != null) {
                EntityWrapper<EntPersonCooperationInfoEntity> personWrapper = new EntityWrapper<>();
                List<EntPersonCooperationInfoEntity> persons = personCooperationInfoService.selectList(personWrapper);

                // 因为有三个不同的用户类型，所以这里需要给三个ArrayList
                ArrayList<EntPersonCooperationInfoEntity> ents = new ArrayList<EntPersonCooperationInfoEntity>();
                ArrayList<EntPersonCooperationInfoEntity> teachers = new ArrayList<EntPersonCooperationInfoEntity>();
                ArrayList<EntPersonCooperationInfoEntity> students = new ArrayList<EntPersonCooperationInfoEntity>();
                projectCooperationInfo.setPersonCooperationEnt(ents);
                projectCooperationInfo.setPersonCooperationTeacher(teachers);
                projectCooperationInfo.setPersonCooperationPer(students);
                if(persons != null && !persons.isEmpty()){
                    for (int i=0; i<persons.size(); i++) {
                        EntPersonCooperationInfoEntity person = persons.get(i);
                        // 合作者用户类型
                        if(person.getEntInfoId() != null){ // 企业
                            EntEnterpriseInfoEntity ent = entEnterpriseInfoService.selectById(person.getEntInfoId());
                            person.setEntEnterpriseInfo(ent);
                            ents.add(person);
                        }else if(person.getUserTeacherId() != null){ // 教师
                            UserTeacherInfoEntity teacher = userTeacherInfoService.selectById(person.getUserTeacherId());
                            person.setUserTeacherInfo(teacher);
                            teachers.add(person);
                        }else if(person.getUserPerId() != null){ // 学生
                            UserPersonInfoEntity student = userPerInfoService.selectById(person.getUserPerId());
                            person.setUserPersonInfo(student);
                            students.add(person);
                        }
                    }
                }
            }
            return R.ok().put("data", project);
        }else{
            return R.error();
        }
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

    /**
     * 提出已存在项目成果的项目
     * @param result
     */
    private void invokeProjectAchieve(List<EntProjectInfoEntity> result) {
        for (int j = 0; j < result.size(); j++) {
            Long proInfoId = result.get(j).getProInfoId();
            EntProjectAchievementInfoEntity entProjectAchievementInfoEntity = entProjectAchievementInfoService.selectOne(new EntityWrapper<EntProjectAchievementInfoEntity>().eq("pro_info_id", proInfoId));
            if(entProjectAchievementInfoEntity != null){
                result.remove(j);
                --j;
            }
        }
    }


}
