package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.dao.FinishStaffInfoDao;
import com.innovate.modules.finish.entity.FinishStaffInfoEntity;
import com.innovate.modules.finish.service.FinishStaffInfoService;
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
public class FinishStaffInfoServiceImpl extends ServiceImpl<FinishStaffInfoDao, FinishStaffInfoEntity> implements FinishStaffInfoService {

    @Override
    public List<FinishStaffInfoEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
