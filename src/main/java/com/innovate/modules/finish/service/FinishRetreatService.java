package com.innovate.modules.finish.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.finish.entity.FinishRetreatEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
public interface FinishRetreatService extends IService<FinishRetreatEntity> {

    @Transactional
    void updateRetreat(Map<String, Object> params);

    List<FinishRetreatEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
