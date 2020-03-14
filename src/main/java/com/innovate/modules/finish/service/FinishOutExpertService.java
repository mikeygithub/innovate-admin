package com.innovate.modules.finish.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.finish.entity.FinishOutExpertEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Mikey
 * @email biaogejiushibiao@outlook.com
 * @date 2019-10-16 22:02:23
 */
public interface FinishOutExpertService extends IService<FinishOutExpertEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FinishOutExpertEntity> findByFinishExpertCollectId(Integer expertCollectId);
}

