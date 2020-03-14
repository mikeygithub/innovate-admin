package com.innovate.modules.declare.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.declare.entity.DeclareInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:18
 * @Version 1.0
 */
@Mapper
public interface DeclareInfoDao extends BaseMapper<DeclareInfoEntity> {

    /**
     * 获得项目ID
     */
    DeclareInfoEntity queryById(Long declareId);

    Integer queryCountPage(Map<String, Object> params);

    List<DeclareInfoEntity> queryPage(Map<String, Object> params);

    List<DeclareInfoEntity> noPass(Map<String, Object> params);

    //统计项目个数
    Long queryDeclareProjectNum(Map<String, Object> params);
    //统计创新训练项目数
    Long queryNewProjectNum(Map<String, Object> params);
    //统计创业训练项目数
    Long queryTrainProjectNum(Map<String, Object> params);
    //统计创业实践项目数
    Long queryPracticeProjectNum(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
