package com.innovate.modules.finish.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.finish.dao.FinishSigningOpinionDao;
import com.innovate.modules.finish.entity.FinishSigningOpinionEntity;
import com.innovate.modules.finish.service.FinishSigningOpinionService;
import com.innovate.modules.finish.service.FinishApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Program: innovate-admin-19-25
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-05 23:12
 * @Describe：
 **/
@Service
public class FinishSigningOpinionServiceImpl extends ServiceImpl<FinishSigningOpinionDao, FinishSigningOpinionEntity> implements FinishSigningOpinionService {

    @Autowired
    private FinishApplyService finishApplyService;

    /**
     * 添加签署意见
     * @param params
     */
    @Override
    public void addSigningOpinion(Map<String, Object> params) {

        FinishSigningOpinionEntity finishSigningOpinionEntity = new FinishSigningOpinionEntity();

        finishSigningOpinionEntity.setSigningOpinion(params.get("sighingOpinion").toString());

        finishSigningOpinionEntity.setFinishId(Long.parseLong(params.get("finishId").toString()));

        finishSigningOpinionEntity.setUserId(Long.parseLong(params.get("userId").toString()));

        finishSigningOpinionEntity.setSigningOpinionTime(new Date());

        this.insert(finishSigningOpinionEntity);

        finishApplyService.apply(params);

    }

    /**
     * 查询签署意见
     * @param params
     * @return
     */
    @Override
    public FinishSigningOpinionEntity queryFinishSigningOpinionByFinishId(Map<String, Object> params) {

        return baseMapper.queryFinishSigningOpinionByFinishId(Long.parseLong(params.get("finishId").toString()));


    }

    /**
     * 删除签署意见
     * @param params
     */
    @Override
    public void remove(Map<String, Object> params) {

        this.deleteByMap(params);

    }
}
