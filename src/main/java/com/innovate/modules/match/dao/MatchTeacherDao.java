package com.innovate.modules.match.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.match.entity.MatchTeacherEntity;
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
public interface MatchTeacherDao extends BaseMapper<MatchTeacherEntity> {

    List<MatchTeacherEntity> queryAll(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
