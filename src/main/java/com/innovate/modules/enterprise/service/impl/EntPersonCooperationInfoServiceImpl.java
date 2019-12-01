package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.dao.EntPersonCooperationInfoDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("entPersonCooperationInfoService")
public class EntPersonCooperationInfoServiceImpl extends ServiceImpl<EntPersonCooperationInfoDao, EntPersonCooperationInfoEntity> implements EntPersonCooperationInfoService {

    @Autowired
    private EntProjectInfoService entProjectInfoService;

    @Autowired
    private UserTeacherInfoService userTeacherInfoService;

    @Autowired
    private UserPerInfoService userPerInfoService;

    @Autowired
    private EntEnterpriseInfoService entEnterpriseInfoService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private EntProjectCooperationInfoService entProjectCooperationInfoService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private EntPersonCooperationInfoService entPersonCooperationInfoService;


    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"0", "userPerId","1"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<EntPersonCooperationInfoEntity> page = this.selectPage(
//                new Query<EntPersonCooperationInfoEntity>(params).getPage(),
//                new EntityWrapper<EntPersonCooperationInfoEntity>()
//        );
//        Page<EntProjectCooperationInfoEntity> pages = pages(params, true);
        Page<EntProjectCooperationInfoEntity> page = invokePage(params);
        List<EntProjectCooperationInfoEntity> records = page.getRecords();
        String type = (String) params.get("inType");
        invokeItem(type, records);
        return new PageUtils(page);
    }

    @Override
    public EntProjectCooperationInfoEntity queryPersonCooperationInfoList(Long proCooperationInfoId, String inType, String inApply) {
        EntProjectCooperationInfoEntity entProjectCooperationInfoEntity = new EntProjectCooperationInfoEntity();
        EntityWrapper<EntPersonCooperationInfoEntity> wrapper = new EntityWrapper<EntPersonCooperationInfoEntity>();
        wrapper.eq("pro_cooperation_info_id", proCooperationInfoId);
        List<EntPersonCooperationInfoEntity> entPersonCooperationInfoEntities = entPersonCooperationInfoService.selectList(wrapper);

        if(entPersonCooperationInfoEntities.size() > 0){
            if("userPerId".equals(inType)){ // 学生
                List<UserPersonInfoEntity> userPersonInfoEntities = new ArrayList<UserPersonInfoEntity>();
                for (int i = 0; i < entPersonCooperationInfoEntities.size(); i++) {
                    if(entPersonCooperationInfoEntities.get(i).getUserPerId() != null && inApply.equals(entPersonCooperationInfoEntities.get(i).getInApply())){
                        Long userPerId = entPersonCooperationInfoEntities.get(i).getUserPerId();
                        UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(userPerId);
                        userPersonInfoEntity.setEntPersonCooperationInfoEntity(entPersonCooperationInfoEntities.get(i));
                        Long userId = userPersonInfoEntity.getUserId();
                        userPersonInfoEntity.setSysUserEntity(sysUserService.selectById(userId));
                        userPersonInfoEntities.add(userPersonInfoEntity);
                    }
                }

                entProjectCooperationInfoEntity.setUserPersonInfoEntities(userPersonInfoEntities);
            }
            if("userTeacherId".equals(inType)){ // 教师
                List<UserTeacherInfoEntity> userTeacherInfoEntities = new ArrayList<UserTeacherInfoEntity>();
                for (int i = 0; i < entPersonCooperationInfoEntities.size(); i++) {
                    if(entPersonCooperationInfoEntities.get(i).getUserTeacherId() != null  && inApply.equals(entPersonCooperationInfoEntities.get(i).getInApply())){
                        Long userTeacherId = entPersonCooperationInfoEntities.get(i).getUserTeacherId();
                        UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(userTeacherId);
                        userTeacherInfoEntity.setEntPersonCooperationInfoEntity(entPersonCooperationInfoEntities.get(i));
                        Long userId = userTeacherInfoEntity.getUserId();
                        userTeacherInfoEntity.setSysUserEntity(sysUserService.selectById(userId));
                        userTeacherInfoEntities.add(userTeacherInfoEntity);
                    }
                }

                entProjectCooperationInfoEntity.setUserTeacherInfoEntities(userTeacherInfoEntities);
            }
            if("entInfoId".equals(inType)){ // 企业
                List<EntEnterpriseInfoEntity> entEnterpriseInfoEntities = new ArrayList<EntEnterpriseInfoEntity>();
                for (int i = 0; i < entPersonCooperationInfoEntities.size(); i++) {
                    if(entPersonCooperationInfoEntities.get(i).getEntInfoId() != null  && inApply.equals(entPersonCooperationInfoEntities.get(i).getInApply())){
                        Long entInfoId = entPersonCooperationInfoEntities.get(i).getEntInfoId();
                        EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(entInfoId);
                        entEnterpriseInfoEntity.setEntPersonCooperationInfoEntity(entPersonCooperationInfoEntities.get(i));
                        Long userId = entEnterpriseInfoEntity.getUserId();
                        entEnterpriseInfoEntity.setSysUser(sysUserService.selectById(userId));
                        entEnterpriseInfoEntities.add(entEnterpriseInfoEntity);
                    }
                }

                entProjectCooperationInfoEntity.setEntEnterpriseInfoEntities(entEnterpriseInfoEntities);
            }
        }
        return entProjectCooperationInfoEntity;
    }

