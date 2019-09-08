package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.ProjectTeacherDao;
import com.innovate.modules.innovate.entity.ProjectTeacherEntity;
import com.innovate.modules.innovate.service.ProjectTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:51
 * @Version 1.0
 */
@Service
public class ProjectTeacherServiceImpl extends ServiceImpl<ProjectTeacherDao, ProjectTeacherEntity> implements ProjectTeacherService {

    @Override
    public List<ProjectTeacherEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
