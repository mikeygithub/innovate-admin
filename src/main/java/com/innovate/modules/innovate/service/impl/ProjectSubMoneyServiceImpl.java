package com.innovate.modules.innovate.service.impl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.innovate.common.ProjectCalculate;
import com.innovate.modules.innovate.dao.ProjectSubMoneyDao;
import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.entity.ProjectSubMoneyEntity;
import com.innovate.modules.innovate.service.BaseInfoService;
import com.innovate.modules.innovate.service.ProjectSubMoneyService;
import com.innovate.modules.innovate.utils.ProjectCalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:49
 * @Version 1.0
 */
@Service
public class ProjectSubMoneyServiceImpl extends ServiceImpl<ProjectSubMoneyDao, ProjectSubMoneyEntity> implements ProjectCalculate, ProjectSubMoneyService {

    ProjectSubMoneyServiceImpl() {
        ProjectCalculateUtil.addObject(this);
    }

    @Autowired
    private BaseInfoService baseInfoService;

    @Override
    public List<ProjectSubMoneyEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public void remove(Map<String, Object> params) {
        baseMapper.remove(params);
    }

    @Override
    public Double querySubMoney(Long subType, Long baseId) {
        return baseMapper.querySubMoney(subType,baseId);
    }

    @Override
    public Long queryInvestNum(Long subType, Long baseId) {
        return baseMapper.queryInvestNum(subType,baseId);
    }

    @Override
    public void calculate(Long projectId,Long baseId) {
        if (null != baseId) {
            BaseInfoEntity baseInfoEntity = baseInfoService.selectById(baseId);
            /**
             * 统计补贴金额
             * 参数：补助类型：1投资，2管理服务，3房租和宽带，4水电费，5一次性创业，6吸纳困难群体，7社会保险，8创业担保贷款
             */
            // 场地：
            Double site = this.querySubMoney(3L, baseId);
            if (null == site) {
                site = 0.0;
            }
            //logger.info("tz--site-->"+site);
            //水电:
            Double water = this.querySubMoney(4L, baseId);
            if (null == water) {
                water = 0.0;
            }
            Double allSub = site + water;
            baseInfoEntity.setBaseAllSubsidy(allSub);

            //一次性创业补贴
            Double firstSubsidy = this.querySubMoney(5L, baseId);
            if (null == firstSubsidy) {
                firstSubsidy = 0.0;
            }
            baseInfoEntity.setBaseFirstSubsidy(firstSubsidy);

            //就业困难群体
            Double empHard = this.querySubMoney(6L, baseId);
            if (null == empHard) {
                empHard = 0.0;
            }
            baseInfoEntity.setBaseAllEmpHard(empHard);
            baseInfoEntity.setBaseYearGrant(empHard);

            //社会保险补贴
            Double Insurance = this.querySubMoney(7L, baseId);
            if (null == Insurance) {
                Insurance = 0.0;
            }
            baseInfoEntity.setBaseInsurance(Insurance);

            //创业担保贷款
            Double allLoan = this.querySubMoney(8L, baseId);
            if (null == allLoan) {
                allLoan = 0.0;
            }
            baseInfoEntity.setBaseAllLoan(allLoan);

            //获得投融资的数量
            Long getInvest = this.queryInvestNum(1L, baseId);
            if (null == getInvest) {
                getInvest = 0L;
            }
            //logger.info("tz--getInvest-->"+getInvest);
            baseInfoEntity.setBaseProAllInvestNum(getInvest);

            //获得投资总额
            Double allInvest = this.querySubMoney(1L, baseId);
            if (null == allInvest) {
                allInvest = 0.0;
            }
            baseInfoEntity.setBaseProAllInvestAllMoney(allInvest);
            //基地累计获得投资金额总数
            baseInfoEntity.setBaseInvestMoney(allInvest);

            baseInfoService.updateAllColumnById(baseInfoEntity);
        }
    }
}
