package com.innovate.modules.match.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.match.entity.MatchEventEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@Mapper
public interface MatchEventDao extends BaseMapper<MatchEventEntity> {

    List<MatchEventEntity> queryAllEvent();
    MatchEventEntity queryByEventId(Map<String, Object> params);

}
