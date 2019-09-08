package com.innovate.modules.match.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.match.entity.MatchEventEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
public interface MatchEventService extends IService<MatchEventEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<MatchEventEntity> queryAllEvent();
    @Transactional
    void total(Map<String, Object> params);
    MatchEventEntity queryByEventId(Map<String, Object> params);
}
