package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.finish.dao.FinishOutExpertDao;
import com.innovate.modules.finish.entity.FinishOutExpertEntity;
import com.innovate.modules.finish.service.FinishOutExpertService;


@Service("innovateFinishOutExpertService")
public class FinishOutExpertServiceImpl extends ServiceImpl<FinishOutExpertDao, FinishOutExpertEntity> implements FinishOutExpertService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FinishOutExpertEntity> page = this.selectPage(
                new Query<FinishOutExpertEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FinishOutExpertEntity> findByFinishExpertCollectId(Integer expertCollectId) {
        EntityWrapper<FinishOutExpertEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new FinishOutExpertEntity());

        entityWrapper.eq("expert_collect_id",expertCollectId);
        entityWrapper.eq("is_del",0);

        return this.selectList(entityWrapper);
    }

}