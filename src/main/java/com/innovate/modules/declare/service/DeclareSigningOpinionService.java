package com.innovate.modules.declare.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.declare.entity.DeclareSigningOpinionEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Program: innovate-admin
 * @Author: 麦奇
 * @Email： 1625017540@qq.com
 * @Create: 2019-02-05 22:54
 * @Describe： 签署意见
 **/
public interface DeclareSigningOpinionService extends IService<DeclareSigningOpinionEntity> {

    /**
     * 添加签署意见
     * @param params
     */
    @Transactional
    void addSigningOpinion(Map<String, Object> params);

    /**
     * 查询签署意见
     * @param params
     * @return
     */
     DeclareSigningOpinionEntity queryDeclareSigningOpinionByDeclareId(Map<String, Object> params);

    /**签署意见
     * 删除
     * @param params
     */
    void remove(Map<String, Object> params);

}
