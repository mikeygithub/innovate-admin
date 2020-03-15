package com.innovate.modules.match.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.match.entity.MatchStaffInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:23
 * @Version 1.0
 */
@Mapper
public interface MatchStaffInfoDao extends BaseMapper<MatchStaffInfoEntity> {

    List<MatchStaffInfoEntity> queryAll(Map<String, Object> params);

    //统计参与者个数
    Long queryUserNum(Map<String, Object> params);

    void remove(Map<String, Object> params);
}
