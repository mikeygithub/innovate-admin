package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.enterprise.dao.EntCoopeationAttachDao;
import com.innovate.modules.enterprise.entity.EntCoopeationAttachEntity;
import com.innovate.modules.enterprise.service.EntCoopeationAttachService;


@Service("entCoopeationAttachService")
public class EntCoopeationAttachServiceImpl extends ServiceImpl<EntCoopeationAttachDao, EntCoopeationAttachEntity> implements EntCoopeationAttachService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntCoopeationAttachEntity> page = this.selectPage(
                new Query<EntCoopeationAttachEntity>(params).getPage(),
                new EntityWrapper<EntCoopeationAttachEntity>()
        );

        return new PageUtils(page);
    }

}