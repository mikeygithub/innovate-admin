package com.innovate.modules.check.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.check.entity.InnovateCheckInfoEntity;
import com.innovate.modules.check.entity.InnovateCheckInfoModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 中期检查表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
public interface InnovateCheckInfoService extends IService<InnovateCheckInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveByDeclareBatchIds(List<Long> checkIds);

    void apply(Map<String, Object> params);

    void save(InnovateCheckInfoModel innovateCheckInfoModel);

    void saveByTime(Map<String, Object> params);
}

