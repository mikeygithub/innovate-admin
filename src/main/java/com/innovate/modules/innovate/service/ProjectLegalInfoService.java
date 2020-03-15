package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.innovate.entity.ProjectLegalInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:法人
 **/
public interface ProjectLegalInfoService extends IService<ProjectLegalInfoEntity> {

    List<ProjectLegalInfoEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
