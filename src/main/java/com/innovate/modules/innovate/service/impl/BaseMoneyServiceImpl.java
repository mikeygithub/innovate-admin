package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.common.BaseCalculate;
import com.innovate.modules.innovate.dao.BaseMoneyDao;
import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.entity.BaseMoneyEntity;
import com.innovate.modules.innovate.service.BaseInfoService;
import com.innovate.modules.innovate.service.BaseMoneyService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:30
 * @Version 1.0
 */
@Service
public class BaseMoneyServiceImpl extends ServiceImpl<BaseMoneyDao, BaseMoneyEntity>  implements BaseCalculate, BaseMoneyService {


    @Autowired
    private BaseInfoService baseInfoService;

    BaseMoneyServiceImpl() {
        BaseCalculateUtil.addObject(this);
    }
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseMoneyEntity> page = this.selectPage(
                new Query<BaseMoneyEntity>(params).getPage(),
                new EntityWrapper<BaseMoneyEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public Double queryMoney(Long baseId,Long moneyType) {
        return baseMapper.queryMoney(baseId,moneyType);
    }

    @Override
    public void calculate(Long baseId) {
        BaseInfoEntity baseInfoEntity = baseInfoService.selectById(baseId);
        //基地累计获得投资金额总数
        Double baseInvestMoney = this.queryMoney(baseId,2L);
        if (null == baseInvestMoney){
            baseInvestMoney = 0.0;
        }
        baseInfoEntity.setBaseInvestMoney(baseInvestMoney);

        //众创空间总收入
        Double baseAllInMoney = this.queryMoney(baseId,null);
        if (null == baseAllInMoney) {
            baseAllInMoney = 0.0;
        }

        baseInfoEntity.setBaseAllInMoney(baseAllInMoney);
        baseInfoService.updateAllColumnById(baseInfoEntity);
    }
}
