package com.innovate.modules.match.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.match.entity.MatchTeacherEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:18
 * @Version 1.0
 */
public interface MatchTeacherService extends IService<MatchTeacherEntity> {

    List<MatchTeacherEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
