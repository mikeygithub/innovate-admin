package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.finish.dao.FinishApplyUpdateDao;
import com.innovate.modules.finish.entity.FinishApplyUpdateEntity;
import com.innovate.modules.finish.entity.FinishInfoEntity;
import com.innovate.modules.finish.service.FinishApplyUpdateService;
import com.innovate.modules.finish.service.FinishInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@Service
public class FinishApplyUpdateServiceImpl extends ServiceImpl<FinishApplyUpdateDao, FinishApplyUpdateEntity> implements FinishApplyUpdateService {

    @Autowired
    private FinishApplyUpdateService finishApplyUpdateService;
    @Autowired
    private FinishInfoService finishInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long finishId = Long.parseLong(params.get("finishId").toString());
        Page<FinishApplyUpdateEntity> page = this.selectPage(
                new Query<FinishApplyUpdateEntity>(params).getPage(),
                new EntityWrapper<FinishApplyUpdateEntity>()
                        .eq(finishId != null, "finish_id", finishId)
        );
        return new PageUtils(page);
    }

    @Override
    public void update(FinishApplyUpdateEntity finishApplyUpdateEntity) {
        Long finishId = finishApplyUpdateEntity.getFinishId();
        Long result = finishApplyUpdateEntity.getResult();
        FinishInfoEntity finishInfoEntity = finishInfoService.selectById(finishId);
        if (1 == result) {//0不通过 1通过
            finishInfoEntity.setIsUpdate(1L);
            finishInfoEntity.setApplyUpdate(0L);
        }
        if (2 == result || 0 == result) {//0不通过 1通过
            finishInfoEntity.setApplyUpdate(0L);
        }
        if (null == result) {//0不通过 1通过
            finishInfoEntity.setApplyUpdate(1L);
        }
        finishInfoService.updateAllColumnById(finishInfoEntity);
        finishApplyUpdateService.updateAllColumnById(finishApplyUpdateEntity);
    }

    @Override
    public void applyUpdate(Map<String, Object> params) {
        Long finishId = Long.parseLong(params.get("finishId").toString());
        String reason = params.get("reason").toString();
        FinishInfoEntity finishInfoEntity = finishInfoService.selectById(finishId);
        finishInfoEntity.setApplyUpdate(1L);
        finishInfoService.updateAllColumnById(finishInfoEntity);
        FinishApplyUpdateEntity finishApplyUpdateEntity = new FinishApplyUpdateEntity();
        finishApplyUpdateEntity.setFinishId(finishId);
        finishApplyUpdateEntity.setReason(reason);
        finishApplyUpdateService.insert(finishApplyUpdateEntity);
    }

    @Override
    public List<FinishApplyUpdateEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        List<FinishApplyUpdateEntity> finishApplyUpdateEntityList = baseMapper.queryAll(params);
        for (FinishApplyUpdateEntity finishApplyUpdateEntity : finishApplyUpdateEntityList) {
            baseMapper.remove(params);
        }
    }
}
