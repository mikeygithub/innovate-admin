package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表
 **/
public interface ProjectMonthlyReportService extends IService<ProjectMonthlyReportEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<ProjectMonthlyReportEntity> queryAllReport();
    //累计投入资金数额
    Double totalInvest(Map<String, Object> params);
    //累计营业额
    Double totalSales(Map<String, Object> params);
    //累计利润情况
    Double totalProfits(Map<String, Object> params);
    //累计上缴税金情况
    Double totalTax(Map<String, Object> params);
    //删除项目
    void remove(Map<String, Object> params);
}
