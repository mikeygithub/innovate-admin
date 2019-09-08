package com.innovate.modules.declare.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.declare.entity.DeclareRetreatEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
public interface DeclareRetreatService extends IService<DeclareRetreatEntity> {

    @Transactional
    void updateRetreat(Map<String, Object> params);

    List<DeclareRetreatEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
