package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchStaffInfoDao;
import com.innovate.modules.match.entity.MatchStaffInfoEntity;
import com.innovate.modules.match.service.MatchStaffInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:43
 * @Version 1.0
 */
@Service
public class MatchStaffInfoServiceImpl extends ServiceImpl<MatchStaffInfoDao, MatchStaffInfoEntity> implements MatchStaffInfoService {

    @Override
    public List<MatchStaffInfoEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
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
