package com.innovate.modules.match.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.match.entity.MatchApplyUpdateEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
public interface MatchApplyUpdateService extends IService<MatchApplyUpdateEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void applyUpdate(Map<String, Object> params);

    List<MatchApplyUpdateEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

    @Transactional
    void update(MatchApplyUpdateEntity matchApplyUpdateEntity);

}
