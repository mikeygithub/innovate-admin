package com.innovate.modules.finish.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.finish.entity.FinishReviewEntity;
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
public interface FinishReviewDao extends BaseMapper<FinishReviewEntity> {

    List<FinishReviewEntity> queryAll(Map<String, Object> params);

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
    FinishReviewEntity queryScore(Map<String, Object> params);
}
