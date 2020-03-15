package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.BaseCostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:16
 * @Version 1.0
 */
@Mapper
public interface BaseCostDao extends BaseMapper<BaseCostEntity> {
    List<BaseCostEntity> list(Long baseId);
}
