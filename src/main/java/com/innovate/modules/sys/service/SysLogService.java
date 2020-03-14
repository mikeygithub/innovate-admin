package com.innovate.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
