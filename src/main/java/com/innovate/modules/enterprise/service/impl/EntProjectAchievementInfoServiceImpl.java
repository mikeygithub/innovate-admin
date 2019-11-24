package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.annotation.LimitPage;
import com.innovate.common.enums.DefValueEnum;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.annotation.DefaultArrayValue;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.dao.EntProjectAchievementInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectAchievementInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import com.innovate.modules.enterprise.service.EntProjectAchievementInfoService;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
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

    @LimitPage(targetType = java.util.Map.class, name = "项目合作分页", index = 0, pageSize = 10,  currPage = 1)
    @DefaultValue(targetType = java.util.Map.class, index = 0, key = "inType", defValue = "userPerId", defValueEnum = DefValueEnum.STRING)
    @DefaultArrayValue(targetType = java.util.Map.class, index = 0, key = {"inApply"}, defValue = {"0"}, defValueEnum = {DefValueEnum.STRING})
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Integer pageSize = (Integer) params.get("pageSize");
        Integer currPage = (Integer) params.get("currPage");
        String inApply = (String) params.get("inApply");
        EntityWrapper<EntProjectCooperationInfoEntity> wrapper = new EntityWrapper<EntProjectCooperationInfoEntity>();
        List<EntProjectAchievementInfoEntity> list = new ArrayList<EntProjectAchievementInfoEntity>();
        if("userPerId".equals(params.get("inType"))){
            UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.selectById(getUserId());
            Long userPerId = userPersonInfoEntity.getUserPerId();
            list = baseMapper.queryProjectAchievementByUserPerId(userPerId, inApply);
        }
        if("userTeacherId".equals(params.get("inType"))){
            UserTeacherInfoEntity userTeacherInfoEntity = userTeacherInfoService.selectById(getUserId());
            Long userTeacherId = userTeacherInfoEntity.getUserTeacherId();
            list = baseMapper.queryProjectAchievementByUserTeacherId(userTeacherId, inApply);
        }
        if("entInfoId".equals(params.get("inType"))){
            EntEnterpriseInfoEntity entEnterpriseInfoEntity = entEnterpriseInfoService.selectById(getUserId());
            Long entInfoId = entEnterpriseInfoEntity.getEntInfoId();
            list = baseMapper.queryProjectAchievementByEntInfoId(entInfoId, inApply);
        }
        return new PageUtils(list, baseMapper.queryCountPage(params), pageSize, currPage);
    }

}