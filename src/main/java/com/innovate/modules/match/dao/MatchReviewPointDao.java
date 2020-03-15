package com.innovate.modules.match.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.match.entity.MatchReviewPointEntity;
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
public interface MatchReviewPointDao extends BaseMapper<MatchReviewPointEntity> {

//    List<MatchReviewEntity> queryAll(Map<String, Object> params);
//
//    void remove(Map<String, Object> params);
//    /**
//     * 统计未评分的个数
//     */
//    Long queryCount(Map<String, Object> params);
//    /**
//     * 计算平均分
//     */
//    Double queryScoreAvg(Map<String, Object> params);
//    //查询分数
//    MatchReviewEntity queryScore(Map<String, Object> params);
    //通过类型查找评审要点
    List<MatchReviewPointEntity> queryAllReviewPointByReviewType(Map<String, Object> params);
    //获取所有
    List<MatchReviewPointEntity> queryAllReviewPoint();
}
