package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:基地信息
 **/
@Data
@TableName("innovate_base_info")
public class BaseInfoEntity implements Serializable {
    @TableId
    private Long baseId;
//    必填
    private String baseName;
//    必填
    private Long baseStationType;
//    必填
    private String baseMessage;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseArea;
    private Long stationNum;
    private Long baseServiceNum;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseInvestMoney;
    private Long baseNowBaseNum;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAreaUseRate;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAllInMoney;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseOperateCost;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseTeamArea;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseServiceArea;
    private Long baseAllTeacherNum;
    private Long baseFullTeacherNum;
    private Long basePartTeacherNum;
    private Long baseCompanyNum;
    private Long baseItemNum;
    private Long baseAllCompanyNum;
    private Long baseAllItemNum;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAllInvest;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAllSales;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAllSubsidy;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseFirstSubsidy;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAllEmpHard;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseInsurance;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseAllLoan;
    private Long baseYearProbate;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseYearGrant;
    private Long baseWorkNum;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseOutRate;
    private Long baseDirectNum;
    private Long baseOutNum;
    private Long baseEntityNum;
    private Long baseInitCompany;
    private Long baseAllInitCompany;
    private Long baseProInvestNum;
    private Long baseProAllInvestNum;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseProInvestAllMoney;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseProInvestSocietyMoney;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseProInvestBaseMoney;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseProAllInvestAllMoney;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseProAllInvestSocietyMoney;
    @JsonSerialize(using=ToStringSerializer.class)
    private Double baseProAllInvestBaseMoney;
    private Long baseStuNum;
    private Long baseScienceNum;
    private Long baseSeriesNum;
    private Long baseIprNum;
    private Long baseNewNum;

}
