package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;

import com.innovate.modules.innovate.dao.InnovateFileAskDao;
import com.innovate.modules.innovate.entity.InnovateFileAskEntity;
import com.innovate.modules.innovate.service.InnovateFileAskService;


@Service("innovateFileAskService")
public class InnovateFileAskServiceImpl extends ServiceImpl<InnovateFileAskDao, InnovateFileAskEntity> implements InnovateFileAskService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateFileAskEntity> page = this.selectPage(
                new Query<InnovateFileAskEntity>(params).getPage(),
                new EntityWrapper<InnovateFileAskEntity>()
        );
        return new PageUtils(page);
    }

}