package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntPatentInfoEntity;

import java.util.Map;

/**
 * 企业/教师知识产权信息表
 *
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
public interface EntPatentInfoService extends IService<EntPatentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

