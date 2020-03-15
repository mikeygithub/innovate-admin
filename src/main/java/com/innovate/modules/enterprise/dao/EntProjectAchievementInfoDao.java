package com.innovate.modules.enterprise.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.enterprise.entity.EntProjectAchievementInfoEntity;
import com.innovate.modules.enterprise.service.EntProjectCooperationInfoService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目成果信息表
 * 
 * @author 莫智帆
 * @email 1217567927@qq.com
 * @date 2019-09-10 22:18:36
 */
@Mapper
public interface EntProjectAchievementInfoDao extends BaseMapper<EntProjectAchievementInfoEntity> {

    Integer queryCountPage(Map<String, Object> params);

    /**
     * 根据当前登录用户Id查项目成果-学生
     * @param
     * @return
     */
    List<EntProjectAchievementInfoEntity> queryProjectAchievementByUserPerId(@Param("userPerId") Long userPerId, @Param("inApply") String inApply);

    /**
     * 根据当前登录用户Id查项目成果-教师
     * @param
     * @return
     */
    List<EntProjectAchievementInfoEntity> queryProjectAchievementByUserTeacherId(@Param("userTeacherId") Long userTeacherId, @Param("inApply") String inApply);

    /**
     * 根据当前登录用户Id查项目成果-企业
     * @param
     * @return
     */
    List<EntProjectAchievementInfoEntity> queryProjectAchievementByEntInfoId(@Param("entInfoId") Long entInfoId, @Param("inApply") String inApply);

    /**
     * 更新项目合作审核状态
     * @param params
     * @return
     */
    boolean updateProjectExamine(Map<String, Object> params);
}
