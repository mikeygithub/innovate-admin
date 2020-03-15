package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.ProjectLegalInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:法人
 **/
@Mapper
public interface ProjectLegalInfoDao extends BaseMapper<ProjectLegalInfoEntity> {

    List<ProjectLegalInfoEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
