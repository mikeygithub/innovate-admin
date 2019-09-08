package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.declare.dao.DeclareAwardDao;
import com.innovate.modules.declare.entity.DeclareAwardEntity;
import com.innovate.modules.declare.service.DeclareAwardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:23
 * @Version 1.0
 */
@Service
public class DeclareAwardServiceImpl extends ServiceImpl<DeclareAwardDao, DeclareAwardEntity> implements DeclareAwardService {
    @Override
    public List<DeclareAwardEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Long queryAwardNum(Map<String, Object> params) {
        return baseMapper.queryAwardNum(params);
    }

    @Override
    public Double queryAwardMoney(Map<String, Object> params) {
        return baseMapper.queryAwardMoney(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
