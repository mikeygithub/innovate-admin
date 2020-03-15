package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.finish.dao.FinishInExpertDao;
import com.innovate.modules.finish.entity.FinishInExpertEntity;
import com.innovate.modules.finish.service.FinishInExpertService;


@Service("innovateFinishInExpertService")
public class FinishInExpertServiceImpl extends ServiceImpl<FinishInExpertDao, FinishInExpertEntity> implements FinishInExpertService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FinishInExpertEntity> page = this.selectPage(
                new Query<FinishInExpertEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FinishInExpertEntity> findByFinishExpertCollectId(Integer expertCollectId) {

        EntityWrapper<FinishInExpertEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(new FinishInExpertEntity());

        entityWrapper.eq("expert_collect_id",expertCollectId);
        entityWrapper.eq("is_del",0);

        List<FinishInExpertEntity> finishInExpertEntities = this.selectList(entityWrapper);

        for (FinishInExpertEntity e:finishInExpertEntities){
            e.setSysUserEntity(sysUserService.selectById(e.getExpertUserId()));
        }
        return finishInExpertEntities;
    }
}