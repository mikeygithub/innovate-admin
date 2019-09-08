package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.BaseProjectStationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:23
 * @Version 1.0
 */
@Mapper
public interface BaseProjectStationDao extends BaseMapper<BaseProjectStationEntity> {
    /**
     * 统计工位数
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
