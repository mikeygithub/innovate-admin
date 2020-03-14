package com.innovate.modules.check.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.check.entity.InnovateCheckAwardEntity;
import com.innovate.modules.declare.entity.DeclareAwardEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:58
 * @Version 1.0
 */
public interface InnovateCheckAwardService extends IService<InnovateCheckAwardEntity> {

    List<InnovateCheckAwardEntity> queryAll(Map<String, Object> params);

    //统计获奖数量
    Long queryAwardNum(Map<String, Object> params);

    //统计奖金数量
    Double queryAwardMoney(Map<String, Object> params);

    void remove(Map<String, Object> params);

    //findById
    InnovateCheckAwardEntity findByAwardId(Long awardId);
}
