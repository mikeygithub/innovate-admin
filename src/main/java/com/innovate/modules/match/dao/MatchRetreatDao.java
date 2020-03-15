package com.innovate.modules.match.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.match.entity.MatchRetreatEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Mapper
public interface MatchRetreatDao extends BaseMapper<MatchRetreatEntity> {

    List<MatchRetreatEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
