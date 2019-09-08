package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchAwardDao;
import com.innovate.modules.match.entity.MatchAwardEntity;
import com.innovate.modules.match.service.MatchAwardService;
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
public class MatchAwardServiceImpl extends ServiceImpl<MatchAwardDao, MatchAwardEntity> implements MatchAwardService {
    @Override
    public List<MatchAwardEntity> queryAll(Map<String, Object> params) {
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
