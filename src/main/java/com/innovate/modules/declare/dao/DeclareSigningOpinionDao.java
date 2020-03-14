package com.innovate.modules.declare.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.declare.entity.DeclareRetreatEntity;
import com.innovate.modules.declare.entity.DeclareSigningOpinionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Mapper
public interface DeclareSigningOpinionDao extends BaseMapper<DeclareSigningOpinionEntity> {

    List<DeclareRetreatEntity> queryDeclareSigningOpinion(Map<String, Object> params);

    /**
     * 通过项目ID查询指导老师签署意见
     * @param declareId
     * @return
     */
    DeclareSigningOpinionEntity queryDeclareSigningOpinionByDeclareId(Long declareId);

    void remove(Map<String, Object> params);
}
