package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.common.BaseCalculate;
import com.innovate.modules.innovate.dao.BaseCostDao;
import com.innovate.modules.innovate.entity.BaseCostEntity;
import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.service.BaseCostService;
import com.innovate.modules.innovate.service.BaseInfoService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import com.innovate.modules.innovate.utils.subtractionMonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:33
 * @Version 1.0
 */
@Service
public class BaseCostServiceImpl extends ServiceImpl<BaseCostDao, BaseCostEntity> implements BaseCostService, BaseCalculate {

    @Autowired
    private BaseInfoService baseInfoService;

    BaseCostServiceImpl() {
        BaseCalculateUtil.addObject(this);
    }
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseCostEntity> page = this.selectPage(
                new Query<BaseCostEntity>(params).getPage(),
                new EntityWrapper<BaseCostEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<BaseCostEntity> list(Long baseId) {
        return baseMapper.list(baseId);
    }

    @Override
    public void calculate(Long baseId) {
        BaseInfoEntity baseInfoEntity = baseInfoService.selectById(baseId);
        Double moneyAll = 0.0;
        Double money = 0.0;
        int month = 0;

        List<BaseCostEntity> entityList = this.list(baseId);
        if (null == entityList){
            moneyAll = 0.0;
        }else{
            for (BaseCostEntity costEntity:entityList){
                money =  costEntity.getPersonCost()+costEntity.getAreaCost()+costEntity.getManageCost()+costEntity.getAnotherCost();
                month = subtractionMonthUtil.subMonth(costEntity.getCostStartTime().toString(),costEntity.getCostEndTime().toString());
                money = money * month;
                moneyAll += money;
            }
        }
        baseInfoEntity.setBaseOperateCost(moneyAll);
        baseInfoService.updateAllColumnById(baseInfoEntity);
    }
}
