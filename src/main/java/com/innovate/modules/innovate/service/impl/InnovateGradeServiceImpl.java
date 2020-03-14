package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.InnovateGradeDao;
import com.innovate.modules.innovate.entity.InnovateGradeEntity;
import com.innovate.modules.innovate.service.InnovateGradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateGradeServiceImpl extends ServiceImpl<InnovateGradeDao, InnovateGradeEntity> implements InnovateGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateGradeEntity> page = this.selectPage(
                new Query<InnovateGradeEntity>(params).getPage(),
                new EntityWrapper<InnovateGradeEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<InnovateGradeEntity> queryAllGrade() {
        return baseMapper.queryAllGrade();
    }
}
