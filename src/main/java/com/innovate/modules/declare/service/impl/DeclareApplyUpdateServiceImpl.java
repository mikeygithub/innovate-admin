package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.declare.dao.DeclareApplyUpdateDao;
import com.innovate.modules.declare.entity.DeclareApplyUpdateEntity;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareApplyUpdateService;
import com.innovate.modules.declare.service.DeclareInfoService;
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
public class DeclareApplyUpdateServiceImpl extends ServiceImpl<DeclareApplyUpdateDao, DeclareApplyUpdateEntity> implements DeclareApplyUpdateService {

    @Autowired
    private DeclareApplyUpdateService declareApplyUpdateService;
    @Autowired
    private DeclareInfoService declareInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long declareId = Long.parseLong(params.get("declareId").toString());
        Page<DeclareApplyUpdateEntity> page = this.selectPage(
                new Query<DeclareApplyUpdateEntity>(params).getPage(),
                new EntityWrapper<DeclareApplyUpdateEntity>()
                        .eq(declareId != null, "declare_id", declareId)
        );
        return new PageUtils(page);
    }

    @Override
    public void update(DeclareApplyUpdateEntity declareApplyUpdateEntity) {
        Long declareId = declareApplyUpdateEntity.getDeclareId();
        Long result = declareApplyUpdateEntity.getResult();
        DeclareInfoEntity declareInfoEntity = declareInfoService.selectById(declareId);
        if (1 == result) {//0不通过 1通过
            declareInfoEntity.setIsUpdate(1L);
            declareInfoEntity.setApplyUpdate(0L);
        }
        if (2 == result || 0 == result) {//0不通过 1通过
            declareInfoEntity.setApplyUpdate(0L);
        }
        if (null == result) {//0不通过 1通过
            declareInfoEntity.setApplyUpdate(1L);
        }
        declareInfoService.updateAllColumnById(declareInfoEntity);
        declareApplyUpdateService.updateAllColumnById(declareApplyUpdateEntity);
    }

    @Override
    public void applyUpdate(Map<String, Object> params) {
        Long declareId = Long.parseLong(params.get("declareId").toString());
        String reason = params.get("reason").toString();
        DeclareInfoEntity declareInfoEntity = declareInfoService.selectById(declareId);
        declareInfoEntity.setApplyUpdate(1L);
        declareInfoService.updateAllColumnById(declareInfoEntity);
        DeclareApplyUpdateEntity declareApplyUpdateEntity = new DeclareApplyUpdateEntity();
        declareApplyUpdateEntity.setDeclareId(declareId);
        declareApplyUpdateEntity.setReason(reason);
        declareApplyUpdateService.insert(declareApplyUpdateEntity);
    }

    @Override
    public List<DeclareApplyUpdateEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        List<DeclareApplyUpdateEntity> declareApplyUpdateEntityList = baseMapper.queryAll(params);
        for (DeclareApplyUpdateEntity declareApplyUpdateEntity : declareApplyUpdateEntityList) {
            baseMapper.remove(params);
        }
    }
}
