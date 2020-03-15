package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表
 **/
@Mapper
public interface ProjectMonthlyReportDao extends BaseMapper<ProjectMonthlyReportEntity> {
    List<ProjectMonthlyReportEntity> queryAllReport();
    /**
     * 删除项目
     * @param params
     */
    void remove(Map<String, Object> params);
    //累计投入资金数额
    Double totalInvest(Map<String, Object> params);
    //累计营业额
    Double totalSales(Map<String, Object> params);
    //累计利润情况
    Double totalProfits(Map<String, Object> params);
    //累计上缴税金情况
    Double totalTax(Map<String, Object> params);
}
