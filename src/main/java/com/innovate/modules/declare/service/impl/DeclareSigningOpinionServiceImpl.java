package com.innovate.modules.declare.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.declare.dao.DeclareSigningOpinionDao;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.entity.DeclareSigningOpinionEntity;
import com.innovate.modules.declare.service.DeclareApplyService;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.declare.service.DeclareSigningOpinionService;
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
public class DeclareSigningOpinionServiceImpl extends ServiceImpl<DeclareSigningOpinionDao, DeclareSigningOpinionEntity> implements DeclareSigningOpinionService {

    @Autowired
    private DeclareApplyService declareApplyService;

    /**
     * 添加签署意见
     * @param params
     */
    @Override
    public void addSigningOpinion(Map<String, Object> params) {

        DeclareSigningOpinionEntity declareSigningOpinionEntity = new DeclareSigningOpinionEntity();

        declareSigningOpinionEntity.setSigningOpinion(params.get("sighingOpinion").toString());

        declareSigningOpinionEntity.setDeclareId(Long.parseLong(params.get("declareId").toString()));

        declareSigningOpinionEntity.setUserId(Long.parseLong(params.get("userId").toString()));

        declareSigningOpinionEntity.setSigningOpinionTime(new Date());

        this.insert(declareSigningOpinionEntity);

        declareApplyService.apply(params);

    }

    /**
     * 查询签署意见
     * @param params
     * @return
     */
    @Override
    public DeclareSigningOpinionEntity queryDeclareSigningOpinionByDeclareId(Map<String, Object> params) {

        return baseMapper.queryDeclareSigningOpinionByDeclareId(Long.parseLong(params.get("declareId").toString()));


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
