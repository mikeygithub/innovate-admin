package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntStudentAchievementInfoDao;
import com.innovate.modules.enterprise.entity.EntStudentAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntStudentAchievementInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entStudentAchievementInfoService")
public class EntStudentAchievementInfoServiceImpl extends ServiceImpl<EntStudentAchievementInfoDao, EntStudentAchievementInfoEntity> implements EntStudentAchievementInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntStudentAchievementInfoEntity> page = this.selectPage(
                new Query<EntStudentAchievementInfoEntity>(params).getPage(),
                new EntityWrapper<EntStudentAchievementInfoEntity>()
        );

        return new PageUtils(page);
    }

}