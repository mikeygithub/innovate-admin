package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.Constant;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntProjectInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @DefaultValue(targetType = java.lang.String.class, index = 1, key = "",defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @Override
    public R queryProjectPersonCooperationInfo(Long proInfoId, String inType) {
        EntProjectInfoEntity project = baseMapper.selectById(proInfoId);
        if(project == null){ return R.error();}
        //entProjectCooperationInfoService.q
        project.getProInfoId();
        if("userPerId".equals(inType)){ // 学生

        }else if("userTeacherId".equals(inType)){ // 教师

        }else if ("entInfoId".equals(inType)){ // 企业

        }
        return null;
    }

}