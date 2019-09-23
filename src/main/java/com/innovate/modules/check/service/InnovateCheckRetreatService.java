package com.innovate.modules.check.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.check.entity.InnovateCheckRetreatEntity;

import java.util.List;
import java.util.Map;

/**
 * 中期检查回退
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
public interface InnovateCheckRetreatService extends IService<InnovateCheckRetreatEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void retreat(Map<String, Object> params);

    List<InnovateCheckRetreatEntity> query(Map<String, Object> params);
}

