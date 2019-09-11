package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntStudentExperienceInfoEntity;

import java.util.Map;

/**
 * 学生工作/项目经历信息表
 *
 * @author mozhifan
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:19:50
 */
public interface EntStudentExperienceInfoService extends IService<EntStudentExperienceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

