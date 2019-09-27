package com.innovate.modules.check.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.check.entity.InnovateCheckAwardEntity;
import com.innovate.modules.declare.entity.DeclareAwardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:29
 * @Version 1.0
 */
@Mapper
public interface InnovateCheckAwardDao extends BaseMapper<InnovateCheckAwardEntity> {

    List<InnovateCheckAwardEntity> queryAll(Map<String, Object> params);

    //统计获奖数量
    Long queryAwardNum(Map<String, Object> params);

    //统计奖金数量
    Double queryAwardMoney(Map<String, Object> params);

    //findById
    InnovateCheckAwardEntity findByAwardId(Long awardId);

    void remove(Map<String, Object> params);
}
