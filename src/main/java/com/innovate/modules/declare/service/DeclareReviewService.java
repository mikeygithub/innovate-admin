package com.innovate.modules.declare.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.declare.entity.DeclareReviewEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:42
 * @Version 1.0
 */
public interface DeclareReviewService extends IService<DeclareReviewEntity> {

    //查询分数
    DeclareReviewEntity queryScore(Map<String, Object> params);
    //评分
    @Transactional
    void score(DeclareReviewEntity declareReviewEntity);
    //绑定用户
    @Transactional
    void reviewUser(Map<String, Object> params);

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

    PageUtils unReview(Map<String, Object> params);
}
