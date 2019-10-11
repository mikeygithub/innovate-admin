package com.innovate.modules.enterprise.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.enterprise.entity.EntTeacherExperienceInfoEntity;

import java.util.Map;

/**
 * 教师科研经历/成果信息表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-10-10 10:10:39
 */
public interface EntTeacherExperienceInfoService extends IService<EntTeacherExperienceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

