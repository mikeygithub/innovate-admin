package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntPatentInfoDao;
import com.innovate.modules.enterprise.entity.EntPatentInfoEntity;
import com.innovate.modules.enterprise.service.EntPatentInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entPatentInfoService")
public class EntPatentInfoServiceImpl extends ServiceImpl<EntPatentInfoDao, EntPatentInfoEntity> implements EntPatentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntPatentInfoEntity> page = this.selectPage(
                new Query<EntPatentInfoEntity>(params).getPage(),
                new EntityWrapper<EntPatentInfoEntity>()
        );

        return new PageUtils(page);
    }

}