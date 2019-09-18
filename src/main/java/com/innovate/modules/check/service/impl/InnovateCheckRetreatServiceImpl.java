package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckRetreatDao;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.entity.InnovateCheckRetreatEntity;
import com.innovate.modules.check.service.InnovateCheckRetreatService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;


@Service("innovateCheckRetreatService")
public class InnovateCheckRetreatServiceImpl extends ServiceImpl<InnovateCheckRetreatDao, InnovateCheckRetreatEntity> implements InnovateCheckRetreatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateCheckRetreatEntity> page = this.selectPage(
                new Query<InnovateCheckRetreatEntity>(params).getPage(),
                new EntityWrapper<InnovateCheckRetreatEntity>()
        );
        return new PageUtils(page);
    }

}