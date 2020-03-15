package com.innovate.modules.finish.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.finish.entity.FinishSigningOpinionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:项目流程回退
 **/
@Mapper
public interface FinishSigningOpinionDao extends BaseMapper<FinishSigningOpinionEntity> {

    List<FinishSigningOpinionEntity> queryFinishSigningOpinion(Map<String, Object> params);

    /**
     * 通过项目ID查询指导老师签署意见
     * @param finishId
     * @return
     */
    FinishSigningOpinionEntity queryFinishSigningOpinionByFinishId(Long finishId);

    void remove(Map<String, Object> params);
}
