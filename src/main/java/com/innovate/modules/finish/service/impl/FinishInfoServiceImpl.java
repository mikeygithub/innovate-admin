package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.entity.FinishInfoEntity;
import com.innovate.modules.finish.dao.FinishInfoDao;
import com.innovate.modules.finish.service.FinishInfoService;
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
public class FinishInfoServiceImpl extends ServiceImpl<FinishInfoDao, FinishInfoEntity> implements FinishInfoService {

    @Override
    public FinishInfoEntity queryById(Long finishId) {
        return baseMapper.queryById(finishId);
    }

    @Override
    public Integer queryCountPage(Map<String, Object> params) {
        return baseMapper.queryCountPage(params);
    }

    @Override
    public List<FinishInfoEntity> queryPage(Map<String, Object> params) {
        return baseMapper.queryPage(params);
    }

    @Override
    public List<FinishInfoEntity> noPass(Map<String, Object> params) {
        return baseMapper.noPass(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
