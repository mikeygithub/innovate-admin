package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntProjectInfoDao;
import com.innovate.modules.enterprise.entity.EntProjectInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entProjectInfoService")
public class EntProjectInfoServiceImpl extends ServiceImpl<EntProjectInfoDao, EntProjectInfoEntity> implements EntProjectInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntProjectInfoEntity> page = this.selectPage(
                new Query<EntProjectInfoEntity>(params).getPage(),
                new EntityWrapper<EntProjectInfoEntity>()
        );

        return new PageUtils(page);
    }

}