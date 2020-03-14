package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.BaseMoneyEntity;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:03
 * @Version 1.0
 */
public interface BaseMoneyService extends IService<BaseMoneyEntity> {
    PageUtils queryPage(Map<String, Object> params);
    Double queryMoney(Long baseId,Long moneyType);
}
