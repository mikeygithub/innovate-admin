package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntStudentExperienceInfoDao;
import com.innovate.modules.enterprise.entity.EntStudentExperienceInfoEntity;
import com.innovate.modules.enterprise.service.EntStudentExperienceInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entStudentExperienceInfoService")
public class EntStudentExperienceInfoServiceImpl extends ServiceImpl<EntStudentExperienceInfoDao, EntStudentExperienceInfoEntity> implements EntStudentExperienceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntStudentExperienceInfoEntity> page = this.selectPage(
                new Query<EntStudentExperienceInfoEntity>(params).getPage(),
                new EntityWrapper<EntStudentExperienceInfoEntity>()
        );

        return new PageUtils(page);
    }

}