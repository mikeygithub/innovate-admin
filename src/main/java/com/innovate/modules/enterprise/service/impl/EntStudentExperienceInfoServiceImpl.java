package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntStudentExperienceInfoDao;
import com.innovate.modules.enterprise.entity.EntStudentExperienceInfoEntity;
import com.innovate.modules.enterprise.service.EntStudentExperienceInfoService;


@Service("entStudentExperienceInfoService")
public class EntStudentExperienceInfoServiceImpl extends ServiceImpl<EntStudentExperienceInfoDao, EntStudentExperienceInfoEntity> implements EntStudentExperienceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Object userId = params.get("userId");
        Object hasApply = params.get("hasApply");

        EntityWrapper<EntStudentExperienceInfoEntity> ew = new EntityWrapper<>();
        //  项目负责人查询
        if (userId!=null&&userId!="") ew.eq("user_per_id",Long.parseLong(userId.toString()));
        //1:未审批 2:已通过 3:未通过
        if (hasApply!=null)ew.eq("in_apply",Long.parseLong(hasApply.toString()));

        Page<EntStudentExperienceInfoEntity> page = this.selectPage(
                new Query<EntStudentExperienceInfoEntity>(params).getPage(), ew
        );
        return new PageUtils(page);
    }

}