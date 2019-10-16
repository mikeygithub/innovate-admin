package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.sys.entity.SysUserEntity;
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
public interface UserTeacherInfoDao extends BaseMapper<UserTeacherInfoEntity> {

    List<SysUserEntity> queryTeacher(Map<String, Object> params);

    List<UserTeacherInfoEntity> queryProjectTeacherInfo(Long projectId);
    List<UserTeacherInfoEntity> queryMatchTeacherInfo(Long matchId);
    List<UserTeacherInfoEntity> queryDeclareTeacherInfo(Long declareId);
    List<UserTeacherInfoEntity> queryFinishTeacherInfo(Long finishId);
    List<UserTeacherInfoEntity> queryAllTeacherInfo();
    List<UserTeacherInfoEntity> queryAll(Long userTeacherId);
    Long deleteByProjectId(Long projectId);
    UserTeacherInfoEntity queryByUserId(Long userId);

    /**
     * 用户id 查教师id
     * @param userId
     * @return
     */
    Long queryUserTeacherIdByUserId(Long userId);

}
