package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.BaseInfoEntity;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:02
 * @Version 1.0
 */
public interface BaseInfoService extends IService<BaseInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
