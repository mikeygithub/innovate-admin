package com.innovate.modules.finish.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.finish.entity.FinishTeacherEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:18
 * @Version 1.0
 */
public interface FinishTeacherService extends IService<FinishTeacherEntity> {

    List<FinishTeacherEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
