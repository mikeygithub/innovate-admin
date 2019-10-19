package com.innovate.modules.finish.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.finish.entity.FinishExpertCollectEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mikey
 * @email biaogejiushibiao@outlook.com
 * @date 2019-10-16 22:02:23
 */
public interface FinishExpertCollectService extends IService<FinishExpertCollectEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateByProps(FinishExpertCollectEntity innovateFinishExpertCollect);
}

