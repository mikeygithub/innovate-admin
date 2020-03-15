package com.innovate.modules.enterprise.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.enterprise.entity.EntStudentAchievementInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 学生所获奖励/证书信息表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2019-10-11 20:55:46
 */
@Mapper
public interface EntStudentAchievementInfoDao extends BaseMapper<EntStudentAchievementInfoEntity> {
//    Integer queryCountPage(Map<String, Object> params);
//    List<EntStudentAchievementInfoEntity> queryPage(Map<String, Object> params);
}
