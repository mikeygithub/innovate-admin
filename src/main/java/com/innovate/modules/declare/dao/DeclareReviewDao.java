package com.innovate.modules.declare.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:38
 * @Version 1.0
 */
@Mapper
public interface DeclareReviewDao extends BaseMapper<DeclareReviewEntity> {

    List<DeclareReviewEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
    /**
     * 统计未评分的个数
     */
    Long queryCount(Map<String, Object> params);
    /**
     * 计算平均分
     */
    Double queryScoreAvg(Map<String, Object> params);
    //查询分数
    DeclareReviewEntity queryScore(Map<String, Object> params);
}
