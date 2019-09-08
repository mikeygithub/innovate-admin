package com.innovate.modules.match.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.match.entity.MatchReviewPointEntity;

import java.util.List;
import java.util.Map;

/**
 * @Program: innovate-admin
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-28 19:39
 * @Describe：
 **/
public interface MatchReviewPointService extends IService<MatchReviewPointEntity> {
    //通过类型查找评审要点
    List<MatchReviewPointEntity> queryAllReviewPointByReviewType(Map<String, Object> params);
    //获取所有
    List<MatchReviewPointEntity> queryAllReviewPoint();
}
