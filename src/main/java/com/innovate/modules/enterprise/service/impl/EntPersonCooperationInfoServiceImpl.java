package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntPersonCooperationInfoDao;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;
import com.innovate.modules.enterprise.service.EntPersonCooperationInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("entPersonCooperationInfoService")
public class EntPersonCooperationInfoServiceImpl extends ServiceImpl<EntPersonCooperationInfoDao, EntPersonCooperationInfoEntity> implements EntPersonCooperationInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntPersonCooperationInfoEntity> page = this.selectPage(
                new Query<EntPersonCooperationInfoEntity>(params).getPage(),
                new EntityWrapper<EntPersonCooperationInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<EntPersonCooperationInfoEntity> queryPersonCooperationInfoByProCooperationInfoId(Long proCooperationInfoId) {
        return baseMapper.queryPersonCooperationInfoByProCooperationInfoId(proCooperationInfoId);
    }

}