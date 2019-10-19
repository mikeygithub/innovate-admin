package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.common.utils.R;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.dao.EntPersonCooperationInfoDao;
import com.innovate.modules.enterprise.dao.EntProjectCooperationInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.enums.DefValueEnum;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.enterprise.service.EntPersonCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}