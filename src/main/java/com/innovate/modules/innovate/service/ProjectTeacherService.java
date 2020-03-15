package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectTeacherEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:18
 * @Version 1.0
 */
public interface ProjectTeacherService extends IService<ProjectTeacherEntity> {

    List<ProjectTeacherEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
