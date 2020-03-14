package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.BaseMoneyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:12
 * @Version 1.0
 */
@Mapper
public interface BaseMoneyDao extends BaseMapper<BaseMoneyEntity> {
    /**
     *    统计投资金额总数
     */
    Double queryMoney(Long baseId,Long moneyType);

}
