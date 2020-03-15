package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntTeacherExperienceInfoDao;
import com.innovate.modules.enterprise.entity.EntTeacherExperienceInfoEntity;
import com.innovate.modules.enterprise.service.EntTeacherExperienceInfoService;


@Service("entTeacherExperienceInfoService")
public class EntTeacherExperienceInfoServiceImpl extends ServiceImpl<EntTeacherExperienceInfoDao, EntTeacherExperienceInfoEntity> implements EntTeacherExperienceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Object userId = params.get("userId");
        Object hasApply = params.get("hasApply");
        Object key = params.get("key");

        EntityWrapper ew = new EntityWrapper<>();
        ew.setEntity(new EntTeacherExperienceInfoEntity());
        //教师查询
        if (userId!=null&&userId!="") ew.eq("user_teacher_id",Long.parseLong(userId.toString()));
        //1:未审批 2:已通过 3:未通过
        if (hasApply!=null)ew.eq("in_apply",Long.parseLong(hasApply.toString()));
        //查询
        if (key!=null&&key.toString()!="")ew.like("tea_experience_content",key.toString(), SqlLike.DEFAULT);


        Page<EntTeacherExperienceInfoEntity> page = this.selectPage(
                new Query<EntTeacherExperienceInfoEntity>(params).getPage(), ew
        );
        return new PageUtils(page);
    }

}