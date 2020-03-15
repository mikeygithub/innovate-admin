package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectAttachEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:41
 * @Version 1.0
 */
public interface ProjectAttachService extends IService<ProjectAttachEntity> {

    List<ProjectAttachEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
