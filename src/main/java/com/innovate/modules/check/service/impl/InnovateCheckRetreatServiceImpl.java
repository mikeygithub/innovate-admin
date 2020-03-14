package com.innovate.modules.check.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.check.dao.InnovateCheckRetreatDao;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.entity.InnovateCheckRetreatEntity;
import com.innovate.modules.check.service.InnovateCheckInfoService;
import com.innovate.modules.check.service.InnovateCheckRetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;


@Service("innovateCheckRetreatService")
public class InnovateCheckRetreatServiceImpl extends ServiceImpl<InnovateCheckRetreatDao, InnovateCheckRetreatEntity> implements InnovateCheckRetreatService {

    @Autowired
    private InnovateCheckInfoService innovateCheckInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InnovateCheckRetreatEntity> page = this.selectPage(
                new Query<InnovateCheckRetreatEntity>(params).getPage(),
                new EntityWrapper<InnovateCheckRetreatEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void retreat(Map<String, Object> params) {

        Long checkId = Long.parseLong(params.get("checkId").toString());
        String apply = params.get("apply").toString();
        Long userId = Long.parseLong(params.get("userId").toString());
        String retreatOption = params.get("retreatOption").toString();
        Integer applyStatus = Integer.parseInt(params.get("applyStatus").toString());


        InnovateCheckRetreatEntity innovateCheckRetreatEntity = new InnovateCheckRetreatEntity();

        innovateCheckRetreatEntity.setApply(apply);
        innovateCheckRetreatEntity.setApplyStatus(applyStatus);
        innovateCheckRetreatEntity.setUserId(userId);
        innovateCheckRetreatEntity.setRetreatOption(retreatOption);
        innovateCheckRetreatEntity.setCheckId(checkId);

        this.insert(innovateCheckRetreatEntity);

        InnovateCheckInfoEntity innovateCheckInfoEntity = innovateCheckInfoService.selectById(checkId);

        switch (apply){
            case "project_check_apply_status":
            innovateCheckInfoEntity.setCheckNoPass(1);
            break;
        }
        innovateCheckInfoService.updateAllColumnById(innovateCheckInfoEntity);
    }

    @Override
    public List<InnovateCheckRetreatEntity> query(Map<String, Object> params) {
        return baseMapper.queryByParams(params);
    }

}