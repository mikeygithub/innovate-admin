package com.innovate.modules.enterprise.service;


import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntTeacherExperienceInfoEntity;

import java.util.Map;

/**
 * 教师科研经历/成果信息表
 *
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
public interface EntTeacherExperienceInfoService extends IService<EntTeacherExperienceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

