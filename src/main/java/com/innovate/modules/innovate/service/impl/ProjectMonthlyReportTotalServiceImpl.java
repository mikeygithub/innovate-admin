package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.innovate.modules.innovate.dao.ProjectMonthlyReportTotalDao;
import com.innovate.modules.innovate.entity.ProjectMonthlyReportTotalEntity;
import com.innovate.modules.innovate.service.ProjectMonthlyReportService;
import com.innovate.modules.innovate.service.ProjectMonthlyReportTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表统计
 **/
@Service
public class ProjectMonthlyReportTotalServiceImpl extends ServiceImpl<ProjectMonthlyReportTotalDao, ProjectMonthlyReportTotalEntity> implements ProjectMonthlyReportTotalService {

    @Autowired
    private ProjectMonthlyReportTotalService projectMonthlyReportTotalService;
    @Autowired
    private ProjectMonthlyReportService projectMonthlyReportService;

    @Override
    public ProjectMonthlyReportTotalEntity total(Map<String, Object> params) {
        Long id = Long.parseLong((String)params.get("projectId"));
        Long longTotalStartTime = Long.parseLong((String)params.get("totalStartTime"));
        Long longTotalEndTime = Long.parseLong((String)params.get("totalEndTime"));
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        String stringTotalStartTime = format.format(longTotalStartTime);
        String stringTotalEndTime = format.format(longTotalEndTime);
        Date totalStartTime = null;
        Date totalEndTime = null;
        try {
            totalStartTime = format.parse(stringTotalStartTime);
            totalEndTime = format.parse(stringTotalEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("totalStartTime", totalStartTime);
        params.put("totalEndTime", totalEndTime);

        Double totalInvestCapital = projectMonthlyReportService.totalInvest(params);
        Double totalSales = projectMonthlyReportService.totalSales(params);
        Double totalProfits = projectMonthlyReportService.totalProfits(params);
        Double totalTax = projectMonthlyReportService.totalTax(params);

        ProjectMonthlyReportTotalEntity projectMonthlyReportTotalEntity = new ProjectMonthlyReportTotalEntity();
        projectMonthlyReportTotalEntity.setProjectId(id);
        //累计投入资金数额
        projectMonthlyReportTotalEntity.setTotalInvestCapital(totalInvestCapital);
        //累计营业额
        projectMonthlyReportTotalEntity.setTotalSales(totalSales);
        //累计利润情况
        projectMonthlyReportTotalEntity.setTotalProfits(totalProfits);
        //累计上缴税金情况
        projectMonthlyReportTotalEntity.setTotalTax(totalTax);

        projectMonthlyReportTotalEntity.setTotalStartTime(totalStartTime);
        projectMonthlyReportTotalEntity.setTotalEndTime(totalEndTime);
        System.out.println(new Gson().toJson(projectMonthlyReportTotalEntity));

//        projectMonthlyReportTotalService.insertOrUpdate(projectMonthlyReportTotalEntity);
        return projectMonthlyReportTotalEntity;
    }
}
