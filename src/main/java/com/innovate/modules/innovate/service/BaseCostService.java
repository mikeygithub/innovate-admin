package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.BaseCostEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:04
 * @Version 1.0
 */
public interface BaseCostService extends IService<BaseCostEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<BaseCostEntity> list(Long baseId);
}
