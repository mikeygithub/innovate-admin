package com.innovate.modules.declare.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.modules.declare.entity.DeclareInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:10
 * @Version 1.0
 */
public interface DeclareInfoService extends IService<DeclareInfoEntity> {
    /**
     * 获得项目ID
     */
    DeclareInfoEntity queryById(Long declareId);

    /**
     * 查询满足条件的项目总数
     * @param params
     * @return
     */
    Integer queryCountPage(Map<String, Object> params);

    /**
     * 查询满足条件的项目
     * @param params
     * @return
     */
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

    /**
     * 删除项目
     * @param params
     */
    void remove(Map<String, Object> params);
}