    @Override
    public EntProjectCooperationInfoEntity queryPersonCooperationInfoListBySelect(Long proCooperationInfoId, String inType, String inApply) {
        EntProjectCooperationInfoEntity entProjectCooperationInfoEntity = new EntProjectCooperationInfoEntity();
        EntityWrapper<EntPersonCooperationInfoEntity> wrapper = new EntityWrapper<EntPersonCooperationInfoEntity>();
        wrapper.eq("pro_cooperation_info_id", proCooperationInfoId);
        List<EntPersonCooperationInfoEntity> entPersonCooperationInfoEntities = entPersonCooperationInfoService.selectList(wrapper);

        if(entPersonCooperationInfoEntities.size() > 0){
            // 学生
            List<UserPersonInfoEntity> userPersonInfoEntities = new ArrayList<UserPersonInfoEntity>();
            for (int i = 0; i < entPersonCooperationInfoEntities.size(); i++) {
                if(entPersonCooperationInfoEntities.get(i).getUserPerId() != null){
                    Long userPerId = entPersonCooperationInfoEntities.get(i).getUserPerId();
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(userPerId);
                    Long userId = userPersonInfoEntity.getUserId();
                    userPersonInfoEntity.setSysUserEntity(sysUserService.selectById(userId));
                    userPersonInfoEntities.add(userPersonInfoEntity);
                }
            }
            entProjectCooperationInfoEntity.setUserPersonInfoEntities(userPersonInfoEntities);

            // 教师
            List<UserTeacherInfoEntity> userTeacherInfoEntities = new ArrayList<UserTeacherInfoEntity>();
            for (int i = 0; i < entPersonCooperationInfoEntities.size(); i++) {
                if(entPersonCooperationInfoEntities.get(i).getUserTeacherId() != null){
                    Long userTeacherId = entPersonCooperationInfoEntities.get(i).getUserTeacherId();
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(userTeacherId);
                    Long userId = userTeacherInfoEntity.getUserId();
                    userTeacherInfoEntity.setSysUserEntity(sysUserService.selectById(userId));
                    userTeacherInfoEntities.add(userTeacherInfoEntity);
                }
            }

            //企业
            entProjectCooperationInfoEntity.setUserTeacherInfoEntities(userTeacherInfoEntities);
            List<EntEnterpriseInfoEntity> entEnterpriseInfoEntities = new ArrayList<EntEnterpriseInfoEntity>();
            for (int i = 0; i < entPersonCooperationInfoEntities.size(); i++) {
                if(entPersonCooperationInfoEntities.get(i).getEntInfoId() != null){
                    Long entInfoId = entPersonCooperationInfoEntities.get(i).getEntInfoId();
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(entInfoId);
                    Long userId = entEnterpriseInfoEntity.getUserId();
                    entEnterpriseInfoEntity.setSysUser(sysUserService.selectById(userId));
                    entEnterpriseInfoEntities.add(entEnterpriseInfoEntity);
                }
            }

            entProjectCooperationInfoEntity.setEntEnterpriseInfoEntities(entEnterpriseInfoEntities);
        }
        return entProjectCooperationInfoEntity;
    }


