package com.innovate.modules.enterprise.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntStudentExperienceInfoEntity;

import java.util.Map;

/**
 * 学生工作/项目经历信息表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2019-10-11 20:55:46
 */
public interface EntStudentExperienceInfoService extends IService<EntStudentExperienceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

