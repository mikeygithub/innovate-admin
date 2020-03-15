package com.innovate.modules.innovate.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.innovate.modules.innovate.dao.UserTeacherInfoDao;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserTeacherInfoService;
import com.innovate.modules.sys.entity.SysUserEntity;
import com.innovate.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mikey
 * @Title:
 * @Description:
 * @date 2018/11/8 17:51
 * @Version 1.0
 */
@Service
public class UserTeacherInfoServiceImpl extends ServiceImpl<UserTeacherInfoDao, UserTeacherInfoEntity> implements UserTeacherInfoService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysUserEntity> queryTeacher(Map<String, Object> params) {
        return baseMapper.queryTeacher(params);
    }

    @Override
    public List<UserTeacherInfoEntity> queryProjectTeacherInfo(Long projectId) {
        return baseMapper.queryProjectTeacherInfo(projectId);
    }

    @Override
    public List<UserTeacherInfoEntity> queryMatchTeacherInfo(Long matchId) {
        return baseMapper.queryMatchTeacherInfo(matchId);
    }

    @Override
    public List<UserTeacherInfoEntity> queryDeclareTeacherInfo(Long declareId) {
        return baseMapper.queryDeclareTeacherInfo(declareId);
    }

    @Override
    public List<UserTeacherInfoEntity> queryFinishTeacherInfo(Long finishId) {
        return baseMapper.queryFinishTeacherInfo(finishId);
    }

    @Override
    public List<UserTeacherInfoEntity> queryAllTeacherInfo() {
        return baseMapper.queryAllTeacherInfo();
    }

    @Override
    public UserTeacherInfoEntity queryByUserId(Long userId) {
        UserTeacherInfoEntity userTeacherInfoEntity = baseMapper.queryByUserId(userId);
        SysUserEntity sysUserEntity = sysUserService.selectById(userId);
        if (null == userTeacherInfoEntity) {
            userTeacherInfoEntity = new UserTeacherInfoEntity();
        }
        userTeacherInfoEntity.setSysUserEntity(sysUserEntity);

        return userTeacherInfoEntity;
    }

    @Override
    public void saveOrUpdate(UserTeacherInfoEntity userTeacherInfoEntity) {
        SysUserEntity sysUserEntity = userTeacherInfoEntity.getSysUserEntity();
        userTeacherInfoEntity.setSysUserEntity(null);
        this.insertOrUpdate(userTeacherInfoEntity);
        sysUserService.insertOrUpdate(sysUserEntity);
    }

    @Override
    public Long queryUserTeacherIdByUserId(Long userId) {
        return baseMapper.queryUserTeacherIdByUserId(userId);
    }
}
