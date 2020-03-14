package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.declare.dao.DeclareTeacherDao;
import com.innovate.modules.declare.entity.DeclareTeacherEntity;
import com.innovate.modules.declare.service.DeclareTeacherService;
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
public class DeclareTeacherServiceImpl extends ServiceImpl<DeclareTeacherDao, DeclareTeacherEntity> implements DeclareTeacherService {

    @Override
    public List<DeclareTeacherEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Long queryTeacherNum(Map<String, Object> params) {
        return baseMapper.queryTeacherNum(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
