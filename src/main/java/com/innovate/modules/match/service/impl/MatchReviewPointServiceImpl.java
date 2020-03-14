package com.innovate.modules.match.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.match.dao.MatchReviewPointDao;
import com.innovate.modules.match.entity.MatchReviewPointEntity;
import com.innovate.modules.match.service.MatchReviewPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Program: innovate-admin
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-28 19:43
 * @Describe：
 **/
@Service
public class MatchReviewPointServiceImpl extends ServiceImpl<MatchReviewPointDao, MatchReviewPointEntity> implements MatchReviewPointService {

    @Autowired
    private MatchReviewPointService matchReviewPointService;
    /**
     * 按照类型查找评分标准
     * @param params
     * @return
     */
    @Override
    public List<MatchReviewPointEntity> queryAllReviewPointByReviewType(Map<String, Object> params) {
        return baseMapper.queryAllReviewPointByReviewType(params);
    }

    /**
     * 直接获取所以评分标准
     * @return
     */
    @Override
    public List<MatchReviewPointEntity> queryAllReviewPoint() {
        return matchReviewPointService.queryAllReviewPoint();
    }
}
