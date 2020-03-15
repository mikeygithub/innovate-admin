package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.BaseTeacherInfoEntity;

import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:18
 * @Version 1.0
 */
public interface BaseTeacherInfoService extends IService<BaseTeacherInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     *  统计教师人数
     *  参数：任职情况：1-专职，2-兼职
     */
    Long queryAllTeacher(Long baseId);
    Long queryTypeTeacher(Long baseId, Long status);
}
