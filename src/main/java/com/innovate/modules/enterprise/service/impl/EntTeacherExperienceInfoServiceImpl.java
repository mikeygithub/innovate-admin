package com.innovate.modules.enterprise.service.impl;

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
        Page<EntTeacherExperienceInfoEntity> page = this.selectPage(
                new Query<EntTeacherExperienceInfoEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

}