package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.BaseProjectStationEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:17
 * @Version 1.0
 */
public interface BaseProjectStationService extends IService<BaseProjectStationEntity> {
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 统计工位数
     * 参数：基地id
     */
    Long queryStationNum(Long baseId);

    /**
     * 统计工位办公面积
     */
    Double queryArea(Long stationId);

    /**
     * 通过stationId查baseId
     */
    Long queryBaseId(Long stationId);

    List<BaseProjectStationEntity> queryAll();

    void hasApply(Long stationId);

    void delApply(Long stationId);
}
