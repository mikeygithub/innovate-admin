package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.InnovateGradeDao;
import com.innovate.modules.innovate.dao.InnovateSchoolDao;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
import com.innovate.modules.innovate.entity.InnovateSchoolEntity;
import com.innovate.modules.innovate.service.InnovateGradeService;
import com.innovate.modules.innovate.service.InnovateSchoolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateSchoolServiceImpl extends ServiceImpl<InnovateSchoolDao, InnovateSchoolEntity> implements InnovateSchoolService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateSchoolEntity> page = this.selectPage(
                new Query<InnovateSchoolEntity>(params).getPage(),
                new EntityWrapper<InnovateSchoolEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<InnovateSchoolEntity> queryAll() {
        return baseMapper.queryAll();
    }
}
