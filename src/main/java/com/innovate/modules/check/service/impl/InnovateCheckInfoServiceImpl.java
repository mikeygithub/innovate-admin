package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckInfoDao;
import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;



@Service("innovateCheckInfoService")
public class InnovateCheckInfoServiceImpl extends ServiceImpl<InnovateCheckInfoDao, InnovateCheckInfoEntity> implements InnovateCheckInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateCheckInfoEntity> page = this.selectPage(
                new Query<InnovateCheckInfoEntity>(params).getPage(),
                new EntityWrapper<InnovateCheckInfoEntity>()
        );
        return new PageUtils(page);
    }

}