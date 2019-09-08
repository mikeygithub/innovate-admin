package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.declare.dao.DeclareInfoDao;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareInfoService;
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
public class DeclareInfoServiceImpl extends ServiceImpl<DeclareInfoDao, DeclareInfoEntity> implements DeclareInfoService {

    @Override
    public DeclareInfoEntity queryById(Long declareId) {
        return baseMapper.queryById(declareId);
    }

    @Override
    public Integer queryCountPage(Map<String, Object> params) {
        return baseMapper.queryCountPage(params);
    }

    @Override
    public List<DeclareInfoEntity> queryPage(Map<String, Object> params) {
        return baseMapper.queryPage(params);
    }

    @Override
    public List<DeclareInfoEntity> noPass(Map<String, Object> params) {
        return baseMapper.noPass(params);
    }

    @Override
    public Long queryDeclareProjectNum(Map<String, Object> params) {
        return baseMapper.queryDeclareProjectNum(params);
    }

    @Override
    public Long queryNewProjectNum(Map<String, Object> params) {
        return baseMapper.queryNewProjectNum(params);
    }

    @Override
    public Long queryTrainProjectNum(Map<String, Object> params) {
        return baseMapper.queryTrainProjectNum(params);
    }

    @Override
    public Long queryPracticeProjectNum(Map<String, Object> params) {
        return baseMapper.queryPracticeProjectNum(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
