package com.innovate.modules.match.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.match.entity.MatchRetreatEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
public interface MatchRetreatService extends IService<MatchRetreatEntity> {

    @Transactional
    void updateRetreat(Map<String, Object> params);

    List<MatchRetreatEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
