package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.innovate.dao.ProjectLegalInfoDao;
import com.innovate.modules.innovate.entity.ProjectLegalInfoEntity;
import com.innovate.modules.innovate.service.ProjectLegalInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:法人
 **/
@Service
public class ProjectLegalInfoServiceImpl extends ServiceImpl<ProjectLegalInfoDao, ProjectLegalInfoEntity> implements ProjectLegalInfoService {

    @Override
    public List<ProjectLegalInfoEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
