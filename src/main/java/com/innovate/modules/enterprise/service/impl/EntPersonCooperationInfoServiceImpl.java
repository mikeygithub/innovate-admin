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

    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply", "inType"}, defValue = {"0", "userPerId"}, defValueEnum = {DefValueEnum.STRING, DefValueEnum.STRING})
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<EntPersonCooperationInfoEntity> page = this.selectPage(
//                new Query<EntPersonCooperationInfoEntity>(params).getPage(),
//                new EntityWrapper<EntPersonCooperationInfoEntity>()
//        );
        EntityWrapper<EntProjectInfoEntity> wrapper = new EntityWrapper<EntProjectInfoEntity>();
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
        Page<EntProjectInfoEntity> page = entProjectInfoService.selectPage( new Query<EntProjectInfoEntity>(params).getPage(), wrapper);
        List<EntProjectInfoEntity> records = page.getRecords();
        if(records != null && records.size() > 0){
            for(int i=0; i<records.size(); i++){
                EntProjectInfoEntity project = records.get(i);
                if("userPerId".equals(type)){ // 学生
                    UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(project.getUserPerId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userPersonInfoEntity.getUserId());
                    project.setUserPersonInfo(userPersonInfoEntity);
                    project.setSysUser(sysUserEntity);
                }else if("userTeacherId".equals(type)){ // 教师
                    UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(project.getUserTeacherId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(userTeacherInfoEntity.getUserId());
                    project.setSysUser(sysUserEntity);
                    project.setUserTeacherInfo(userTeacherInfoEntity);
                }else if ("entInfoId".equals(type)){ // 企业
                    EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(project.getEntInfoId());
                    SysUserEntity sysUserEntity = sysUserService.selectById(entEnterpriseInfoEntity.getUserId());
                    project.setSysUser(sysUserEntity);
                    project.setEntEnterpriseInfo(entEnterpriseInfoEntity);
                }
            }
        }
        return new PageUtils(page);
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
        List<EntPersonCooperationInfoEntity> list = queryPersonCooperationInfoByProCooperationInfoIdAndApply(entity.getProCooperationInfoId(), "1");
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
        List<Long> roles = sysUserRoleService.queryRoleIdList(userId);
        if(roles != null && roles.size() > 0){
            for(int i = 0; i < roles.size(); i++){
                Long aLong = roles.get(i);
                Long aLong1 = roleMap.get(aLong);
                if(aLong1 != null && aLong1 == 11L){ // 学生
                    Long userPerId = userPerInfoService.queryUserPerIdByUserId(userId);
                    person.setUserPerId(userPerId);
                    break;
                }else if (aLong1 != null && aLong1 == 12L){ // 教师
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

    @Override
    public R updatePersonCooperation(Map<String, Object> params) {
        if(params.get("proCooperationId") != null){
            baseMapper.updatePersonCooperation(Long.valueOf(params.get("proCooperationId").toString()), "pro_cooperation_id", "1");
        }else if(params.get("proCooperationInfoId") != null){
            baseMapper.updatePersonCooperation(Long.valueOf(params.get("proCooperationInfoId").toString()), "pro_cooperation_info_id", "1");
        }
        return R.ok();
    }

}