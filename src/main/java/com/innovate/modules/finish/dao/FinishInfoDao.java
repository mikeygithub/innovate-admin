package com.innovate.modules.finish.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.finish.entity.FinishInfoEntity;
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
public interface FinishInfoDao extends BaseMapper<FinishInfoEntity> {

    /**
     * 获得项目ID
     */
    FinishInfoEntity queryById(Long finishId);

    Integer queryCountPage(Map<String, Object> params);

    List<FinishInfoEntity> queryPage(Map<String, Object> params);

    List<FinishInfoEntity> noPass(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
