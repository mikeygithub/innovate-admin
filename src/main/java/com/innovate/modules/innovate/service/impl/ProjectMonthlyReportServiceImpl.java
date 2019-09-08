package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.dao.ProjectMonthlyReportDao;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportEntity;
import com.innovate.modules.innovate.service.ProjectMonthlyReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表
 **/
@Service
public class ProjectMonthlyReportServiceImpl extends ServiceImpl<ProjectMonthlyReportDao, ProjectMonthlyReportEntity> implements ProjectMonthlyReportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProjectMonthlyReportEntity> page = this.selectPage(
                new Query<ProjectMonthlyReportEntity>(params).getPage(),
                new EntityWrapper<ProjectMonthlyReportEntity>()
                        .eq("is_del", 0)
        );
        return new PageUtils(page);
    }

    @Override
    public List<ProjectMonthlyReportEntity> queryAllReport() {
        return baseMapper.queryAllReport();
    }

    @Override
    public Double totalInvest(Map<String, Object> params) {
        return baseMapper.totalInvest(params);
    }

    @Override
    public Double totalSales(Map<String, Object> params) {
        return baseMapper.totalSales(params);
    }

    @Override
    public Double totalProfits(Map<String, Object> params) {
        return baseMapper.totalProfits(params);
    }

    @Override
    public Double totalTax(Map<String, Object> params) {
        return baseMapper.totalTax(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }
}
