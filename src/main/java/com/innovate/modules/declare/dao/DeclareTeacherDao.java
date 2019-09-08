package com.innovate.modules.declare.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.declare.entity.DeclareTeacherEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:07
 * @Version 1.0
 */
@Mapper
public interface DeclareTeacherDao extends BaseMapper<DeclareTeacherEntity> {

    List<DeclareTeacherEntity> queryAll(Map<String, Object> params);

    //统计参与者个数
    Long queryTeacherNum(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
