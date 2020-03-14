package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckAwardDao;
import com.innovate.modules.check.entity.InnovateCheckAwardEntity;
import com.innovate.modules.check.service.InnovateCheckAwardService;
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
public class InnovateCheckAwardServiceImpl extends ServiceImpl<InnovateCheckAwardDao, InnovateCheckAwardEntity> implements InnovateCheckAwardService {
    @Override
    public List<InnovateCheckAwardEntity> queryAll(Map<String, Object> params) {
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

    @Override
    public InnovateCheckAwardEntity findByAwardId(Long awardId) {
        return baseMapper.findByAwardId(awardId);
    }
}
