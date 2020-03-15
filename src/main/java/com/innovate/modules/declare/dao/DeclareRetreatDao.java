package com.innovate.modules.declare.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.check.entity.InnovateCheckRetreatEntity;
import com.innovate.modules.declare.entity.DeclareRetreatEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Mapper
public interface DeclareRetreatDao extends BaseMapper<DeclareRetreatEntity> {

    List<DeclareRetreatEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
