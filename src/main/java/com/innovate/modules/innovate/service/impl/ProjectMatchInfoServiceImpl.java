package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.ProjectMatchInfoDao;
import com.innovate.modules.innovate.entity.ProjectMatchInfoEntity;
import com.innovate.modules.innovate.service.ProjectMatchInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:38
 * @Version 1.0
 */
@Service
public class ProjectMatchInfoServiceImpl extends ServiceImpl<ProjectMatchInfoDao, ProjectMatchInfoEntity> implements ProjectMatchInfoService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProjectMatchInfoEntity> page = this.selectPage(
                new Query<ProjectMatchInfoEntity>(params).getPage(),
                new EntityWrapper<ProjectMatchInfoEntity>()
        );
        return new PageUtils(page);
    }
}
