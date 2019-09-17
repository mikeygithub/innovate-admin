package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.InnovateSubjectDao;
import com.innovate.modules.innovate.dao.InnovateTitleDao;
import com.innovate.modules.innovate.entity.InnovateSubjectEntity;
import com.innovate.modules.innovate.entity.InnovateTitleEntity;
import com.innovate.modules.innovate.service.InnovateSubjectService;
import com.innovate.modules.innovate.service.InnovateTitleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateTitleServiceImpl extends ServiceImpl<InnovateTitleDao, InnovateTitleEntity> implements InnovateTitleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateTitleEntity> page = this.selectPage(
                new Query<InnovateTitleEntity>(params).getPage(),
                new EntityWrapper<InnovateTitleEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<InnovateTitleEntity> queryTitle(Map<String, Object> params) {
        return baseMapper.queryTitle(params);
    }

    @Override
    public List<InnovateTitleEntity> queryAll() {
        return baseMapper.queryAll();
    }
}