    private Page<EntProjectCooperationInfoEntity> invokePage(Map<String, Object> params){
        EntityWrapper<EntProjectCooperationInfoEntity> wrapper = new EntityWrapper<EntProjectCooperationInfoEntity>();
        String type = (String) params.get("inType");
        if("userPerId".equals(type)){ // 学生
            wrapper.isNotNull("user_per_id");
        }else if("userTeacherId".equals(type)){ // 教师
            wrapper.isNotNull("user_teacher_id");
        }else if ("entInfoId".equals(type)){ // 企业
            wrapper.isNotNull("ent_info_id");
        }
        if(params.get("releaseType") != null){
            wrapper.eq("release_type", params.get("releaseType"));
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
        }else if("1".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "1");
        }else if("2".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "2");
        }else if("3".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "3");
        }
        if(params.get("key") != null){
            wrapper.like("pro_name", (String) params.get("key"));
        }

        Page<EntProjectCooperationInfoEntity> page = entProjectCooperationInfoService.selectPage( new Query<EntProjectCooperationInfoEntity>(params).getPage(), wrapper);
        return page;
    }

    @Override
    public List<EntPersonCooperationInfoEntity> queryPersonCooperationInfoByProCooperationInfoId(Long proCooperationInfoId) {
        return baseMapper.queryPersonCooperationInfoByProCooperationInfoId(proCooperationInfoId);
    }

    @Override
    public List<EntPersonCooperationInfoEntity> queryPersonCooperationInfoByProCooperationInfoIdAndApply(Long proCooperationInfoId, String inApply) {
        return baseMapper.queryPersonCooperationInfoByProCooperationInfoIdAndApply(proCooperationInfoId, inApply);
    }

    @Override
    public R savePersonCooper(Long proInfoId, Long userId) {
        EntProjectCooperationInfoEntity entity = entProjectCooperationInfoService.queryEntProjectCooperationInfoByProjectId(proInfoId);
        // 先处理当前用户是否已加如该项目
        if(entity == null){
            return R.error("该项目合作项目不存在！");
        }
        List<EntPersonCooperationInfoEntity> list = baseMapper.queryPersonCooperationInfoByProCooperationInfoId(entity.getProCooperationInfoId());
        boolean flag = false;
        if(list != null){
            for(int i = 0; i < list.size(); i++){
                EntPersonCooperationInfoEntity cooperationInfo = list.get(i);
                // 当前用户
                if(cooperationInfo.getUserPerId() != null){ // 学生
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(cooperationInfo.getUserPerId());
                    flag = userPersonInfoEntity != null && userPersonInfoEntity.getUserId().longValue() == userId;
                    break;
                }else if(cooperationInfo.getUserTeacherId() != null){ // 教师
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(cooperationInfo.getUserTeacherId());
                    flag = userTeacherInfoEntity != null  && userTeacherInfoEntity.getUserId().longValue() == userId;
                    break;
                }else if(cooperationInfo.getEntInfoId() != null) { // 企业
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(cooperationInfo.getEntInfoId());
                    flag = entEnterpriseInfoEntity != null  && entEnterpriseInfoEntity.getUserId().longValue() == userId;
                    break;
                }
            }
        }
        if(flag){
            return R.error("您已加入该项目不能重复加入");
        }
        EntPersonCooperationInfoEntity person = new EntPersonCooperationInfoEntity();
        person.setProCooperationInfoId(entity.getProCooperationInfoId());
        HashMap<Long, Long> roleMap = new HashMap<>();
        roleMap.put(11L, 11L);
        roleMap.put(12L, 12L);
        roleMap.put(7L, 7L);
        roleMap.put(2L, 2L);
        roleMap.put(3L, 3L);
        List<Long> roles = sysUserRoleService.queryRoleIdList(userId);
        if(roles != null && roles.size() > 0){
            for(int i = 0; i < roles.size(); i++){
                Long aLong = roles.get(i);
                Long aLong1 = roleMap.get(aLong);
                if(aLong1 != null && aLong1 == 11L || aLong1 == 2L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(userId);
                    person.setUserPerId(userPerId);
                    break;
                }else if (aLong1 != null && aLong1 == 12L  || aLong1 == 3L){ // 教师
                    Long userTeacherId = userTeacherInfoService.queryUserTeacherIdByUserId(userId);
                    person.setUserTeacherId(userTeacherId);
                    break;
                }else if (aLong1 != null && aLong1 == 7L){ // 企业
                    Long entInfoId = entEnterpriseInfoService.queryEntInfoIdByUserId(userId);
                    person.setEntInfoId(entInfoId);
                    break;
                }
            }
        }
        baseMapper.insert(person);
        return R.ok("加入项目成功，祝您合作愉快");
    }

    @Transactional
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"1"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public R updatePersonCooperation(Map<String, Object> params) {
        if(params.get("proCooperationId") != null){
            if("1".equals(params.get("inApply"))){
                baseMapper.updatePersonCooperation(Long.valueOf(params.get("proCooperationId").toString()), "pro_cooperation_id", "1");
            }else if("2".equals(params.get("inApply"))){
                baseMapper.updatePersonCooperation(Long.valueOf(params.get("proCooperationId").toString()), "pro_cooperation_id", "2");
            }
        }else if(params.get("proCooperationInfoId") != null){
            EntProjectCooperationInfoEntity cooperationInfo = entProjectCooperationInfoService.selectById(Long.valueOf(params.get("proCooperationInfoId").toString()));
            if(cooperationInfo != null){
                cooperationInfo.setInApply("1");
                entProjectCooperationInfoService.insertOrUpdate(cooperationInfo);
            }
            baseMapper.updatePersonCooperation(Long.valueOf(params.get("proCooperationInfoId").toString()), "pro_cooperation_info_id", "1");
        }
        return R.ok();
    }

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"0", "userPerId"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public PageUtils queryPageList(Map<String, Object> params) {
        //Page<EntProjectCooperationInfoEntity> pages = pages(params, true);
        Page<EntProjectCooperationInfoEntity> page = invokePage(params);
        List<EntProjectCooperationInfoEntity> records = page.getRecords();
        String type = (String) params.get("inType");
        invokeItem(type, records);
        return new PageUtils(page);
    }

    @Override
    public boolean deleteByProCooperationInfoId(Long proCooperationInfoId) {
        return baseMapper.deleteByProCooperationInfoId(proCooperationInfoId);
    }

    @Override
    public List<Long> queryProCooperationId(Long proCooperationInfoId) {
        return baseMapper.queryProCooperationId(proCooperationInfoId);
    }

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"1"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public R queryMyPersonProject(Map<String, Object> params) {
        EntityWrapper<EntPersonCooperationInfoEntity> wrapper = new EntityWrapper<EntPersonCooperationInfoEntity>();
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
        }else  if("1".equals(params.get("inApply"))){
            wrapper.eq("in_apply", "1");
        }
        Page<EntPersonCooperationInfoEntity> page = this.selectPage(new Query<EntPersonCooperationInfoEntity>(params).getPage(), wrapper);
        // 查询合作信息
        List<EntPersonCooperationInfoEntity> records = page.getRecords();
        if(records != null && records.size() > 0) {
            for(int i=0; i < records.size(); i++){
                EntPersonCooperationInfoEntity person = records.get(i);
                // 合作项目信息
                EntProjectCooperationInfoEntity projectCoo = entProjectCooperationInfoService.selectById(person.getProCooperationInfoId());
                person.setEntProjectCooperationInfo(projectCoo);
                // 项目信息
                if(projectCoo != null) {
                    EntProjectInfoEntity project = entProjectInfoService.selectById(projectCoo.getProInfoId());
                    person.setEntProjectInfo(project);
                    // 项目发布者信息
                    // 角色条件
                    if(project != null){
                        if(project.getUserPerId() != null){ // 学生
                            UserPersonInfoEntity userPersonInfo = userPerInfoService.selectById(project.getUserPerId());
                            project.setUserPersonInfo(userPersonInfo);
                        }else if(project.getUserTeacherId() != null){// 教师
                            UserTeacherInfoEntity userTeacherInfo = userTeacherInfoService.selectById(project.getUserTeacherId());
                            project.setUserTeacherInfo(userTeacherInfo);
                        }else if(project.getEntInfoId() != null){// 企业
                            EntEnterpriseInfoEntity entEnterpriseInfo = entEnterpriseInfoService.selectById(project.getEntInfoId());
                            project.setEntEnterpriseInfo(entEnterpriseInfo);
                        }
                    }
                }
            }
        }

        return R.ok().put("page", new PageUtils(page));
    }

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"1"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public R queryPersonProject(Map<String, Object> params) {
        EntityWrapper<EntProjectCooperationInfoEntity> wrapper = new EntityWrapper<>();
        // 角色条件
        if(params.get("user_per_id") != null){ // 学生
            wrapper.eq("user_per_id",params.get("user_per_id"));
        }else if(params.get("user_teacher_id") != null){// 教师
            wrapper.eq("user_teacher_id",params.get("user_teacher_id"));
        }else if(params.get("ent_info_id") != null){// 企业
            wrapper.eq("ent_info_id",params.get("ent_info_id"));
        }
        wrapper.eq("in_apply", params.get("inApply"));
        Page<EntProjectCooperationInfoEntity> page = entProjectCooperationInfoService.selectPage(new Query<EntProjectCooperationInfoEntity>(params).getPage(), wrapper);
        List<EntProjectCooperationInfoEntity> records = page.getRecords();
        if(records != null && records.size() > 0){
            for(int i=0; i<records.size(); i++){
                EntProjectCooperationInfoEntity projectCoo = records.get(i);
                // 项目信息
                EntProjectInfoEntity project = entProjectInfoService.selectById(projectCoo.getProInfoId());
                projectCoo.setProjectInfo(project);
                // 我的信息
                if(project != null){
                    // 角色条件
                    if(project.getUserPerId() != null){ // 学生
                        UserPersonInfoEntity userPersonInfo = userPerInfoService.selectById(project.getUserPerId());
                        project.setUserPersonInfo(userPersonInfo);
                    }else if(project.getUserTeacherId() != null){// 教师
                        UserTeacherInfoEntity userTeacherInfo = userTeacherInfoService.selectById(project.getUserTeacherId());
                        project.setUserTeacherInfo(userTeacherInfo);
                    }else if(project.getEntInfoId() != null){// 企业
                        EntEnterpriseInfoEntity entEnterpriseInfo = entEnterpriseInfoService.selectById(project.getEntInfoId());
                        project.setEntEnterpriseInfo(entEnterpriseInfo);
                    }
                }
            }
        }
        return R.ok().put("page", page);
    }

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"0", "userPerId"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public R queryMyApplyPage(Map<String, Object> params, Long userId) {
        String type = (String) params.get("inType");
        EntityWrapper<EntPersonCooperationInfoEntity> wrapper = new EntityWrapper<>();
        if("userPerId".equals(type)){
            UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectOne(new EntityWrapper<UserPersonInfoEntity>().eq("user_id", userId));
            wrapper.eq("user_per_id", userPersonInfoEntity.getUserPerId());
        }else if("userTeacherId".equals(type)){
            UserTeacherInfoEntity teacherInfoEntity = userTeacherInfoService.selectOne(new EntityWrapper<UserTeacherInfoEntity>().eq("user_id", userId));
            wrapper.eq("user_teacher_id", teacherInfoEntity.getUserTeacherId());
        }else if("entInfoId".equals(type)){
            EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectOne(new EntityWrapper<EntEnterpriseInfoEntity>().eq("user_id", userId));
            wrapper.eq("ent_info_id", entEnterpriseInfoEntity.getEntInfoId());
        }
        Page<EntPersonCooperationInfoEntity> page = this.selectPage(new Query<EntPersonCooperationInfoEntity>(params).getPage(), wrapper);
        if(page.getRecords().size() > 0){
            for (int i = 0; i < page.getRecords().size(); i++) {
                EntPersonCooperationInfoEntity entPersonCooperationInfoEntity = entPersonCooperationInfoService.selectById(page.getRecords().get(i).getProCooperationId());
                EntProjectCooperationInfoEntity entProjectCooperationInfoEntity = entProjectCooperationInfoService.selectById(entPersonCooperationInfoEntity.getProCooperationInfoId());
                EntProjectInfoEntity entProjectInfoEntity = entProjectInfoService.selectById(entProjectCooperationInfoEntity.getProInfoId());
                entPersonCooperationInfoEntity.setEntProjectCooperationInfo(entProjectCooperationInfoEntity);
                entPersonCooperationInfoEntity.setEntProjectInfo(entProjectInfoEntity);
                page.getRecords().set(i, entPersonCooperationInfoEntity);
            }
            for (int i = 0; i < page.getRecords().size(); i++) {
                if(!page.getRecords().get(i).getEntProjectCooperationInfo().getInApply().equals(params.get("inApply"))){
                    page.getRecords().remove(i);
                    --i;
                }
            }
        }
        return R.ok().put("page", new PageUtils(page));
    }

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"0", "userPerId"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public R queryMyCooperationPage(Map<String, Object> params, Long userId) {
        String type = (String) params.get("inType");
        String inApply = (String) params.get("inApply");
        EntityWrapper<EntPersonCooperationInfoEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("in_apply", "1");
        if("userPerId".equals(type)){
            UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectOne(new EntityWrapper<UserPersonInfoEntity>().eq("user_id", userId));
            wrapper.eq("user_per_id", userPersonInfoEntity.getUserPerId());
        }else if("userTeacherId".equals(type)){
            UserTeacherInfoEntity teacherInfoEntity = userTeacherInfoService.selectOne(new EntityWrapper<UserTeacherInfoEntity>().eq("user_id", userId));
            wrapper.eq("user_teacher_id", teacherInfoEntity.getUserTeacherId());
        }else if("entInfoId".equals(type)){
            EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectOne(new EntityWrapper<EntEnterpriseInfoEntity>().eq("user_id", userId));
            wrapper.eq("ent_info_id", entEnterpriseInfoEntity.getEntInfoId());
        }
        Page<EntPersonCooperationInfoEntity> page = this.selectPage(new Query<EntPersonCooperationInfoEntity>(params).getPage(), wrapper);
        if(page.getRecords().size() > 0){
            for (int i = 0; i < page.getRecords().size(); i++) {
                EntPersonCooperationInfoEntity entPersonCooperationInfoEntity = entPersonCooperationInfoService.selectById(page.getRecords().get(i).getProCooperationId());
                EntProjectCooperationInfoEntity entProjectCooperationInfoEntity = entProjectCooperationInfoService.selectById(entPersonCooperationInfoEntity.getProCooperationInfoId());
                EntProjectInfoEntity entProjectInfoEntity = entProjectInfoService.selectById(entProjectCooperationInfoEntity.getProInfoId());
                entPersonCooperationInfoEntity.setEntProjectCooperationInfo(entProjectCooperationInfoEntity);
                entPersonCooperationInfoEntity.setEntProjectInfo(entProjectInfoEntity);
                page.getRecords().set(i, entPersonCooperationInfoEntity);
            }
            for (int i = 0; i < page.getRecords().size(); i++) {
                if(!page.getRecords().get(i).getEntProjectCooperationInfo().getInApply().equals("2")){
                    page.getRecords().remove(i);
                    --i;
                }
            }
        }
        return R.ok().put("page", new PageUtils(page));
    }


    /**
     * 列表
     * @param params
     * @param falg 是否要剔除结束项目
     * @return
     */
    @Deprecated
    private Page<EntProjectCooperationInfoEntity> pages(Map<String, Object> params, boolean falg){
        EntityWrapper<EntProjectCooperationInfoEntity> wrapper = new EntityWrapper<EntProjectCooperationInfoEntity>();
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

        Page<EntProjectCooperationInfoEntity> page = entProjectCooperationInfoService.selectPage( new Query<EntProjectCooperationInfoEntity>(params).getPage(), wrapper);
        List<EntProjectCooperationInfoEntity> records = page.getRecords();
//        if(records != null && records.size() > 0){
//            if(!falg){
//                ArrayList<Long> proIds = new ArrayList<Long>();
//                for(int a=0; a<records.size(); a++){
//                    proIds.add(records.get(a).getProInfoId());
//                }
//                ArrayList<Long> dbProIds = entProjectCooperationInfoService.queryProInfoIdsByProInfoId(proIds);
//                if(dbProIds != null && dbProIds.size() > 0){
//                    proIds.removeAll(dbProIds);
//                }
//                for(int b=0; b<proIds.size(); b++){
//                    for(int c=0; c<records.size(); c++){
//                        if(proIds.get(b).longValue() == records.get(c).getProInfoId().longValue()){
//                            records.remove(c);
//                        }
//                    }
//                }
//            }
//        }
        invokeItem(type, records);
        return page;
    }

    /**
     * 数据项处理
     * @param type
     * @param records
     */
    private void invokeItem(String type, List<EntProjectCooperationInfoEntity> records) {
        if (records != null && records.size()>0) {
            for (int i = 0; i < records.size(); i++) {
                EntProjectCooperationInfoEntity projectCooperation = records.get(i);
                EntProjectInfoEntity projectInfo = entProjectInfoService.selectById(projectCooperation.getProInfoId());
                projectCooperation.setProjectInfo(projectInfo);
                if ("userPerId".equals(type)) { // 学生
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(projectCooperation.getUserPerId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                    userPersonInfoEntity.setSysUserEntity(sysUserEntity);
                    projectCooperation.setUserPersonInfo(userPersonInfoEntity);
                } else if ("userTeacherId".equals(type)) { // 教师
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(projectCooperation.getUserTeacherId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                    userTeacherInfoEntity.setSysUserEntity(sysUserEntity);
                    projectCooperation.setUserTeacherInfo(userTeacherInfoEntity);
                } else if ("entInfoId".equals(type)) { // 企业
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(projectCooperation.getEntInfoId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                    entEnterpriseInfoEntity.setSysUser(sysUserEntity);
                    projectCooperation.setEntEnterpriseInfo(entEnterpriseInfoEntity);
                }
            }
        }
    }

}
