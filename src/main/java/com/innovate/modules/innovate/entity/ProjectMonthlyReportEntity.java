package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:tz
 * @create:2019-01-08
 * @description:项目月报表
 **/
@Data
@TableName("innovate_project_monthly_report")
public class ProjectMonthlyReportEntity implements Serializable {
    @TableId
    private Long reportId;
    private Long projectId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Double reportInvestCapital;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double reportSales;
    private String reportSalesName;
    private String reportSalesPath;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double reportProfits;
    private String reportProfitsName;
    private String reportProfitsPath;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double reportTax;
    private String reportTaxName;
    private String reportTaxPath;
    private Date reportTime;
    private Long isDel;
}
