package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntProjectAchievementInfoDao;
import com.innovate.modules.enterprise.entity.EntProjectAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectAchievementInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entProjectAchievementInfoService")
public class EntProjectAchievementInfoServiceImpl extends ServiceImpl<EntProjectAchievementInfoDao, EntProjectAchievementInfoEntity> implements EntProjectAchievementInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntProjectAchievementInfoEntity> page = this.selectPage(
                new Query<EntProjectAchievementInfoEntity>(params).getPage(),
                new EntityWrapper<EntProjectAchievementInfoEntity>()
        );

        return new PageUtils(page);
    }

}