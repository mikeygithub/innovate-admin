package com.innovate.modules.innovate.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 16:21
 * @Version 1.0
 */
@Mapper
public interface UserPersonInfoDao extends BaseMapper<UserPersonInfoEntity> {
    List<UserPersonInfoEntity> queryAllPersonInfo(Map<String, Object> params);
    Long deleteByProjectId(Long projectId);
    UserPersonInfoEntity queryByUserId(Long userId);

    List<UserPersonInfoEntity> queryByUserInstituteIds(Long instituteId);

    /**
     * 用户id 查学生id
     * @param userId
     * @return
     */
    Long queryUserPerIdByUserId(Long userId);

}
