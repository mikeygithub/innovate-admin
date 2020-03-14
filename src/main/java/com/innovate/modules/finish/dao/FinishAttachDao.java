package com.innovate.modules.finish.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.finish.entity.FinishAttachEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @Email:1625017540@qq.com
 * @date 2018/11/19 18:36
 * @Version 1.0
 */
@Mapper
public interface FinishAttachDao extends BaseMapper<FinishAttachEntity> {

    List<FinishAttachEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
