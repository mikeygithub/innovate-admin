package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchInfoDao;
import com.innovate.modules.match.entity.MatchInfoEntity;
import com.innovate.modules.match.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:35
 * @Version 1.0
 */
@Service
public class MatchInfoServiceImpl extends ServiceImpl<MatchInfoDao, MatchInfoEntity> implements MatchInfoService {

    @Override
    public MatchInfoEntity queryById(Long matchId) {
        return baseMapper.queryById(matchId);
    }

    @Override
    public List<MatchInfoEntity> queryByName(Map<String, Object> params) {
        return baseMapper.queryByName(params);
    }

    @Override
    public Integer queryCountPage(Map<String, Object> params) {
        return baseMapper.queryCountPage(params);
    }

    @Override
    public List<MatchInfoEntity> queryPage(Map<String, Object> params) {
        return baseMapper.queryPage(params);
    }

    @Override
    public List<MatchInfoEntity> noPass(Map<String, Object> params) {
        return baseMapper.noPass(params);
    }

    @Override
    public Long queryProjectNum(Map<String, Object> params) {
        return baseMapper.queryProjectNum(params);
    }

    @Override
    public Long queryUserNum(Map<String, Object> params) {
        return baseMapper.queryUserNum(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
