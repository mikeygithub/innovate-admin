package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.Query;
import com.innovate.modules.innovate.common.BaseCalculate;
import com.innovate.modules.innovate.dao.BaseProjectStationDao;
import com.innovate.modules.innovate.entity.BaseInfoEntity;
import com.innovate.modules.innovate.entity.BaseProjectStationEntity;
import com.innovate.modules.innovate.service.BaseInfoService;
import com.innovate.modules.innovate.service.BaseProjectStationService;
import com.innovate.modules.innovate.utils.BaseCalculateUtil;
import com.innovate.modules.innovate.utils.BaseTeamAreaTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:47
 * @Version 1.0
 */
@Service
public class BaseProjectStationServiceImpl extends ServiceImpl<BaseProjectStationDao, BaseProjectStationEntity> implements BaseCalculate, BaseProjectStationService {

    @Autowired
    private BaseInfoService baseInfoService;

    BaseProjectStationServiceImpl() {
        BaseCalculateUtil.addObject(this);
    }
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseProjectStationEntity> page = this.selectPage(
                new Query<BaseProjectStationEntity>(params).getPage(),
                new EntityWrapper<BaseProjectStationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public Long queryStationNum(Long baseId) {
        return baseMapper.queryStationNum(baseId);
    }

    @Override
    public Double queryArea(Long stationId) {
        return baseMapper.queryArea(stationId);
    }

    @Override
    public Long queryBaseId(Long stationId) {
        return baseMapper.queryBaseId(stationId);
    }

    @Override
    public List<BaseProjectStationEntity> queryAll() {
        return baseMapper.queryAll();
    }

    @Override
    public void hasApply(Long stationId) {
        baseMapper.hasApply(stationId);
    }

    @Override
    public void delApply(Long stationId) {
        baseMapper.delApply(stationId);
    }

    @Override
    public void calculate(Long baseId) {
        BaseInfoEntity baseInfoEntity = baseInfoService.selectById(baseId);

        //工位数
        Long stationNum = this.queryStationNum(baseId);
        if (null == stationNum){
            stationNum = 0L;
        }
        baseInfoEntity.setStationNum(stationNum);

        //企业使用面积
        Double baseTeamArea = BaseTeamAreaTotal.calculate(baseId);
        //logger.error("tz---工位面积--->"+baseTeamArea);
        baseInfoEntity.setBaseTeamArea(baseTeamArea);

        //场地利用率
        Double userRate = baseTeamArea / (baseInfoEntity.getBaseArea());
        //logger.error("tz---场地利用率--->"+userRate);
        baseInfoEntity.setBaseAreaUseRate(userRate);

        baseInfoService.updateAllColumnById(baseInfoEntity);
    }
}
