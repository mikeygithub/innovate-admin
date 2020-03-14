package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.ProjectAwardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:29
 * @Version 1.0
 */
@Mapper
public interface ProjectAwardDao extends BaseMapper<ProjectAwardEntity> {

    List<ProjectAwardEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
