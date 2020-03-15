package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.BaseInfoDao;
import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.service.BaseInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:27
 * @Version 1.0
 */
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoDao, BaseInfoEntity> implements BaseInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseInfoEntity> page = this.selectPage(
                new Query<BaseInfoEntity>(params).getPage(),
                new EntityWrapper<BaseInfoEntity>()
        );
        return new PageUtils(page);
    }
}
