package com.innovate.modules.finish.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.finish.entity.FinishReviewEntity;
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
public interface FinishReviewService extends IService<FinishReviewEntity> {

    //查询分数
    FinishReviewEntity queryScore(Map<String, Object> params);
    //评分
    @Transactional
    void score(FinishReviewEntity finishReviewEntity);
    //绑定用户
    @Transactional
    void reviewUser(Map<String, Object> params);

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

    /**
     * 查询未评分
     * @param params
     * @return
     */
    PageUtils unReview(Map<String, Object> params);
}
