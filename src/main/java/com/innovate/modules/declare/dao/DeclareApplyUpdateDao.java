package com.innovate.modules.declare.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.declare.entity.DeclareApplyUpdateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目申请修改
 **/
@Mapper
public interface DeclareApplyUpdateDao extends BaseMapper<DeclareApplyUpdateEntity> {

    List<DeclareApplyUpdateEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
