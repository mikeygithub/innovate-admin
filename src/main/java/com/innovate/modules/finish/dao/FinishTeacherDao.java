package com.innovate.modules.finish.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.finish.entity.FinishTeacherEntity;
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
public interface FinishTeacherDao extends BaseMapper<FinishTeacherEntity> {

    List<FinishTeacherEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
