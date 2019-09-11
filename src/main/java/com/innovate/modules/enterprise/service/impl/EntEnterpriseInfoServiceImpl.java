package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntEnterpriseInfoDao;
import com.innovate.modules.enterprise.entity.EntEnterpriseInfoEntity;
import com.innovate.modules.enterprise.service.EntEnterpriseInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entEnterpriseInfoService")
public class EntEnterpriseInfoServiceImpl extends ServiceImpl<EntEnterpriseInfoDao, EntEnterpriseInfoEntity> implements EntEnterpriseInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntEnterpriseInfoEntity> page = this.selectPage(
                new Query<EntEnterpriseInfoEntity>(params).getPage(),
                new EntityWrapper<EntEnterpriseInfoEntity>()
        );

        return new PageUtils(page);
    }

}