package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntTeacherAchievementInfoDao;
import com.innovate.modules.enterprise.entity.EntTeacherAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntTeacherAchievementInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entTeacherAchievementInfoService")
public class EntTeacherAchievementInfoServiceImpl extends ServiceImpl<EntTeacherAchievementInfoDao, EntTeacherAchievementInfoEntity> implements EntTeacherAchievementInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntTeacherAchievementInfoEntity> page = this.selectPage(
                new Query<EntTeacherAchievementInfoEntity>(params).getPage(),
                new EntityWrapper<EntTeacherAchievementInfoEntity>()
        );

        return new PageUtils(page);
    }

}