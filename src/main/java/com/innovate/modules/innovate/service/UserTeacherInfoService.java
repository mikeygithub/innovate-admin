package com.innovate.modules.innovate.service;

import com.baomidou.mybatisplus.service.IService;
import com.innovate.common.utils.PageUtils;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.sys.entity.SysUserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:18
 * @Version 1.0
 */
public interface UserTeacherInfoService extends IService<UserTeacherInfoEntity> {

    List<SysUserEntity> queryTeacher(Map<String, Object> params);
    List<UserTeacherInfoEntity> queryProjectTeacherInfo(Long projectId);
    List<UserTeacherInfoEntity> queryMatchTeacherInfo(Long matchId);
    List<UserTeacherInfoEntity> queryDeclareTeacherInfo(Long declareId);
    List<UserTeacherInfoEntity> queryFinishTeacherInfo(Long declareId);
    List<UserTeacherInfoEntity> queryAllTeacherInfo();
    UserTeacherInfoEntity queryByUserId(Long userId);

    @Transactional
    void saveOrUpdate(UserTeacherInfoEntity userTeacherInfoEntity);

    /**
     * 用户id 查教师id
     * @param userId
     * @return
     */
    Long queryUserTeacherIdByUserId(Long userId);

}
