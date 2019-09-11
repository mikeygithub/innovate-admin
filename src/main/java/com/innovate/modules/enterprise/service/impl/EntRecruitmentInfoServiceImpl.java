package com.innovate.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.enterprise.dao.EntRecruitmentInfoDao;
import com.innovate.modules.enterprise.entity.EntRecruitmentInfoEntity;
import com.innovate.modules.enterprise.service.EntRecruitmentInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("entRecruitmentInfoService")
public class EntRecruitmentInfoServiceImpl extends ServiceImpl<EntRecruitmentInfoDao, EntRecruitmentInfoEntity> implements EntRecruitmentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EntRecruitmentInfoEntity> page = this.selectPage(
                new Query<EntRecruitmentInfoEntity>(params).getPage(),
                new EntityWrapper<EntRecruitmentInfoEntity>()
        );

        return new PageUtils(page);
    }

}