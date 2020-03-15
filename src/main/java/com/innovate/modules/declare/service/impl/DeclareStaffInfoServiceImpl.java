package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.declare.dao.DeclareStaffInfoDao;
import com.innovate.modules.declare.entity.DeclareStaffInfoEntity;
import com.innovate.modules.declare.service.DeclareStaffInfoService;
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
public class DeclareStaffInfoServiceImpl extends ServiceImpl<DeclareStaffInfoDao, DeclareStaffInfoEntity> implements DeclareStaffInfoService {

    @Override
    public List<DeclareStaffInfoEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Long queryStaffNum(Map<String, Object> params) {
        return baseMapper.queryStaffNum(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
