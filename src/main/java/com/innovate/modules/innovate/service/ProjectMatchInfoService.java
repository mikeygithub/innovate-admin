package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectMatchInfoEntity;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:12
 * @Version 1.0
 */
public interface ProjectMatchInfoService extends IService<ProjectMatchInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
