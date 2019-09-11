package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntPersonCooperationInfoEntity;

import java.util.Map;

/**
 * 项目合作人表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
public interface EntPersonCooperationInfoService extends IService<EntPersonCooperationInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

