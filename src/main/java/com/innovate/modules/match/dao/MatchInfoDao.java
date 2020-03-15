package com.innovate.modules.match.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.match.entity.MatchInfoEntity;
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
public interface MatchInfoDao extends BaseMapper<MatchInfoEntity> {

    /**
     * 获得项目ID
     */
    MatchInfoEntity queryById(Long matchId);

    /**
     * 通过名称获得项目
     */
    List<MatchInfoEntity> queryByName(Map<String, Object> params);

    Integer queryCountPage(Map<String, Object> params);

    List<MatchInfoEntity> queryPage(Map<String, Object> params);

    List<MatchInfoEntity> noPass(Map<String, Object> params);

    //统计项目个数
    Long queryProjectNum(Map<String, Object> params);

    //统计负责人个数
    Long queryUserNum(Map<String, Object> params);

    void remove(Map<String, Object> params);

}
