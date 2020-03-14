package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportTotalEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表统计
 **/
public interface ProjectMonthlyReportTotalService extends IService<ProjectMonthlyReportTotalEntity> {
    @Transactional
    ProjectMonthlyReportTotalEntity total(Map<String, Object> params);
}
