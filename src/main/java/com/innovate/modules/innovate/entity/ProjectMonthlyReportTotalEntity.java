package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表统计
 **/
@Data
@TableName("innovate_project_monthly_report_total")
public class ProjectMonthlyReportTotalEntity implements Serializable {

    private Long projectId;

    @JsonSerialize(using=ToStringSerializer.class)
    private Double totalInvestCapital;

    @JsonSerialize(using=ToStringSerializer.class)
    private Double totalSales;

    @JsonSerialize(using=ToStringSerializer.class)
    private Double totalProfits;

    @JsonSerialize(using=ToStringSerializer.class)
    private Double totalTax;

    private Date totalStartTime;
    private Date totalEndTime;

}
