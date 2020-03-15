package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.InnovateSchoolDao;
import com.innovate.modules.innovate.dao.InnovateSubjectDao;
import com.innovate.modules.innovate.entity.InnovateSchoolEntity;
import com.innovate.modules.innovate.entity.InnovateSubjectEntity;
import com.innovate.modules.innovate.service.InnovateSchoolService;
import com.innovate.modules.innovate.service.InnovateSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Service
public class InnovateSubjectServiceImpl extends ServiceImpl<InnovateSubjectDao, InnovateSubjectEntity> implements InnovateSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<InnovateSubjectEntity> page = this.selectPage(
//                new Query<InnovateSubjectEntity>(params).getPage(),
//                new EntityWrapper<InnovateSubjectEntity>()
//        );
//        return new PageUtils(page);

        String key = params.get("key").toString();

        EntityWrapper<InnovateSubjectEntity> ew = new EntityWrapper<>();

        if (key!=null&&key!="")ew.like("subject_name",key, SqlLike.DEFAULT);

        Page<InnovateSubjectEntity> page = this.selectPage(
                new Query<InnovateSubjectEntity>(params).getPage(),
                ew
        );
        return new PageUtils(page);
    }

    @Override
    public List<InnovateSubjectEntity> queryAll() {
        return baseMapper.queryAll();
    }
}
