package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntProjectCooperationInfoDao;
import com.innovate.modules.enterprise.entity.EntProjectCooperationInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entProjectCooperationInfoService")
public class EntProjectCooperationInfoServiceImpl extends ServiceImpl<EntProjectCooperationInfoDao, EntProjectCooperationInfoEntity> implements EntProjectCooperationInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntProjectCooperationInfoEntity> page = this.selectPage(
                new Query<EntProjectCooperationInfoEntity>(params).getPage(),
                new EntityWrapper<EntProjectCooperationInfoEntity>()
        );

        return new PageUtils(page);
    }

}